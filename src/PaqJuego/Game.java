

package PaqJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	int x = 0;
	int y = 0;
        int xa = 1;
	int ya = 1;

	private void moveBall() {
		//int r=(int)(Math.random()*380);
                //x = x+1;
		//y = y+1;
                if (x + xa < 0){
			xa = 1;
                        y=y+20;
                }
		if (x + xa > getWidth() - 20){
			xa = -1;
                        y=y+20;
                }
                        
		//if (y + ya < 0)
			//ya = 1;
		//if (y + ya > getHeight() - 20)
			//ya = -1;
		
		x = x + xa;
		y = y + 0;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
                if(xa == -1)
                   g2d.setColor(Color.red);
                if (ya == -1)
                   g2d.setColor(Color.blue); 
                
		g2d.fillOval(x, y, 20, 30);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.moveBall();
			game.repaint();
			Thread.sleep(10);
		}
	}
}