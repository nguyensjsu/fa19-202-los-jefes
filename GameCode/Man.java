import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Man here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Man extends Subject
{
    /**
     * Act - do whatever the Man wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ISubject scoreBoardObs = null;
    int health = 1000000;
    int animationCounter=0;
    int timer=1;
    boolean trackMovement=false;
    class MotionRenderer {
        String file;
        GreenfootImage image;
        public MotionRenderer (String file){
            this.file=file;
            this.image= new GreenfootImage(file);
        }
    }
    Monster monster=null;
    private MotionRenderer img,imgW,imgA,imgS,imgD,imgR,imgB,imgL;
    public Man()
    {
        //img = new GreenfootImage("warrior.png");
        //imgW= new GreenfootImage("warrior-front-attack.png");
        //imgD= new GreenfootImage("warrior-right-attack.png");
        //imgA= new GreenfootImage("warrior-left-attack.png");
        //imgS= new GreenfootImage("warrior-back-attack.png");
        //img.scale(60,60);
        
        img = new MotionRenderer("warrior.png");
        imgW= new MotionRenderer("warrior-front-attack.png");
        imgD= new MotionRenderer("warrior-right-attack.png");
        imgA= new MotionRenderer("warrior-left-attack.png");
        imgS= new MotionRenderer("warrior-back-attack.png");
        imgR= new MotionRenderer("warrior-right.png");
        imgB=new MotionRenderer("warrior-back.png");
        imgL=new MotionRenderer("warrior-left.png");
        img.image.scale(60,60);
        
        
        
        //gif = new GifImage("skeleton-club.gif");
        //gif.resizeImages(60,60);
        
        setImage(img);
        
    }
    public void updateDamage(ISubject s) {
        if(s instanceof Monster) {
            this.health = this.health-1;
            notifyObservers(s);
            System.out.println(this.health);
        }
        if(s instanceof banana) {
            this.health = this.health+5;
            notifyObservers(s);
        }
        if(s instanceof bear) {
            this.health = this.health-50;
            notifyObservers(s);
        }
    }
    public void notifyObservers(ISubject s){
        //man shud update score board observer
        if(s!=null) {
        ((Scoreboard)scoreBoardObs).setValue(health);
        }
    }
    public void addObservers(ISubject s){
        scoreBoardObs = s;
    }
     private void checktouching(){
        if(isTouching(banana.class)) {
            removeTouching(banana.class);
            this.updateDamage(new banana());
            //notifyObservers() i.e scoreboard 
        }
        if(isTouching(bear.class)){
            //removeTouching(bear.class);
            this.updateDamage(((ISubject)this.getOneIntersectingObject(bear.class)));
        }
    }
    public void setImage(MotionRenderer img){
    //System.err.println("Man image is set to "+img.fileName);
    super.setImage(img.image);
    }
    public void act() 
    {
        
         movement();
         trackMovement = Greenfoot.isKeyDown("up")||Greenfoot.isKeyDown("right")||Greenfoot.isKeyDown("left")||Greenfoot.isKeyDown("down");
         if(trackMovement==false){
         timer=1;
        
       
        setImage(img);
          }
         checktouching();
         attack();
         animationCounter++;
        // Add your action code here.
    }
    private void attack(){
        
            if(Greenfoot.isKeyDown("a")){
                 if(animationCounter%2==0)
               animateAttack(imgA);
               
               
            }
            if(Greenfoot.isKeyDown("d")) {
                  if(animationCounter%2==0)
               animateAttack(imgD);
              
                              
            }
            if(Greenfoot.isKeyDown("w")) {
                 if(animationCounter%2==0)
               animateAttack(imgW);
                
                                
            }
            if(Greenfoot.isKeyDown("s")) {
                if(animationCounter%2==0)
               animateAttack(imgS);
                
            }
        if(getObjectsInRange(50, Monster.class).size()>0) {
            monster = getObjectsInRange(50, Monster.class).get(0);
            if(Greenfoot.isKeyDown("a")||Greenfoot.isKeyDown("d")||Greenfoot.isKeyDown("w")||Greenfoot.isKeyDown("s"))
             monster.updateDamage(this);
        }
    }
    public void animateAttack(MotionRenderer img)
    {
      
        if(timer == 1)
        setImage(img);
        else if(timer==2)
        setImage(imgD);
        else if(timer ==3)
        setImage(imgA);
        //else if(timer>=4)
        //endAnimation();
        timer++;
        
    }
    private void checkLocation() {
        System.out.println(getX()+"  , "+getY());
        if(getX() == 599)
            Greenfoot.setWorld(new MonsterWorld());
    }
    public void movement()
    {
            if(this.isTouching(Monster.class))
            {
                move(0);
            }
            
            if(Greenfoot.isKeyDown("up") )
            {
                setImage(img);
                setLocation(getX(),getY()-2) ;
            }
            if(Greenfoot.isKeyDown("down") )
             {  
                 setImage(imgB);
                 setLocation(getX(),getY()+2);
             }
            if(Greenfoot.isKeyDown("right") )
            {
                setImage(imgR);
                setLocation(getX()+2,getY());
            }
            if(Greenfoot.isKeyDown("left"))
            {
                setImage(imgL);
                setLocation(getX()-2,getY());
            }
            
    }
}
