/*
 * Created by Torstein Collett 2012
 * 
 * This class creates the buttons used
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DButton extends JButton implements Dimensioned, ChangeListener{
	private int row;
	private int col;
	private Model model;
	private ImageIcon empty = new ImageIcon("Pic/empty.gif");
	private ImageIcon x = new ImageIcon("Pic/x.gif");
	private ImageIcon o = new ImageIcon("Pic/o.gif");


	public DButton(ImageIcon img, int row, int col,Model model){
		super(img);
		this.row = row;
		this.col = col;
		this.model = model;
		this.model.addChangeListener(this);
	}
	//used to let the controller know where the button is
	@Override
	public int getRow() {
		return this.row;
	}
	@Override
	public int getCol() {
		return this.col;
	}
	//changes the piece to the correct color
	@Override
	public void stateChanged(ChangeEvent arg0) {
		Tile tile;
		tile = model.getTile(row, col);
		if(tile != model.EMPTY){
			if(tile == model.X){
				this.setIcon(x);
			}else if(tile == model.O){
				this.setIcon(o);
			}
		}else{
			this.setIcon(empty);

		}
	}

}
