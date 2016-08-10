/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

/**
 *
 * @author user
 */
public class prunning {
    
    private int alpha= Integer.MIN_VALUE;
    private int beta= Integer.MAX_VALUE;
    private long start;
    private final long lim;
    private final int depth;
    
    public prunning(int lim)
    {
        this.lim = (long) (lim * 1000);
	this.depth = lim*100;
    }
    public position alphabeta(Board board) {
		return alphabeta(board, depth);
	}
    
    public position alphabeta(Board board, int d) {
		int score;
		int maxi = 0;
		int maxj = 0;
		int best = alpha;
                int size = board.board.length;
		start = System.currentTimeMillis();
                
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board.board[i][j] == '-') 
				{
                                        board.putX(i,j);
					score = minValue(board, d - 1);
					board.remove(i, j); 
					if (score > best) {
						maxi = i;
						maxj = j;
						best = score;
					}
				} 
			}
		}
		return new position(maxi, maxj);
	}
    private int maxValue(Board board, int d) {
		if (board.Xwin())
			return beta / 2;
		if (board.Owin())
			return alpha / 2;
		if (board.countEmptySpot() == 0)
			return 0;
		if (System.currentTimeMillis() - start > lim || d <= 0)
			return scoring(board);
	
		int best = alpha;
		for (int i = 0; i < board.board.length; i++) {
			for (int j = 0; j < board.board.length; j++) {
				if (board.board[i][j] == '-') {
					board.putO(i,j);
					best = Math.max(best, minValue(board, d - 1));
					board.remove(i, j); // undo move

				}
			}
		}
		return best;
	}
    private int minValue(Board board, int d) {
                int size = board.board.length;
		if (board.Xwin())
			return beta / 2;
		if (board.Owin())
			return alpha / 2;
		if (board.countEmptySpot() == 0)
			return 0;
		if (System.currentTimeMillis() - start>lim|| d <= 0)
			return scoring(board);
		
		int best = beta;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board.board[i][j] == '-') {
					board.putX(i, j);
					best = Math.min(best,maxValue(board,d-1));
					board.remove(i, j);
				}
			}
		}
		return best;
	}
    
    private int scoring(Board board) 
	{
		int score = 0;
		int comp = 0;
		int human = 0;
                int size = board.board.length;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				score += scoring(board, i, j);
		return score;
	}
    
    private int scoring(Board board, int i, int j) {
		int score = 0;
		int temp = 0;
                int size = board.board.length;
                
                
                if(board.board[i][j]=='-')
                {
                   
                   if(board.validPosition(i+1, j)&&board.validPosition(i-1, j)&&board.board[i+1][j]=='X'&&board.board[i-1][j]=='X')
                       if(board.validPosition(i+2, j)&&board.validPosition(i-2, j)&&board.board[i+2][j]=='-'&&board.board[i-2][j]=='-')
                            score+=1000;
                   if(board.validPosition(i+1, j)&&board.validPosition(i+2, j)&&board.board[i+1][j]=='X'&&board.board[i+2][j]=='X')
                       if(board.validPosition(i-1, j)&&board.validPosition(i+3, j)&&board.board[i-1][j]=='-'&&board.board[i+3][j]=='-')
                            score+=1000;
                   if(board.validPosition(i-1, j)&&board.validPosition(i-2, j)&&board.board[i-1][j]=='X'&&board.board[i-2][j]=='X')
                       if(board.validPosition(i-3, j)&&board.validPosition(i+1, j)&&board.board[i-3][j]=='-'&&board.board[i+1][j]=='-')
                            score+=1000;
                   if(board.validPosition(i, j+1)&&board.validPosition(i, j-1)&&board.board[i][j+1]=='X'&&board.board[i][j-1]=='X')
                       if(board.validPosition(i, j+2)&&board.validPosition(i, j-2)&&board.board[i][j+2]=='-'&&board.board[i][j-2]=='-')
                            score+=1000;
                   if(board.validPosition(i, j+1)&&board.validPosition(i, j+2)&&board.board[i][j+1]=='X'&&board.board[i][j+2]=='X')
                       if(board.validPosition(i, j-1)&&board.validPosition(i, j+3)&&board.board[i][j-1]=='-'&&board.board[i][j+3]=='-')
                            score+=1000;
                   if(board.validPosition(i, j-1)&&board.validPosition(i, j-2)&&board.board[i][j-1]=='X'&&board.board[i][j-2]=='X')
                       if(board.validPosition(i, j-3)&&board.validPosition(i, j+1)&&board.board[i][j-3]=='-'&&board.board[i][j+1]=='-')
                            score+=1000;
                }
                
                if (board.board[i][j] == 'X') {
                        board.board[i][j] = 'O';
			if (board.Owin()) {
				score += 40001;
			}
			board.board[i][j] = 'X';
		}
		if (i >= 3) {
			for (int c = 1; c < 4; c++)
				if (board.board[i - c][j] == 'O')
					temp += 5 - c;
				else if (board.board[i - c][j] != '-') {
					temp = -1;
					c = 10;
				}
			if (i < size - 1)
				if (temp == 7 && board.board[i + 1][j] == '-')
					temp = 10000;
			if (temp == 9)
				temp = 9990;
			score += temp;
			temp = 0;
		} else
			score--;
		if (i < size - 3) {
			for (int c = 1; c < 4; c++)
				if (board.board[i + c][j] == 'O')
					temp += 5 - c;
				else if (board.board[i + c][j] != '-') {
					temp = -1;
					c = 10;
				}
			if (i > 0)
				if (temp == 7 && board.board[i - 1][j] == '-')
					temp = 10000;
			if (temp == 9)
				temp = 9990;
			score += temp;
			temp = 0;
		} else
			score--;
		if (j >= 3) {
			for (int c = 1; c < 4; c++)
				if (board.board[i][j - c] == 'O')
					temp += 5 - c;
				else if (board.board[i][j - c] != '-') {
					temp = -1;
					c = 10;
				}
			if (j < size - 1)
				if (temp == 7 && board.board[i][j + 1] == '-')
					temp = 10000;
			if (temp == 9)
				temp = 9990;
			score += temp;
			temp = 0;
		} else
			score--;
		if (j < size - 3) {
			for (int c = 1; c < 4; c++)
				if (board.board[i][j + c] == 'O')
					temp += 5 - c;
				else if (board.board[i][j + c] != '-') {
					temp = -1;
					c = 10;
				}
			if (j > 0)
				if (temp == 7 && board.board[i][j - 1] == '-')
					temp = 10000;
                        if (temp == 9)
                            temp = 9990;
                        score += temp;
                        temp = 0;
                        } 
                        else
                            score--;

			if (i >= 1 && i < size - 1 && j >= 1
					&& j < size - 1) {
				if (board.board[i + 1][j + 1] == 'O'
						|| board.board[i + 1][j - 1] == 'O'
						|| board.board[i - 1][j + 1] == 'O'
						|| board.board[i - 1][j - 1] == 'O')
					score++;
			}
                if(board.board[i][j]=='X')
                    return score;
                else
                    return -score;
	}
    public static void main(String args[])
    {
        Board b = new Board(new char[8][8]);
        
        b.move("A0", true);
        b.move("C4", true);
        b.move("D4", true);
        b.move("D2", true);
        b.move("D3", true);
        b.move("F2", true);
        b.move("C2", true);
        b.move("F3", true);
        b.putX(0,4);
        b.putX(2,3);
        b.putX(4,4);
        b.putX(3,5);
        b.putX(3,1);
        b.putX(6,2);
        b.putX(4,2);
        
        
        
        b.printBoard();
        prunning p = new prunning(5);
        position p2 = p.alphabeta(b);
        
        System.out.println(p2.x+" "+p2.y);
    }
}
