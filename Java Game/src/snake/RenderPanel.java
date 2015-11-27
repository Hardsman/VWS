package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class RenderPanel extends JPanel{

	private static final long serialVersionUID = 2374893107743112921L;

	public static Color background = new Color(3342336);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(background);
		g.fillRect(0,0,600,500); 
		Snake snake = Snake.snake;

		g.setColor(Color.WHITE);
		for (Point point : snake.tail){
			g.fillRect(point.x * Snake.dimScale, point.y * Snake.dimScale, Snake.dimScale, Snake.dimScale);			
		}
		g.fillRect(snake.head.x * Snake.dimScale, snake.head.y * Snake.dimScale, Snake.dimScale, Snake.dimScale);
		g.setColor(Color.GREEN);
		g.fillRect(snake.food.x * Snake.dimScale, snake.food.y * Snake.dimScale, Snake.dimScale, Snake.dimScale);
		
		g.drawString("Score: " + snake.score + ", Length " + snake.length, 97, 10);
		
	}
}
