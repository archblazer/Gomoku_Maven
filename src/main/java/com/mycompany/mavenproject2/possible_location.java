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
public class possible_location extends Brain {
    
    
    //find the pieces of the computer or player
    public int[][] own_pieces(board current,int player)
    {
       int own_pieces[][] = new int[100][100],n=0;
        
        for (int i=0;i<19;i++)
        {
            for (int j=0;j<19;j++)
            {
                if(current.board[i][j]==player)
                {
                    own_pieces[n][0]=j;
                    own_pieces[n][1]=i;
                    
                    n++;
                }
            }

        }
        
       own_pieces[99][99]=n-1;
       
       
       return own_pieces;
         
    }
    
    //find the possible pieces of the computer or player
    public int[][] list_loc(board current,int player)
    {
        int own_pieces[][] = new int[100][100];
        own_pieces = own_pieces(current,player);
       
        
        //get all possible location including overlaps
        for (int i=0;i<=own_pieces[99][99];i++)
        {   //horizontal
            for (int position=-1;position<2&&(own_pieces[i][0]+position)<19&&(own_pieces[i][0]+position)>=0;position++)
            {
                if (super.h(current, own_pieces[i][0], own_pieces[i][1], position, player)==0)
                {
                    check_duplicate(own_pieces[i][0]+position, own_pieces[i][1], current);
                }
            }
                
            //verticle
            for (int position=-1;position<2&&(own_pieces[i][1]+position)<19&&(own_pieces[i][1]+position)>=0;position++)
            {
                if (super.v(current, own_pieces[i][0], own_pieces[i][1], position, player)==0)
                {
                    check_duplicate(own_pieces[i][0], own_pieces[i][1]+position, current);
                }
            }
            
            //diagonal1 \
            for (int position=-1;   
                    position<2&&
                    (own_pieces[i][0]+position)<19&&
                    (own_pieces[i][0]+position)>=0&&
                    (own_pieces[i][1]-position)<19&&
                    (own_pieces[i][1]-position)>=0;
                    position++)
            {
                if (super.d1(current, own_pieces[i][0], own_pieces[i][1], position, player)==0)
                {
                    check_duplicate(own_pieces[i][0]+position, own_pieces[i][1]-position, current);
                }
            }
            
            //diagonal2 /
            for (int position=-1;   
                    position<2&&
                    (own_pieces[i][0]+position)<19&&
                    (own_pieces[i][0]+position)>=0&&
                    (own_pieces[i][1]+position)<19&&
                    (own_pieces[i][1]+position)>=0;
                    position++)
            {
                if (super.d2(current, own_pieces[i][0], own_pieces[i][1], position, player)==0)
                {
                    check_duplicate(own_pieces[i][0]+position, own_pieces[i][1]+position, current);
                }
            }
        }
        
        possible_loc[99][99]=count-1;
        
        
        return possible_loc;
    }

    //check if there is duplicate and drop the duplicates
    private int count=0;
    private int[][] possible_loc = new int[100][100];
    private void check_duplicate(int x, int y, board current)
    {
        if (count==0)
        {
            possible_loc[count][0]=x;
            possible_loc[count][1]=y;
            count++;               
        }    
        else
        {    
            for (int i=0;i<count;i++)
            {
                
                if ((possible_loc[i][0]==x&&possible_loc[i][1]==y)||current.board[y][x]!=0)
                {
                    break;
                }
                if (i==count-1)
                {
                    possible_loc[count][0]=x;
                    possible_loc[count][1]=y;
                    count++;
                }
                
            }
        }

    }

}


