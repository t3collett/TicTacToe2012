/* Created by Torstein Collett 2012
 * 
 * action listener for my DButtons
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener{
	private Model m;

	public Controller(Model model) {
		this.m = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DButton hasD = (DButton) e.getSource();
		int row = hasD.getRow();
		int col = hasD.getCol();
		m.placePiece(row, col);
	}
}