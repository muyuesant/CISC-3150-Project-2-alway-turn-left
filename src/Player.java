import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Player  {
	public int x;
	public int y;
	private MazaCanvas myCan;
	public Block[][] playB;
	public Vector<Pair> path;
	
	public Player() {
		// TODO Auto-generated constructor stub
		x=0;
		y=0;
		path = new Vector<Pair>();
		//path = new ArrayList<Pair>();
	}
	public Player(int x, int y){
		this.x =x;
		this.y =y;
	}
	public void setCanvas(MazaCanvas Can){
		myCan = Can;
	}
	public void setBlock(Block[][] block){
		playB = block;
	}
	public void setXY(int x, int y){
		this.x =x;
		this.y= y;
	}

}
