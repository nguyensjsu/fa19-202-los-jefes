import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tavern here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tavern extends GameActor
{
    /**
     * Act - do whatever the Tavern wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Tavern()
    {
        super();
        setImage(new GreenfootImage("Webp.net-resizeimage.png"));
    }
    public void act() 
    {
        // Add your action code here.
    }   

    public void createCommandBindings()
    {
        IPlayerCommandTarget healCmd = new IPlayerCommandTarget(){
            public void act(Man player){
                if (player.chargeGold(20) == true)
                {
                    player.heal(1000);
                    System.out.println("Tavern healing man");
                }
            }
        };
        newCommandBinding("Q", "Drink Ale to Heal (20 Gold)", healCmd);
    }

}
