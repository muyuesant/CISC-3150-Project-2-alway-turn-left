import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MazaCanvas extends JPanel{
//public class MazaCanvas extends Canvas implements MouseListener{
	private static Testing myTest;
	private static Player p1;
	private static final int height = 500;
	private static final int width = 500;
	private static final int begX = 100;
	private static final int begY =100;
	private static final int blockH =40;
	private static final int blockW =40;
	private static int maxbi, maxbj;
	private static Block[][] myB;
	private static boolean isFin = false;
	
	//public boolean isRun = true;
	//public static int count =10;
	
	public MazaCanvas() {
		myTest = new Testing();
		setBackground(Color.white);
		setSize(height, width);
		
		
		String s1 = "WRWWLWWLWWLWLWRRWRWWWRWWRWLW";
		String s2 = "WWRRWLWLWWLWWLWWRWWRWWLW";

		
		myTest.runTest(s1,s2);
		maxbi = myTest.getMaxBi();
		maxbj = myTest.getMaxBj();
		myB =myTest.getBlock();
		p1 = myTest.p;
		p1.setXY(begX,begY);
		p1.setCanvas(this);
		p1.setBlock(myB);
 	}
	
	public MazaCanvas(String s1, String s2){
		myTest = new Testing();
		setBackground(Color.white);
		setSize(height, width);
		
		myTest.runTest(s1,s2);
		maxbi = myTest.getMaxBi();
		maxbj = myTest.getMaxBj();
		myB =myTest.getBlock();
		p1 = myTest.p;
		p1.setXY(begX,begY);
		p1.setCanvas(this);
		p1.setBlock(myB);

	}
	
	public void paint(Graphics g){
		super.paint(g);
		int k=0;
		for(int i=0; i<= maxbi; i++){
			 k=0;
			for(int j = 0; j<= maxbj; j++){				
				if(myB[i][j].walk){
					if(!p1.playB[i][j].pWalk){
						g.setColor(Color.black);
						g.drawRect(begX+(j-k)*40, begY+i*40, blockW, blockH);
				}
				else{
						if(!p1.playB[i][j].sWalk)
							//g.fillOval(begX+(j-k)*40, begY+i*40, 10,10);
							g.setColor(Color.black);

						else{
							g.setColor(Color.yellow);
						}
						g.fillOval(begX+(j-k)*40, begY+i*40, 10,10);
						g.setColor(Color.black);
						for(int ind =0; ind <4; ind++){//e,w,s,n
							if(myB[i][j].s.charAt(ind) == '0'){//e,w,s,n
								switch(ind){
								case 0:
									g.drawLine(begX+(j-k+1)*40, begY+i*40, begX+(j-k+1)*40,begY+(i+1)*40 );
									break;
								case 1:
									g.drawLine(begX+(j-k)*40, begY+i*40, begX+(j-k)*40,begY+(i+1)*40 );
									break;
								case 2:
									g.drawLine(begX+(j-k)*40, begY+(i+1)*40, begX+(j-k+1)*40,begY+(i+1)*40 );
									break;
								case 3:
									g.drawLine(begX+(j-k)*40, begY+i*40, begX+(j-k+1)*40,begY+i*40 );
									break;
								}
							}
							
						}
					}	
				}
				else{
					k++;
				}
			}
		}
		g.setColor(Color.black);
		if(isFin)
			g.drawString("Finish!!!", 100, 100);
	}
	
	public void move(){
		//p1.path.setSize(p1.path.size()/2+2);
		//System.out.println(p1.path.size());
		for(Pair p : p1.path){
			
			if(!p1.playB[p.i][p.j].pWalk)
				p1.playB[p.i][p.j].pWalk = true;
			else
			p1.playB[p.i][p.j].sWalk = true;
				
			repaint();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e){}
		}
		isFin = true;
		repaint();
		System.out.println("finish");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e){}
	}
	
}
