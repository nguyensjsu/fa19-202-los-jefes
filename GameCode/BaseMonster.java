import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaseMonster here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class BaseMonster extends Subject 
{
    /**
     * Act - do whatever the BaseMonster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Man man = null;
    int health = 1000;
    boolean monsterDead = false;
    int weaponCode = 0;
    int attackpower = 1;
    int c = 1;
    String image1;
    String image2;

    class MotionRenderer {
        String file;
        GreenfootImage image;

        public MotionRenderer(String file) {
            this.file = file;
            this.image = new GreenfootImage(file);
            image.scale(60, 60);
        }
    }

    MotionRenderer im1, im2;

    BaseMonster(String image1, String image2, String name) 
    {
        if (name == null || name == "")
        {
            setActorTitle("MONSTER");
        }
        else
        {
            setActorTitle(name);
        }
        this.image1 = image1;
        this.image2 = image2;
        GreenfootImage img = new GreenfootImage(this.image1);

        img.scale(60, 60);
        this.setImage(img);
    }

    public void updateDamage(ISubject s) {
        if (s instanceof Man) {
            if (this.health <= 0) {
                monsterDead = true;
                this.getWorld().removeObject(this);
                ((Actor) s).getWorld().addObject(new Gold(), this.getRandomNumber(300, 500),
                        this.getRandomNumber(300, 500));
                // this.getWorld(). (new Gold(),300,100);
                Scoreboard.monsterDead();
            } else {
                /*
                 * here the weapon power is taken from weapon singleton class
                 */
                weaponCode = 0;// WeaponSingleton.getInstance().getCurrentWeapon();
                switch (weaponCode) {
                case 0:
                    attackpower = 1;
                    break;
                case 1:
                    attackpower = 5;
                    break;
                case 2:
                    attackpower = 10;
                    break;
                case 3:
                    attackpower = 15;
                    break;
                default:
                    attackpower = 2;
                }
                this.health = this.health - attackpower;
                notifyObservers(s);
                // System.out.println(this.health);
            }
        }
    }

    public void notifyObservers(ISubject s) {
        // man shud update score board observer
        if (s != null) {
            WorldManager.getCurrentWorld().getScoreboard().setMonsterHealth(health);
        }
    }


    public void animasi() {
        if (c == 1) {
            setImage(new MotionRenderer(this.image1).image);
            c = 2;
        } else if (c == 2) {
            setImage(new MotionRenderer(this.image2).image);
            c = 1;
        }

    }

    public void act() {
        WorldManager.getCurrentWorld().getScoreboard().setMonsterHealth(health);

        man = (Man) getOneIntersectingObject(Man.class);

        if (null == man) {
        } else {
            animasi();

            if (c==1)
            {
                man.damage(5);
            }
        }
    }


    public int getRandomNumber(int start, int end) {
        int normal = Greenfoot.getRandomNumber(end - start + 1);
        return normal + start;
    }
    
    public void createCommandBindings()
    {        
        IPlayerCommandTarget takeDamageCommand = new IPlayerCommandTarget(){
            public void act(Man player){
                updateDamage(((ISubject)player));
            }
        };
        newCommandBinding("A", "ATTACK!", takeDamageCommand);
    }
}