/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class run {
    public static void main(String args[])
    {
                position p;
		boolean player = true;
		String input;
		prunning comp = new prunning(5);
                char[][] a = new char[8][8];
		Board board = new Board(new char[8][8]);
		Scanner sc = new Scanner(System.in);
                board.printBoard();
		
		while (board.countEmptySpot() > 0) {
			if (player) {
				//player move
				System.out.print("Choose move: ");
				input = sc.nextLine();
				while (board.occupied(input)) {
					System.out.print("Space Occupied. Try Again: ");
					input = sc.nextLine();
				}
                                board.move(input, player);
			} else {
				//ai move
				p = comp.alphabeta(board);
				board.putX(p.x, p.y);
				System.out.println("Computer put in ("+p.x+","+p.y+")");
			}
			if (player)
				player = false;
			else
				player = true;
			board.printBoard();
			if (board.Xwin()||board.Owin())
				break;
		}
		if(board.Xwin())
			System.out.println("COMPUTER WINS");
		else if(board.Owin())
			System.out.println("YOU WIN");
                else 
			System.out.println("DRAW");
                 System.out.println(board.countEmptySpot());
	}
    }

