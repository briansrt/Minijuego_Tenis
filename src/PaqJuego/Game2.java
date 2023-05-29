

package PaqJuego;

import ig.Usuario;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Game2 extends JPanel {

	Ball ball = new Ball(this);
        Racquet racquet = new Racquet(this);
        public int con=0,vel=11;
        private String playerId;
        private String playerName;
        private Usuario usuario;
        private Ranking ranking;
  
        public Game2() {
            ranking = new Ranking();
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}

                    
		});
                setFocusable(true);
                        }
        
	public void move() {
               Game2 game = new Game2();
		ball.move();
                racquet.move();
                
                if(ball.y==348){
                     if(ball.x>=(racquet.x-10) && ball.x<=(racquet.x+60)){  
                        con=con+3;
                        System.out.println(con);
                        if(con % 3==0)
                            vel=Math.abs(vel-3);
                        ball.ya = -ball.ya;
                        
                     }
                     else{
                        
                       if(JOptionPane.showConfirmDialog(this,"Game Over\nTu puntaje fue: "+con+"\nDesea Continuar","INFO", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                         
                           try {
                               guardarPuntaje();
                               ranking.mostrarRanking();
                               Thread.sleep(3300);
                               game.ranking.agregarPuntuacion(playerName, con);
                               reiniciarJuego();
                           } catch (InterruptedException ex) {
                               Logger.getLogger(Game2.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }else{
                         con=0;vel=11;
                         ball.reset();
                         racquet.reset();
                         repaint();
                     }
                }
                }
	}
        
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
                racquet.paint(g2d);
	}
      
	public static void main(String[] args) throws InterruptedException {
            Game2 game = new Game2();
            Usuario usuario = new Usuario(game);
            usuario.iniciarIngreso();
            while(!usuario.verificador){
                Thread.sleep(100);
            }
            JFrame frame = new JFrame("Mini Tennis");
            frame.add(game);
            frame.setLocation(535, 200);
            frame.setSize(300, 430);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            while (true) {
            game.move();
            game.repaint();
            Thread.sleep(game.vel);
        }   
    }
                
        public void setPlayerName(String playerName) {
        this.playerName = playerName;
        }
     
        private void guardarPuntaje() {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt", true));
        writer.write(playerName + "," + con);
        writer.newLine();
        writer.close();
        ranking.agregarPuntuacion(playerName, con);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        private void reiniciarJuego() throws InterruptedException {
            Game2 game = new Game2();
            Usuario usuario = new Usuario(game);
            usuario.iniciarIngreso();
            usuario.verificador = false;
            while(!usuario.verificador){
                Thread.sleep(100);
            }
            JFrame frame = new JFrame("Mini Tennis");
            frame.add(game);
            frame.setLocation(535, 200);
            frame.setSize(300, 430);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            while (true) {
                game.move();
                game.repaint();
                Thread.sleep(game.vel);; // Salir del ciclo si el usuario ha ingresado nuevos datos
        
            }
        }
        
}