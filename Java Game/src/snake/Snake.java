package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{
	
	public static Snake snake;	
	ArrayList<Point> tail = new ArrayList<Point>();
	
	public Dimension dimension;
	public static int dimScale = 10;
	public JFrame window;
	public RenderPanel frame;
	

	
	public Snake(){
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Snake");
		frame = new RenderPanel();
		window.setVisible(true);
		window.setSize(302, 250);
		window.setResizable(false);
		window.setLocation(dimension.width / 2 - window.getWidth() / 2,
				dimension.height / 2 - window.getHeight() / 2);
		window.add(frame);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(this);
		
				
		start();
	}
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	public int richtung;
	public int ref, score, length, speed;
	
	public Timer timer = new Timer(20, this);
	
	
	
	
	public Point head, food;
	public Random randNum;
	
	public boolean collision;
	public boolean pause;
	

	

	public void start(){
		collision = false;
		pause = false;
		score = 0;
		length = 0;
		speed = 10;
		richtung = DOWN;
		tail.clear();
		randNum = new Random();
		head = new Point(0, -1);

		food = new Point(randNum.nextInt(29), randNum.nextInt(22));
		
		for (int i = 0; i < length; i++){
			tail.add(new Point(head.x, head.y)); 
		}
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.repaint();
		ref++;
				
		if (ref % 5 == 0 && head != null && collision != true && pause != true){
			tail.add(new Point(head.x, head.y)); 
			if(richtung == UP && noSnakeAt(head.x, head.y - 1)){
				if(head.y - 1 >= 0)
					head = new Point(head.x, head.y - 1);
				else
					collision = true;
			}
				
			if(richtung == DOWN && noSnakeAt(head.x, head.y + 1)){
				if(head.y + 1 < 22)
					head = new Point(head.x, head.y + 1);
				else 
					collision = true;
			}
			if(richtung == LEFT){
				if(head.x - 1 >= 0 && noSnakeAt(head.x - 1, head.y))
					head = new Point(head.x - 1, head.y);
				else
					collision = true;
			}
			if(richtung == RIGHT && noSnakeAt(head.x + 1, head.y)){
				if(head.x + 1 < 29)
					head = new Point(head.x + 1, head.y);
				else
					collision = true;				
			}
			if(tail.size() > length)
				tail.remove(0);
			 
			if(food != null){
				if(head.x == food.x && head.y == food.y){
					score+=10;
					length++;
					speed -= 1; 
					food.setLocation(randNum.nextInt(29), randNum.nextInt(22));
				}				
			}
		}				
	}
	
	public boolean noSnakeAt(int x, int y) {
		for(Point point : tail){
			if(point.equals(new Point(x, y)))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		snake = new Snake();
	}

	@Override
	public void keyTyped(KeyEvent e) {
				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT && richtung != RIGHT)
			richtung = LEFT;
		if(i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT && richtung != LEFT)
			richtung = RIGHT;
		if(i == KeyEvent.VK_W || i == KeyEvent.VK_UP && richtung != DOWN)
			richtung = UP;
		if(i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN && richtung != UP)
			richtung = DOWN;
		if(i == KeyEvent.VK_SPACE)
			if(collision)
				start();
			else
				pause = !pause;
	}

	@Override
	public void keyReleased(KeyEvent e) {
				
	}
}
