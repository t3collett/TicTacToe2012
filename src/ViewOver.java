/*
 * Created by Torstein Collett 2012
 * 
 * This class creates the Game over JOptionPane
 */
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ViewOver{
	public ViewOver(Model board){
		int x = 9;
		if(board.threeInARow(board.X)){
			x = JOptionPane.showOptionDialog(null, "X Wins do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Pic/x.gif"), null, null);
			}else if(board.threeInARow(board.O)){
			x = JOptionPane.showOptionDialog(null, "O Wins do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Pic/o.gif"), null, null);
		}else{
			x = JOptionPane.showOptionDialog(null, "cat's game. do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Pic/empty.gif"), null, null);
		}
		if(x == 1){
			System.exit(0);
		}else{
			board.reset();
		}
	}
}
