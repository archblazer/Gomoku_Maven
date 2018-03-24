/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.Check;
import com.mycompany.mavenproject2.Brain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Guat Si Jie
 */
public class Gomoku {

    /**
     * @param args the command line arguments
     * 
     */
    
    private static int x,y,chall_switch=0;
    private int canClick=1;
    private static board current = new board();
    private static winlose player = new winlose();
    private static possible_location count_pieces =  new possible_location();
    private static board old1 = new board();
    private static draw object = new draw();
    private static normal object2=new normal();
    private static JFrame Bframe = new JFrame("Gomoku");
    private static JFrame Wframe = new JFrame("Welcome");
    private static JButton Ok = new JButton("Ok");   
    private static JButton reset = new JButton("Reset");
    private static JButton undo = new JButton("Undo");
    private static JButton challenge = new JButton("Challenge");
    private static JButton first = new JButton("First");  
    private static String name;
    private static JTextField text =  new JTextField ();
    private static JLabel p_name = new JLabel();
    private static int player_start=1;
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        
        //broard frame
        Bframe.setVisible(false);
        Bframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Bframe.setSize(610,720);
        Bframe.add(object);
        object.addMouseListener(new AL());
 
        //adding component to board
        object.setLayout(null);
        object.add(reset);
        object.add(undo);
        object.add(challenge);
        reset.setBounds(445, 600,100, 50);
        reset.addActionListener(new ButtonListener());
        undo.setBounds(50, 600,100, 50);
        undo.addActionListener(new ButtonListener());
        challenge.setBounds(250, 600,100, 50);
        challenge.addActionListener(new ButtonListener());  
        object.add(p_name);
        p_name.setLocation(250,640);
        p_name.setSize(200,50);
      
        
        //welcome frame
        Wframe.setVisible(true);
        Wframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Wframe.setSize(500,300);
        object2.setLayout(new BorderLayout());
        JPanel panel = new JPanel();

        
        panel.setLayout(null);
        JLabel label1 = new JLabel("May I have your name?"); 
        JLabel label2 = new JLabel("Name : ");

        
        //add items to welcome frame
        Wframe.add(panel);
        panel.add(label1);
        panel.add(label2);
        panel.add(text);
        panel.add(Ok);
        panel.add(first);
        
        label1.setLocation(180,20);
        label1.setSize(200,50);
        label2.setLocation(130,70);
        label2.setSize(150,50);
        text.setLocation(200,87);
        text.setSize(150, 20);
        Ok.setLocation(270,180);
        Ok.setSize(80,40);
        first.setLocation(150,180);
        first.setSize(80,40);  
                
        first.addActionListener(new ButtonListener());
        Ok.addActionListener(new ButtonListener());
        
        

        
    }
    

    

    //player's move
    private static int player_move(int x, int y,board current,board old1)
    {
        // check if the piece is valid
        Check check_p = new Check();
        if (check_p.check_valid(current,x,y)==0)
        {   //copy to old board to save for redo
            if (old1.board==null)
            {               
                old1.board = new int[19][19];
            }

            for (int i=0;i<19;i++)
            {
                for (int j=0;j<19;j++)
                {
                    old1.board[i][j]=current.board[i][j];
                }
                
            }                
            

            
            //write on board
            current.board[y][x]=1;
            return 1;
        }
        else
        {   //if the piece location is invalid, pop up invalid
            JOptionPane.showMessageDialog(null, "INVALID LOCATION");
        }
        return 0;
    }
    
    
    private static void computer_move(board current)
    {
        Brain com = new Brain();
        int decision[]=new int[100];
        //allow challenge mode
        if (chall_switch==1)
        {
            decision = com.brain(current,"challenge");
        }
        else
        {
            decision = com.brain(current);
        }
        //write on board
        current.board[decision[1]][decision[0]]=2;

    }
    
    private static void undo_P()
    {   //maximum of 1 undo
        if (old1.board == null)
        {
            JOptionPane.showMessageDialog(null, "NO MORE UNDO OPTION");
        }
        else
        {
            current.board=old1.board;
            old1.board=null;
            object.repaint();
        }

    }
    //mouse adapter
    static class AL extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {   //get x and y of mouse 
            x=e.getX();
            y=e.getY();
            
            // change the value of x and y to the coordinate of the grid
            x=(x-15)/30;
            y=(y-15)/30;    
            

            //calls the player's move
            int p = player_move(x,y,current,old1);                
            
            if (p==1)
            {           //refresh the interface
            object.repaint();
            //check win codition
            if (player.winlose(current, 1, count_pieces.own_pieces(current, 1))==0)
            {
                JOptionPane.showMessageDialog(null, "You win");
                
            }
            
            //calls computer move
            computer_move(current);
            //refresh the interface
            object.repaint();
            //check win codition
            if (player.winlose(current, 2, count_pieces.own_pieces(current, 2))==0)  
            {
                JOptionPane.showMessageDialog(null, "Game over");
                
            }
            }
        }
    }
    
        private static class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   //allow player enter name and enter the game
            if (e.getSource()== Ok)
            {
                Bframe.setVisible(true);
                Wframe.setVisible(false);
                name=text.getText();
                p_name.setText(name+"'s turn");
                current.startboard(player_start);
                chall_switch=0;
            }
            else if (e.getSource()==reset)
            {
                //allows the player to reset the board
                current.startboard(player_start);
                old1.board=null;
                object.repaint();
            }
            else if (e.getSource()==undo)
            {
                //allows the player to undo once
                undo_P();
            }
            else if (e.getSource()==challenge)
            {
                //challenge mode toggel
                if (chall_switch==0)
                {
                    chall_switch=1;
                    JOptionPane.showMessageDialog(null, "CHALLENGE MODE ON");
                }
                else
                {
                    chall_switch=0;
                    JOptionPane.showMessageDialog(null, "CHALLENGE MODE OFF");
                }
            }
            else if (e.getSource()==first)
            {
                //player can choose if they want to start first
                if(player_start==1)
                {
                    player_start=2;
                    first.setText("Second");
                }
                else
                {
                    player_start=1;
                    first.setText("First");
                } 
            }
        }
    }
        // for creating jpane 
        public static class normal extends JPanel
        {
            
        }
    //draw the board
    public static class draw extends JPanel{
    private static int x,y;

    
    //allow the x and y value to be passed to paint
    public void drawing (int xx,int yy)
    {   
        x=xx;
        y=yy;

        repaint();
    }
    
    public void paintComponent(Graphics g)
    {   
        super.paintComponent(g);
        //set background color to wooden colour
        setBackground(new Color(193, 123, 65));
        //draw the pieces
        for (int i=0;i<19;i++)
        {
            for (int j=0;j<19;j++)
            {   
                if (current.board[i][j]==1)
                {
                    g.setColor(Color.black);
                    g.fillOval(j*30+15,i*30+15,20,20);
                }
                else if (current.board[i][j]==2)
                {
                    g.setColor(Color.white);
                    g.fillOval(j*30+15,i*30+15,20,20);
                }
                
                
            }
        }
        //drawing the lines for the base 
        for (int i=0;i<20;i++)
        {
            g.setColor(Color.black);
            g.drawLine(i*30+10, 10, i*30+10, 580);
            g.drawLine(10, i*30+10, 580 , i*30+10);
        }
        

    }
    
}
   
}
    



