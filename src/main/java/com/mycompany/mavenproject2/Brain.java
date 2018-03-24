/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.util.Random;

/**
 *
 * @author Guat Si Jie
 */
public class Brain 
{
    //normal ai
    public int[] brain(board current)
    {
        think easy = new think();
        possible_location player_pieces = new possible_location();
        
       // easy.
        if(easy.think(player_pieces,current)==0)
        {
            return attdef(current,1); 
        }
        else
        {
            return attdef(current,2);          
        }
     
    }
    //challenge ai
    public int[] brain(board current,String challenge)
    {

        int def[] = new int[3];
        def = attdef(current,1); 
        int att[] = new int [3]; 
        att = attdef(current,2); 
        
        if (def[2]>att[2])
        {
            return def;
        }
        else
        {
            return att;
        }        
    }    
    
    
    //counting horizontal pieces in a row
    public int h (board current, int x, int y,int position,int player)
    {
        x=x+position;
        
        if (x>18||x<0||y>18||y<0)
        {
            return -1;
        }
        
        if (current.board[y][x] == 0)
        {
            return 0;
        }
        else if (current.board[y][x] == player)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    
    //counting verticle pieces in a row
    public int v (board current, int x, int y,int position,int player)
    {
        y=y+position;
        
        if (x>18||x<0||y>18||y<0)
        {
            return 0;
        }
        
        if (current.board[y][x] == 0)
        {
            return 0;
        }
        else if (current.board[y][x] == player)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    // //counting diagonal pieces in a row (this way \\)
    public int d1 (board current, int x, int y,int position,int player)
    {
        x=x+position;
        y=y-position;
        
        if (x>18||x<0||y>18||y<0)
        {
            return 0;
        }
        
        
        if (current.board[y][x] == 0)
        {
            return 0;
        }
        else if (current.board[y][x] == player)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }    
    
        // counting horizontal pieces in a row this way //
    public int d2(board current, int x, int y,int position,int player)
    {
        x=x+position;
        y=y+position;
        
        if (x>18||x<0||y>18||y<0)
        {
            return 0;
        }
        
        if (current.board[y][x] == 0)
        {
            return 0;
        }
        else if (current.board[y][x] == player)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    } 
    

    //if player is 1, then computer def, else computer attack
    private int[] attdef(board current,int player)
    {   
        int temp[][]=new int[100][100];
        possible_location attack = new possible_location();
        count_values attack_v = new count_values();
        
        //get good location of possible pieces
        temp=attack.list_loc(current,player);       
    
        //counting values for each possible pieces
        for (int i=0;i <= temp[99][99];i++ )
        {
            temp[i][2]=attack_v.count_values(current,temp[i][0],temp[i][1],player);            
        }
        
        
        
        //error handling, if there is no possible location, the computer decide a new location
        if (temp[99][99]<0)
        {
            temp[99][99] = 0;
            int i=8,j=8;
            
            while (current.board[i][j]!=0)
            {
                     j++;                    
                 
                 if ((current.board[i][j]==0))
                 {
                     i++;
                 }
            } 
            
            temp[0][0]=j;
            temp[0][1]=i;
        }
        
        
        int max=0,max_V=0;
        //find the best value
        for (int i=0;i<=temp[99][99];i++)
        {
            if (max<temp[i][2])
            {
                max=temp[i][2];
                max_V=i;
            }
            else if (max==temp[i][2])
            {   //if there is same highest value, the computer will random
                Random random = new Random();
                if (random.nextInt(2)==0)
                {
                    max=temp[i][2];
                    max_V=i;                    
                }
            }

        }   
      
        //return computer's descision
        int descision[] = {temp[max_V][0],temp[max_V][1],temp[max_V][2]};
        return descision;
        
        
    }
    
}

    
    

