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
public class Check {
    //check if the board is occupied
    public int check_valid(board current,int x,int y)
    {
        if (current.board[y][x]==0)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
}
