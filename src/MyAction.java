/*
 * Created by Torstein Collett 2012
 * 
 * used to make save, load, hint, new game, and restart
 */
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;


public class MyAction extends AbstractAction{
	String name;
	Model m;
	public MyAction(String name, Integer mnemonic, KeyStroke accelerator,Model m){
		super(name,null);
		putValue(Action.MNEMONIC_KEY, mnemonic);
		putValue(Action.ACCELERATOR_KEY, accelerator);
		this.name = name;
		this.m = m;
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(name.equals("Restart")){
			m.reset();
		}			
			
	}

}
