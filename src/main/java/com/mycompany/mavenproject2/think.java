/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.possible_location;
import com.mycompany.mavenproject2.board;
import com.mycompany.mavenproject2.Brain;

/**
 *
 * @author Guat Si Jie
 */
public class think extends Brain {
    //to decide if the computer wants to attack or defence
    public int think (possible_location p1, board current)
    {   
        int player_pieces[][]=new int[100][100];
        int count;
        
        //player piece is 1
         player_pieces = p1.own_pieces(current, 1);
         
         //if one of the player's pieces are formed in 3 or more, the ai will automatically in defence mode
         for (int i=0;i<=player_pieces[99][99];i++)
         {  
            count=0;
            count = count_h(current,player_pieces[i][0],player_pieces[i][1],1);
            if (count>=3)
            {
                return 0;
            }
            count = count_v(current,player_pieces[i][0],player_pieces[i][1],1);
            if (count>=3)
            {
                return 0;
            }
            count = count_d1(current,player_pieces[i][0],player_pieces[i][1],1);
            if (count>=3)
            {
                return 0;
            }
            count = count_d2(current,player_pieces[i][0],player_pieces[i][1],1);
            if (count>=3)
            {
                return 0;
            }            

         }
         
         
        return 1;
         
         
    }
    //counting horizontal pieces in a row
        private int count_h(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=0;position < 5;position++)
        {
            temp += super.h(current, x, y, position, player);
            if (super.h(current, x, y, position, player)==0&&super.h(current, x, y, position+1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.h(current, x, y, position, player)<0)
            {

                break;
            }

        }
        
        for (int position=-1;position > -5;position--)
        {
            temp += super.h(current, x, y, position, player);
            if (super.h(current, x, y, position, player)==0&&super.h(current, x, y, position-1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.h(current, x, y, position, player)<0)
            {

                break;
            }
            
        }
        
        return temp;
    }
    
    //counting verticle pieces in a row
    private int count_v(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=0;position < 5;position++)
        {
            temp += super.v(current, x, y, position, player);
            if (super.v(current, x, y, position, player)==0&&super.v(current, x, y, position+1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.v(current, x, y, position, player)<0)
            {

                break;
            }
            
        }
        
        for (int position=-1;position > -5;position--)
        {
            temp += super.v(current, x, y, position, player);
            if (super.v(current, x, y, position, player)==0&&super.v(current, x, y, position-1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.v(current, x, y, position, player)<0)
            {

                break;
            }
            
        }
        
        return temp;
    }
    
    //counting diagonal 1 (\\) pieces in a row1
    private int count_d1(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=0;position < 5;position++)
        {
            temp += super.d1(current, x, y, position, player);
            if (super.d1(current, x, y, position, player)==0&&super.d1(current, x, y, position+1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.d1(current, x, y, position, player)<0)
            {

                break;
            }
            
        }
        
        for (int position=-1;position > -5;position--)
        {
            temp += super.d1(current, x, y, position, player);
            if (super.d1(current, x, y, position, player)==0&&super.d1(current, x, y, position-1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.d1(current, x, y, position, player)<0)
            {

                break;
            }
            
        }
        
        return temp;
    }
    
    //counting diagonal2 (//) pieces in a row
    private int count_d2(board current,int x, int y,int player)
    {   
        int temp=0;
        
        for (int position=0;position < 5;position++)
        {
            temp += super.d2(current, x, y, position, player);
            if (super.d2(current, x, y, position, player)==0&&super.d2(current, x, y, position+1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.d2(current, x, y, position, player)<0)
            {

                break;
            }
            
        }
        
        for (int position=-1;position > -5;position--)
        {
            temp += super.d2(current, x, y, position, player);
            if (super.d2(current, x, y, position, player)==0&&super.d2(current, x, y, position-1, player)==1)
            {
                temp += 1;
                break;
            }
            else if (super.d2(current, x, y, position, player)<0)
            {
                break;
            }
            
        }
        
        return temp;
    }


    

}
