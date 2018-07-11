/*
 * Created by Torstein Collett 2012
 * 
 * This class is the engine that runs the game
 */
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;


import javax.swing.event.ChangeListener;


public class Model {
	public final Tile X = new XTile();
	public final Tile O = new OTile();
	public final Tile EMPTY = new EmptyTile();
	public int inARow = 0;
	private Tile turn = X;
	private Tile[][] tile;
	public Tile player;
	private Collection<ChangeListener> cL = new HashSet<ChangeListener>();
	/**
	 * creates board 
	 * 
	 */
	public Model(int size){
		if(size%2 != 0){
			if(size < 20){
				tile = new Tile[size][size];
				reset();
			}else{
			}
		}else{
		}
	}
	/**
	 * resets the board to the start specification
	 */
	public void reset(){
		for(int col = 0 ; col < getSize() ; col++){
			for(int row = 0 ; row < getSize() ; row++){
				tile[row][col] = EMPTY;
			}
		}
		this.turn = X;
		notifyChangeListeners();
	}
	/**
	 * prints out the board
	 */
	public String toString(){
		String answer = "";
		for(int row = 0 ; row < getSize() ; row++){
			for(int col = 0 ; col < getSize() ; col++){
				if(tile[row][col] != EMPTY){
					if(tile[row][col] == X){
						answer = answer + " X";
					}else if(tile[row][col] == O){
						answer = answer + " O";
					}
				}else{
					answer = answer + " _";
				}
			}
			answer = answer + "\n";
		}

		return answer;
	}

	public void addChangeListener(ChangeListener cl){
		cL.add(cl);
	}
	public void removeChangeListener(ChangeListener cl){
		cL.remove(cl);
	}
	public void notifyChangeListeners(){
		Iterator<ChangeListener> iterator = cL.iterator();
		while(iterator.hasNext()){
			ChangeListener cl = iterator.next();
			cl.stateChanged(null);
		}
	}
	public int getSize(){
		return tile.length;
	}
	public Tile getTile(int row, int col){
		return tile[row][col];
	}
	public Tile getTurn(){
		return turn;
	}
	/*
	 * returns true if the game is over.
	 */
	public boolean isOver(){
		for(int row = 0 ; row < getSize() ; row++){
			for(int col = 0 ; col < getSize() ; col++){
				if(moveIsLegal(row, col)){
					return false;
				}
			}
		}
		takeTurn();
		for(int row = 0 ; row < getSize() ; row++){
			for(int col = 0 ; col < getSize() ; col++){
				if(moveIsLegal(row, col)){
					return false;
				}
			}
		}
		return true;
	}
	public boolean threeInARow(Tile type){
		for(int row = 0 ; row < getSize() ; row++){
			for(int col = 0 ; col < getSize() ; col++){
				checkDirection(row, col, -1,0, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, -1, 1, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, -1, -1, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, 0, 1, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, 1, 0, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, 1, 1, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, 1, -1, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
				checkDirection(row, col, 0, -1, type);
				if(inARow == 3){
					inARow = 0;
					return true;
				}
				inARow = 0;
			}
		}

		return false;
	}
	public boolean checkDirection(int row, int col, int dirX, int dirY, Tile type) {
		try{
			if(tile[row][col] == type){
				row += dirX;
				col += dirY;
				if (checkDirection(row, col, dirX, dirY, type)){
					inARow += 1;
					return true;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			if(tile[row - dirX][col - dirY] == type){
				return true;
			}
			return false;
		}
		return false;
	}
	/**
	 *  changes which player's turn it is
	 */
	public void takeTurn(){
		if(turn == X){
			turn = O;
		}else if(turn == O){
			turn = X;
		}
		notifyChangeListeners();
	}
	/*
	 *puts a piece on the board 
	 */
	public void occupy(int row, int col, Tile player){
		tile[row][col] = player;
	}
	/*
	 * ====================checks to see if the move is legal============================
	 */
	public boolean moveIsLegal(int row,int col){
		if(tile[row][col] == EMPTY){
			return true;
		}
		return false;
	}
	//is called when pieces are placed
	public void placePiece(int row, int col){
		if(tile[row][col] == EMPTY && moveIsLegal(row, col)){
			this.moveIsLegal(row, col);
			this.occupy(row, col, getTurn());
			notifyChangeListeners();
			if(threeInARow(turn) || isOver()){
				ViewOver vo = new ViewOver(this);
			}else{
			takeTurn();
			}
		}else{
			playNote();
		}
	}

	public void playNote(){
		int	nNoteNumber = 47;	// MIDI key number
		int	nVelocity = 100;	// MIDI note on velocity
		int	nDuration = 250;

		Synthesizer	synth = null;
		try {
			synth = MidiSystem.getSynthesizer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		try {
			synth.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}

		MidiChannel[]	channels = synth.getChannels();
		channels[0].noteOn(nNoteNumber, nVelocity);
		try{
			Thread.sleep(nDuration);
		}catch (InterruptedException e){

		}
		channels[0].noteOff(nNoteNumber);
	}


}
