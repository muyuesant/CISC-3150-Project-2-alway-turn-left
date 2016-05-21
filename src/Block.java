
public class Block {
	public boolean walk;
	public boolean pWalk = false;
	public boolean sWalk = false;
	public String s;//e,w,s,n 0000
	
	public Block(){
		walk = false;
		s ="0000";
	}
	public void reset(){
		s = "0000";
		walk = false;
	}
	public void print(){
		if(walk){
			int tem = Integer.valueOf(s, 2);
			if(tem >9)
				System.out.print( (char)('a' + (tem - 10) ) +" " );
			else
				System.out.print(tem+" ");
		}
	}
	public String getS(){
		return s;
	}
	public void setS(String s){
		this.s = s;
	}
	
}
