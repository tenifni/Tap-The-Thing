import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public class TTT implements ActionListener

{
    JSlider s;
    JButton[] h;
    JLabel rst,hint;
    int t;
    int index;
    int total=0,click=0; 
    public static JLabel label = new JLabel();
   
    public void init()
    {
        JFrame game =new JFrame("Hit The Face");
        game.setVisible(true);
        game.setLayout(new BorderLayout());
        game.setSize(800, 600);
        
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        game.getContentPane().add("North",p1);
        game.getContentPane().add("Center",p2);
        
        p1.setLayout(new GridLayout(2,2));
        JPanel p3=new JPanel();
        s =new JSlider(2,6);                                      
        hint=new JLabel("Change Speed: Fast-----------Slow");
        
        p1.setLayout(new GridLayout(3,2)); //put hint&slider in p1
        p1.add(hint);
        p1.add(s);
        p1.add(p3);
        rst=new JLabel(" ");
        JButton exit=new JButton("Exit");
        JButton clear =new JButton("Clear Score");
        rst.setText("score£∫0 / 0");
        
             
        TimerExample t = new TimerExample();
        label.setText("Timer");
        p1.add(label);
       
        p3.add(rst);// put exit, reset in p3
        p3.add(exit);
        p3.add(clear);
        
        p2.setLayout(new GridLayout(6,6));
        h=new JButton[36]; 
        	
        for(index=0;index<36;index++)
        {
        	ImageIcon Simile = new ImageIcon (TTT.class.getResource("Simile.jpg"));
        	Image SimileImage = Simile.getImage();
        	Image ScaledSimile = SimileImage.getScaledInstance(125,100,Image.SCALE_DEFAULT);
            ImageIcon SimileIcon = new ImageIcon(ScaledSimile);
   			
            h[index]=new JButton(SimileIcon);
            h[index].addActionListener(this); 
            	         
            p2.add(h[index]);
            h[index].setVisible(false);         
            h[index].setEnabled(false);
        }
        clear.addActionListener(this);        
        exit.addActionListener(this);  
         
        try
      {
         URL url = this.getClass().getClassLoader().getResource("GuyL.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);       
         Clip clip = AudioSystem.getClip(); 
         clip.open(audioIn);
         clip.loop(Clip.LOOP_CONTINUOUSLY);
         
     }catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
    
   
  	 try 
  	{
         
         URL url2 = this.getClass().getClassLoader().getResource("OrangeLaughter.wav");
         AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(url2);       
         Clip clip = AudioSystem.getClip(); 
         clip.open(audioIn2);
         clip.loop(Clip.LOOP_CONTINUOUSLY);
         
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
       
    }
    
    
    
    
    
    public void play()
    {
        Random rm=new Random(new GregorianCalendar().getTimeInMillis());   
        
        while(true)
       {
            index=rm.nextInt(36);      
            h[index].setVisible(true);  
            h[index].setEnabled(true);
            total++;        
            t=s.getValue();     
            try
            {
                Thread.currentThread();
                Thread.sleep(150*t);                    
            }
            catch(InterruptedException e){System.exit(0);};
            rst.setText("score£∫"+String.valueOf(click)+" / "+String.valueOf(total)+"              ");  
            h[index].setVisible(false);     
            h[index].setEnabled(false);
            
     }
             
    }
    
  
    public void actionPerformed(ActionEvent e)
     {
        String order=e.getActionCommand();
        if(order.equalsIgnoreCase("Clear score"))
        {
            click=0;
            total=0;
        }
        if(order.equalsIgnoreCase("Exit")) System.exit(0);
        if (e.getSource() == h[index])
           
        {
            click++;            
            JButton b = (JButton)e.getSource();       
            b.setVisible(false);        
            b.setEnabled(false);
        }
        
    }
    
     public static void main(String[] args)
    {
        TTT hm=new TTT();
        hm.init();
        hm.play(); 
    }
}









