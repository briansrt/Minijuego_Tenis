

package PaqJuego;


import java.awt.Graphics2D;

public class Ball {
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
        int pos=0;
	private Game2 game;
        
        

	public Ball(Game2 game) {
        this.game = game;
        
        
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - 30)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - 30){
			ya = -1;
                        pos=x;
                        
                }

		x = x + xa;
		y = y + ya;
	}
        

	public void paint(Graphics2D g) {
		g.fillOval(x, y, 30, 30);
	}
        
        public void reset() {
    x = 0;
    y = 0;
    xa = 1;
    ya = 1;
}




}
