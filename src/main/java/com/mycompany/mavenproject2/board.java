/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author Guat Si Jie
 */
public class board {
    
    public int board[][]=new int [19][19]; 

    //clear or start the board
    public void startboard(int player){
        
        for (int i=0;i<19;i++)
        {
            for (int j=0;j<19;j++)
            {
                board[i][j]=0;  
            }
            
            
        }
        //always make the first piece at middle if computer first
        if (player == 2)
            {
                board[9][9]=2;
            }
    }
    
}
