
public class GUITicTacToe {
	public static void main(String[] args) {
		Model m;
		m = new Model(3);
		Controller c = new Controller(m);
		ViewA v = new ViewA(m,c);
	}
}
