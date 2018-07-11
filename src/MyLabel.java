/*
 * Created by Torstein Collett 2012
 * 
 * This class keeps score and says whose turn it is
 */
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MyLabel extends JLabel implements ChangeListener {
	private Model model;

	public MyLabel(String label, Model model) {
		super(label);
		this.model = model;
		this.model.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		String turn = "";
		if(model.getTurn() == model.X){
			turn = "  X's";
		}else if(model.getTurn() == model.O){
			turn = "  O's";
		}
		this.setText("      "+turn +" turn ");
	}
}