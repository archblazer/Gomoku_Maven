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
public class count_values extends Brain {
    //count the value of each location
    public int count_values(board current,int x, int y,int player)
    {   
        int value=0;
        
        value += count_h(current,x,y,player);
        value += count_v(current,x,y,player);
        value += count_d1(current,x,y,player);
        value += count_d2(current,x,y,player);
        
        return value;
    }
    
    //count horizontal 
    private int count_h(board current,int x, int y,int player)
    {   
        int temp=0;
        
        
        for (int position=1;position < 5;position++)
        {   
            temp += super.h(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.h(current, x, y, position, player)<=0)
            {
                break;
            }
        }
        
        for (int position=-1;position > -5;position--)
        {
            temp += super.h(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.h(current, x, y, position, player)<=0)
            {
                break;
            }
        }


        
        return count_temp(temp+1);
    }
    
    //count vertical
    private int count_v(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=1;position < 5;position++)
        {

            temp += super.v(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.v(current, x, y, position, player)<=0)
            {
                break;
            }
        }
        
        for (int position=-1;position > -5;position--)
        {
            temp += super.v(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.v(current, x, y, position, player)<0)
            {
                break;
            }
        }
        
        return count_temp(temp+1);
    }
    
    //count diagonal1
    private int count_d1(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=1;position < 5;position++)
        {

            temp += super.d1(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.d1(current, x, y, position, player)<0)
            {
                break;
            }
        }
        
        for (int position=-1;position > -5;position--)
        {

            temp += super.d1(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.d1(current, x, y, position, player)<0)
            {
                break;
            }
        }
        
        return count_temp(temp+1);
    }
    
    //count diagonal2
    private int count_d2(board current,int x, int y,int player)
    {   
        int temp=0;
      
        for (int position=1;position < 5;position++)
        {
            temp += super.d2(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.d2(current, x, y, position, player)<0)
            {
                break;
            }
        }
        
        for (int position=-1;position > -5;position--)
        {           
            temp += super.d2(current, x, y, position, player);
            if (temp==4)
            {
                temp+=3;
            }
            if (super.d2(current, x, y, position, player)<0)
            {
                break;
            }
        }
      
        return count_temp(temp+1);
    }
    
   
    
    //count temp and return suitable values, prioritise higher count 
    private int count_temp(int temp)
    {
        if (temp>4)
        {
            return 100000;
        }
        else if (temp>=4)
        {
            return 10000;
        }
        else if (temp>=3)
        {
            return 1000;
        }
        else if (temp>=2)
        {
            return 100;
        }
        else if (temp>=1)
        {
            return 10;
        }
        else if (temp>=0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    
}
    
