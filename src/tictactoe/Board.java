/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Board {
    char[][] board;
    int size;
    
    public Board(char[][] board)
    {
        this.board = board;
        size = board.length;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                board[i][j]='-';
            }        
        }    
    }
    public void move(String input, boolean player)
    {
        char c = input.charAt(0);
        int a = c-65;
        int b = Character.getNumericValue(input.charAt(1));
        
        if(player)
            putO(a,b);
        else
            putX(a,b);
    }
    public boolean occupied(String input)
    {
        char c = input.charAt(0);
        int a = c-65;
        int b = Character.getNumericValue(input.charAt(1));
            if(a>7||a<0||b>7||b<0)
                return false;
        if(board[a][b]=='-')
            return false;
        else 
            return true;
    }
    public void printBoard()
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public int countEmptySpot()
    {
        int count =0; 
       for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(board[i][j]=='-')
                    count++;
            }
        }
       return count;
    }
    public void putO(int x , int y)
    {
        board[x][y] = 'O';
    }
    public void putX(int x, int y)
    {
        board[x][y] = 'X';
    }
    public void remove(int x,int y)
    {
        board[x][y] ='-';
    }
    public boolean validPosition(int x,int y)
    {
        if(x<size&&x>=0&&y<size&&y>=0)
        {
            return true;
        }
    return false;
    }
    public boolean isO(int x,int y)
    {
        if(board[x][y]=='O')
            return true;
        return false;
    }
    public boolean isX(int x,int y)
    {
        if(board[x][y]=='X')
            return true;
        return false;
    }
    
    public boolean Owin()
    {
        for(int x=0;x<board.length;x++)
        {
            for(int y=0;y<board.length;y++)
            {
                if(board[x][y]=='O')
                {
                    if(validPosition(x+1,y)&&validPosition(x+2,y)&&validPosition(x+3,y))
                        if(isO(x+1,y)&&isO(x+2,y)&&isO(x+3,y))
                            return true;
                    if(validPosition(x-1,y)&&validPosition(x-2,y)&&validPosition(x-3,y))
                        if(isO(x-1,y)&&isO(x-2,y)&&isO(x-3,y))
                            return true;
                    if(validPosition(x,y-1)&&validPosition(x,y-2)&&validPosition(x,y-3))
                        if(isO(x,y-1)&&isO(x,y-2)&&isO(x,y-3))
                            return true;
                    if(validPosition(x,y+1)&&validPosition(x,y+2)&&validPosition(x,y+3))
                            if(isO(x,y+1)&&isO(x,y+2)&&isO(x,y+3))
                            return true;
                }
            }
        }
        return false;
    }
    public boolean Xwin()
    {
        for(int x=0;x<board.length;x++)
        {
            for(int y=0;y<board.length;y++)
            {
                if(board[x][y]=='X')
                {
                    if(validPosition(x+1,y)&&validPosition(x+2,y)&&validPosition(x+3,y))
                        if(isX(x+1,y)&&isX(x+2,y)&&isX(x+3,y))
                            return true;
                    if(validPosition(x-1,y)&&validPosition(x-2,y)&&validPosition(x-3,y))
                        if(isX(x-1,y)&&isX(x-2,y)&&isX(x-3,y))
                            return true;
                    if(validPosition(x,y-1)&&validPosition(x,y-2)&&validPosition(x,y-3))
                        if(isX(x,y-1)&&isX(x,y-2)&&isX(x,y-3))
                            return true;
                    if(validPosition(x,y+1)&&validPosition(x,y+2)&&validPosition(x,y+3))
                            if(isX(x,y+1)&&isX(x,y+2)&&isX(x,y+3))
                            return true;
                }
            }
        }
        return false;
    }
    
    public boolean Xwin(int x, int y)
    {
        if(validPosition(x+1,y)&&validPosition(x+2,y)&&validPosition(x+3,y))
                        if(isX(x+1,y)&&isX(x+2,y)&&isX(x+3,y))
                            return true;
                    if(validPosition(x-1,y)&&validPosition(x-2,y)&&validPosition(x-3,y))
                        if(isX(x-1,y)&&isX(x-2,y)&&isX(x-3,y))
                            return true;
                    if(validPosition(x,y-1)&&validPosition(x,y-2)&&validPosition(x,y-3))
                        if(isX(x,y-1)&&isX(x,y-2)&&isX(x,y-3))
                            return true;
                    if(validPosition(x,y+1)&&validPosition(x,y+2)&&validPosition(x,y+3))
                            if(isX(x,y+1)&&isX(x,y+2)&&isX(x,y+3))
                            return true;
          return false;
    }
    public static void main(String args[])
    {
    
    }
}
