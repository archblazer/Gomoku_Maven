/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Guat Si Jie
 */
public class draw extends JPanel{
    private static int x,y;

    
    
    public void drawing (int xx,int yy)
    {   
        x=xx;
        y=yy;
        repaint();
    }
    
    public void paintComponent(Graphics g)
    {   
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillOval(x,y,20,20); 

        

    }
    
}
