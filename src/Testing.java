
public class Testing {
	
	public static Block[][] myB = new Block[50][50];
	private static int direct; // global variable to hold the direction. 0 90 180 270
	private static int maxbi,maxbj;// index help to determind the size of the maza
	private static Pair in,out;
	public static Player p;
	
	public Testing(){
		direct = 270;
		maxbi =0;
		maxbj =0;
		iniBlock(50,50);
		p = new Player();
	}
	
	public static void updateBlock(int dir, Block bl,String str){
		
		if(bl.s != str  && str != "0000"){
			if( bl.s == "0000")
				bl.s =str;
			else if(bl.s != "0000"){
			//	System.out.println(bl.s);
					
				char[] blArr = bl.s.toCharArray();
				StringBuilder sb = new StringBuilder(bl.s);
				for(int i =0; i< 4; i++)
					if( str.charAt(i) =='1')
						//blArr[i] = '1';
						sb.replace(i, i+1, "1");
				//bl.s = blArr.toString();
				bl.s = sb.toString();
			}
		}
		if(!bl.walk)
			bl.walk = true;
		direct = dir;
	}
	
		public static Pair turnlf(String s1, int r, int c){
		//int row = 50, col = 50;
		int i=0, bi=r, bj=c;
		char fi, se;
		
		while(i < s1.length()){
			p.path.add(new Pair(bi,bj));
			fi = s1.charAt(i);
			if(fi != 'W'){
				
				i++;	
				se = s1.charAt(i);
				if(fi == 'L' && se == 'W'){
					switch(direct) {
						case 0://90wn
							updateBlock(90, myB[bi][bj],"0101");
							bi--;
							break;
						case 90://180sw
							updateBlock(180, myB[bi][bj],"0110");
							bj--;
							break;
						case 180://270es
							updateBlock(270, myB[bi][bj],"1010");
							bi++;
							break;
						case 270://0ne
							updateBlock(0, myB[bi][bj],"1001");
							bj++;
							break;
					}
					maxbi = maxbi > bi ? maxbi : bi;
					maxbj = maxbj > bj ? maxbj : bj;
				}
				else if(fi == 'R' && se == 'W'){
					switch(direct) {
						case 0://270ws
							updateBlock(270, myB[bi][bj],"0110");
							bi++;
							break;
						case 90://0se
							updateBlock(0, myB[bi][bj],"1010");
							bj++;
							break;
						case 180://90en
							updateBlock(90, myB[bi][bj],"1001");
							bi--;
							break;
						case 270://180nw
							updateBlock(180, myB[bi][bj],"0101");
							bj--;
							break;
					}
					maxbi = maxbi > bi ? maxbi : bi;
					maxbj = maxbj > bj ? maxbj : bj;
				}
				else if(fi == 'R' && se == 'R'){
					switch(direct){//e,w,s,n;
						case 0://180w
							updateBlock(180, myB[bi][bj],"0100");
							bj--;
							break;
						case 90://270s
							updateBlock(270, myB[bi][bj],"0010");
							bi++;
							break;
						case 180://0e
							updateBlock(0, myB[bi][bj],"1000");
							bj++;
							break;
						case 270://90n
							updateBlock(90, myB[bi][bj],"0001");
							bi--;
							break;
					}//switch
					i++;//read one more step which must be 'W'	
					maxbi = maxbi > bi ? maxbi : bi;
					maxbj = maxbj > bj ? maxbj : bj;
				}
				//p.path.add(new Pair(bi,bj));
			}//first if
			
			else if(fi == 'W'){
				switch(direct){//e,w,s,n;
						case 0://
							updateBlock(0, myB[bi][bj],"1100");
							bj++;
							break;
						case 90://
							updateBlock(90, myB[bi][bj],"0011");
							bi--;
							break;
						case 180://
							updateBlock(180, myB[bi][bj],"1100");
							bj--;
							break;
						case 270://
							updateBlock(270, myB[bi][bj],"0011");
							bi++;
							break;
				}
				maxbi = maxbi > bi ? maxbi : bi;
				maxbj = maxbj > bj ? maxbj : bj;
				//p.path.add(new Pair(bi,bj));
			}
			i++;
		}//end of while
		
		direct = direct >= 180 ? direct-180: direct+180; // when get out the maze, reverse the direction.
		Pair re = new Pair(bi,bj);
		return  re;
	}//end pf func
		
	public static void iniBlock(int n, int m){
		for(int i =0;i<n;i++)
			for(int j=0;j<m;j++)
				myB[i][j] = new Block();
	}
	
	public static void print(){
		for(int i=0; i<= maxbi; i++){
			for(int j = 0; j<= maxbj; j++){
				if(myB[i][j].walk){
					
					int tem = Integer.valueOf(myB[i][j].s, 2);
					
					if(tem >9)
						System.out.print( (char)('a' + (tem - 10) ) +" ");
					else
						System.out.print(tem+" ");
					//System.out.print(tem+" ");
				}
				myB[i][j].reset();
			}
			System.out.println();
		}
	}
	
	public void runTest(String s1, String s2) {		
		out = turnlf(s1, 0, 25);
		turnlf(s2,out.i,out.j);
		myB[0][25].reset();
		myB[out.i][out.j].reset();
	}
	
	public int getMaxBi(){
		return maxbi;
	}
	public int getMaxBj(){
		return maxbj;
	}
	public Block[][] getBlock(){
		return myB;
	}
}
