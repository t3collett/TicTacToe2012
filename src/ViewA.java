/*
 * Created by Torstein Collett 2012
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
public class ViewA {
	private JFrame frame;
	private JLabel label;
	private DButton button;
	private ImageIcon empty = new ImageIcon("Pic/empty.gif");
	private ImageIcon x = new ImageIcon("Pic/x.gif");
	private ImageIcon o = new ImageIcon("Pic/o.gif");

	public ViewA (Model model, Controller controller) {

		Action r = new MyAction("Restart",new Integer(KeyEvent.VK_R),KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK),model);

		frame = new JFrame("Othello");
		frame.setResizable(false);
		Container contentPane = frame.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(model.getSize(),model.getSize(), 5,5));
		JMenuBar jmb = new JMenuBar();
		JMenu fileM = new JMenu("Game");
		fileM.add(r);
		frame.setJMenuBar(jmb);
		jmb.add(fileM);
		for(int row = 0 ; row < model.getSize() ; row++){
			for(int col = 0 ; col < model.getSize() ; col++){
				if(model.getTile(row, col) != model.EMPTY){
					if(model.getTile(row, col) == model.X){
						button = new DButton(x,row,col,model);
					}else if(model.getTile(row, col) == model.O){
						button = new DButton(o,row,col,model);
					}
				}else{
					button = new DButton(empty,row,col, model);
				}
				Insets inset = new Insets(0,0,0,0);
				button.setMargin(inset);
				panel.add(button);
				button.addActionListener(controller);
			}
		}
		label = new MyLabel("      X's turn ", model);
		jmb.add(label);
		JPanel group = new JPanel();
		group.setLayout(new BorderLayout());
		group.add(panel,BorderLayout.WEST);
		contentPane.add(group);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}