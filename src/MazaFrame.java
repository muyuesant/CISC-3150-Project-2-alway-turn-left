import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFrame;





public class MazaFrame extends JFrame{
	
	private static MazaCanvas myCanvas;
	
	private static final int height = 500;
	private static final int width = 500;


	public MazaFrame() {
		setSize(height, width);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);        
		setVisible(true);
		myCanvas = new MazaCanvas();
		add(myCanvas);
	}
	
	public MazaFrame(String s1, String s2) {
		setSize(height, width);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);        
		setVisible(true);
		myCanvas = new MazaCanvas(s1,s2);
		add(myCanvas);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		
		BufferedReader infile = new BufferedReader(new FileReader("input")); // a decorated FileReader!
		String buffer = null;
		buffer = infile.readLine();
		int caseNum = Integer.valueOf(buffer);
		String s1,s2;
		while((buffer = infile.readLine()) != null && caseNum >0) {							// read in a line of text
			StringTokenizer words = new StringTokenizer(buffer);				// chop it up into words
			/*while( words.hasMoreTokens() ) {									// test each word
				String word = words.nextToken();
				if (which.check(word)) {
					System.out.println(word);
				}
			}*/
			s1 = words.nextToken();
			s2 = words.nextToken();
			//MazaFrame game = new MazaFrame("WWWLWLWRRWLWLWWLWRW","WLWRWWRWWRWWLWLWWRRWLWLWRRWRWLWLWRRWWLW");
			MazaFrame game = new MazaFrame(s1,s2);
			myCanvas.move();
			game.dispose();
		}
		infile.close();
		
	}

}
