import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import java.io.*;
import javax.swing.*;



public class Game extends JPanel implements ActionListener, MouseListener, MouseMotionListener  {
   
   public ArrayList<Stalin> s = new ArrayList<Stalin>();
   
   
   private Stalin temp;
   private Stalin communistBackground;
   private Random r;
   private Menu menu = new Menu();
   
   private int test = 1;
   private int score = 0;
   private int stalinX = 100;
   private int stalinY = 100;
   private int stalinYSpeed = -5;
   private int stalinXSpeed = -5;
   private int radius1 = 100;
   private int radius2 = 175;
   
   public enum STATE {
      Menu,
      Game
   };
   public STATE GameState = STATE.Game;
   
   public Game() {
      r = new Random();
      Stalin stalin = new Stalin(100,100,"stalin.jpg");
      communistBackground = new Stalin(0,0,"sovietflag.jpg");
      setBackground(Color.WHITE);     
      setFocusable(true);
      addMouseListener(this);
      Timer timer = new Timer(1000/60, this);
      timer.start();
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if(score==10){
         menu.render(g);
      }
      else if(GameState == STATE.Game){
         g.setColor(Color.BLACK);
         communistBackground.render(g);
         for(int i = 0; i< s.size(); i++) {
            temp = s.get(i);
            temp.render(g);
         }
         repaint();
      }
   }
   
   public void actionPerformed(ActionEvent e) {
      reset();
   }
   
   public void reset()  {
      if(GameState == STATE.Game) {
         if(s.size()<=10) {
            for(int j= s.size(); j<9; j++) {
            /*try{
               Thread.sleep(2500);
            }
            catch(Exception e) {
            }
            */
               s.add(new Stalin(r.nextInt(1280), r.nextInt(1040-184), "stalin.jpg"));
            }
         }
         for(int i = 0; i<s.size(); i++) {
            Stalin TempStalin = s.get(i);
            if(s.get(i).getX() >= 1280-160)
               stalinXSpeed = -3;
            else if(s.get(i).getX()<=0)
               stalinXSpeed = 3;
            if(s.get(i).getY() <= 0)
               stalinYSpeed = 3;
            else if(s.get(i).getY() >= 1040-184)
               stalinYSpeed =-3;
            s.get(i).changeCoordinates(stalinXSpeed,stalinYSpeed);
            stalinX+=stalinXSpeed;
            stalinY+= stalinYSpeed;
         }
      }
   }
   public void mouseClicked(MouseEvent e) {
      if(GameState == STATE.Menu)
         GameState = STATE.Game;
      if(GameState == STATE.Game) {
         for(int i = 0; i<s.size(); i++) {
            Stalin TempStalin = s.get(i);
            if(e.getX() >= TempStalin.getX() && e.getX() <= TempStalin.getX() + radius1 && e.getY() >= TempStalin.getY() && e.getY() <= TempStalin.getY() + radius2 ) {
               killStalin(TempStalin);
               Background.score();
               score = Background.getScore();
            }
         }
      }
   }
   public void mouseEntered(MouseEvent e) {
   
   }
   public void mouseExited(MouseEvent e) {
   
   }
   public void mousePressed(MouseEvent e) {
   
   }
   public void mouseReleased(MouseEvent e) {
   
   }
   public void mouseDragged(MouseEvent e) {                      
   
   }
   public void mouseMoved(MouseEvent e) {
                                                                                                                           
   }
   public void addStalin(Stalin boo) {
      s.add(boo);
   }
   public void killStalin(Stalin boo) {
      s.remove(boo);   
   }
}