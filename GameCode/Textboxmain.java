import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Textboxmain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Textboxmain extends Actor
{
    /**
     * Act - do whatever the Textboxmain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Textbox tb =null;
    GreenfootImage img;
    GreenfootImage map;
    GreenfootImage textDec;
    Color n = new Color(255,232,213);
     public  Textboxmain(){
        
          textDec= new GreenfootImage("txtbox.png");
        textDec.scale( textDec.getWidth()-400 , textDec.getHeight()-50);
         setImage(textDec);  
        tb=Textbox.getInstance();
       img = new GreenfootImage(" csjna ", 20, 
                                     Color.BLACK, n);
       
        //this.setLocation(100,100);
        //setImage(img);
        textDec.drawImage(img, textDec.getWidth()/2, textDec.getHeight()/2);
        this.setLocation(400,700);
        //setImage(imgM);
        }
    public void act() 
    {
        // Add your action code here.
         setImage(textDec);
         tb=Textbox.getInstance();
         if(WorldManager.getInstance().currentWorld instanceof MonsterWorld) {
         img = new GreenfootImage( tb.message , 20, 
                                     Color.BLACK , n );
          }
          else if(WorldManager.getInstance().currentWorld instanceof TavernWorld) {
         img = new GreenfootImage( tb.message , 20, 
                                     Color.BLACK , n);
            
            }
        map = new GreenfootImage( WorldManager.displayWorldMap() , 27, 
                                     Color.BLACK , n);   
                                     
        textDec.drawImage(map, 30, 30);
        textDec.drawImage(img, textDec.getWidth()/2, textDec.getHeight()/2);
        this.setLocation(400,700);
        //setImage(img);
    }    
}