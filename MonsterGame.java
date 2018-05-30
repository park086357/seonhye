import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Project2 extends JFrame {	
	private GamePanel gamePanel = new GamePanel("a", "M", 200);
	private JLabel avatar_loc;
	private JLabel monster_loc;
	
	public Project2() {
		setTitle("선혜 개인 프로젝트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(gamePanel);
	
		setSize(300,300);
		setVisible(true);

		gamePanel.setFocusable(true);		
		gamePanel.requestFocus();
	}

	
	class GamePanel extends JPanel {
		private String avatarChar;
		private String monsterChar;
		private long monsterDelay;
		private JLabel avatar;
		private JLabel monster;
		final int AVATAR_MOVE = 10;

		private Image img = new ImageIcon("back/back.jpg").getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		
		
		public GamePanel(String avatarChar, String monsterChar, long monsterDelay) {
			this.avatarChar = avatarChar;
			this.monsterChar = monsterChar;
			this.monsterDelay = monsterDelay;
			
			avatar = new JLabel(avatarChar); 
			monster = new JLabel(monsterChar);
			
			setLayout(null);
			addKeyListener(new MyKeyListener());
			
			avatar.setLocation(50,50);
			avatar.setSize(15,15);
			avatar.setForeground(Color.RED);
			add(avatar);
			
			avatar_loc = new JLabel();
			avatar_loc.setText("아바타위치: (" + avatar.getX() + "," + avatar.getY() + ")");
			avatar_loc.setSize(150,15);
			avatar_loc.setLocation(90,220);
			add(avatar_loc);
			
			monster.setLocation(200,5);
			monster.setSize(15,15);
			add(monster);
			
			monster_loc = new JLabel();
			monster_loc.setText("몬스터위치: (" + monster.getX() + "," + monster.getY() + ")");
			monster_loc.setSize(150,15);
			monster_loc.setLocation(90,235);
			add(monster_loc);
			
			MonsterThread th = new MonsterThread(monster, avatar, monsterDelay);
			th.start();
			
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JPanel panel = (JPanel)e.getSource();
					panel.requestFocus();
				}
			});

		}
		
		class MyKeyListener extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent e) {
				
				int keyCode = e.getKeyCode();

				switch(keyCode) {
				case KeyEvent.VK_UP: 
					avatar.setLocation(avatar.getX(), avatar.getY()-AVATAR_MOVE); 
					break;
				case KeyEvent.VK_DOWN: 
					avatar.setLocation(avatar.getX(), avatar.getY()+AVATAR_MOVE); 
					break;
				case KeyEvent.VK_LEFT: 
					avatar.setLocation(avatar.getX()-AVATAR_MOVE, avatar.getY()); 
					break;
				case KeyEvent.VK_RIGHT: 
					avatar.setLocation(avatar.getX()+AVATAR_MOVE, avatar.getY()); 
					break;
				}
				
				avatar_loc.setText("아바타위치: (" + avatar.getX() + "," + avatar.getY() + ")");
				
				avatar.getParent().repaint();
			}
		}
	}

	class MonsterThread extends Thread {
		private JLabel from; 
		private JLabel to; 
		private long monsterDelay;
		private final int MONSTER_MOVE = 5; 
		
		public MonsterThread(JLabel from, JLabel to, long monsterDelay) {
			this.from = from;
			this.to = to;
			this.monsterDelay = monsterDelay;
		}
		
		@Override
		public void run() {
			int x=from.getX(),y=from.getY();
			
			while(true) {
				if(to.getX() < from.getX()) 
					x = from.getX() - MONSTER_MOVE;
				else if(to.getX() > from.getX()) 
					x = from.getX() + MONSTER_MOVE;
				
				if(to.getY() < from.getY())
					y = from.getY() - MONSTER_MOVE;
				else if(to.getY() > from.getY())		
					y = from.getY() + MONSTER_MOVE;			
				
				from.setLocation(x, y);
				from.getParent().repaint();
				
				monster_loc.setText("몬스터 위치: (" + from.getX() + "," + from.getY() + ")");
				
				if (to.getX() == from.getX() && to.getY() == from.getY()) {
					JOptionPane.showMessageDialog(from.getParent(), "잡혔습니다! :(");
					to.setLocation(50,50);
					from.setLocation(200,5);
				}
				
				try {
					sleep(monsterDelay);
				}catch(InterruptedException e) {
					return;
				}
			}
		}
	}
	
	public static void main(String [] args) {
		new Project2();
	}
} 
