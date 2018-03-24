/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.board;
import com.mycompany.mavenproject2.Brain;

/**
 *
 * @author Guat Si Jie
 */
public class winlose extends Brain
{
    public int winlose (board current, int player, int own_pieces[][])
    {
        //check if there is there is any rows 5 in a row
        for (int n=0;n<=own_pieces[99][99];n++)
        {
            if (count_h(current,own_pieces[n][0],own_pieces[n][1],player)>=4)
            {
                return 0;
            }
            
            if (count_v(current,own_pieces[n][0],own_pieces[n][1],player)>=4)
            {
                return 0;
            }
            
            if (count_d1(current,own_pieces[n][0],own_pieces[n][1],player)>=4)
            {
                return 0;
            }
            
            if (count_d2(current,own_pieces[n][0],own_pieces[n][1],player)>=4)
            {
                return 0;
            }
                       
        }

        
        return 1;
    }
    //count horizontal
    private int count_h(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=1;position < 5;position++)
        {   
            if (super.h(current, x, y, position, player)<0)
            {
                break;
            }
            temp += super.h(current, x, y, position, player);

        }
        
        for (int position=-1;position < -5;position--)
        {
            if (super.h(current, x, y, position, player)<0)
            {
                break;
            }
            temp += super.h(current, x, y, position, player);

        }
        
        return temp;
    }
    
    //count vertical
    private int count_v(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=1;position < 5;position++)
        {
            if (super.v(current, x, y, position, player)<1)
            {
                break;
            }
            temp += super.v(current, x, y, position, player);

        }
        
        for (int position=-1;position < -5;position--)
        {
            if (super.v(current, x, y, position, player)<1)
            {
                break;
            }
            temp += super.v(current, x, y, position, player);

        }
        
        return temp;
    }
    
    //count diagonal1
    private int count_d1(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=1;position < 5;position++)
        {
            if (super.d1(current, x, y, position, player)<1)
            {
                break;
            }
            temp += super.d1(current, x, y, position, player);

        }
        
        for (int position=-1;position < -5;position--)
        {
            if (super.d1(current, x, y, position, player)<1)
            {
                break;
            }
            temp += super.d1(current, x, y, position, player);

        }
        
        return temp;
    }
    
    //count diagonal2
    private int count_d2(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=1;position < 5;position++)
        {
            if (super.d2(current, x, y, position, player)<1)
            {
                break;
            }
            temp += super.d2(current, x, y, position, player);

        }
        
        for (int position=-1;position < -5;position--)
        {
            if (super.d2(current, x, y, position, player)<1)
            {
                break;
            }
            temp += super.d2(current, x, y, position, player);

        }
        
        return temp;
    }

}
