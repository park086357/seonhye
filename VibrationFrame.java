import java.awt.*;
import javax.swing.*;
import java.util.Random;

@SuppressWarnings("serial")
public class VibratingFrame extends JFrame{
	public VibratingFrame() {
		setTitle("선혜 진동프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(300,300);
		setLocation(100,100);
		setVisible(true);
		
		VibratingThread thread = new VibratingThread(this);
		thread.start();
	}

	class VibratingThread extends Thread {
		private Component comp;
		public VibratingThread(Component comp) {
			this.comp = comp;
		}
		
		public void run() {
			Random r = new Random();
			int x = comp.getX();
			int y = comp.getY();
			
			while(true) {
				
				try {
					Thread.sleep(10);
				}
				
				catch(InterruptedException e) {
					return;
				}
				
				int sign = 1;
				if(r.nextBoolean()) 
					sign = 1;
				else 
					sign = -1;
				
				int printX = x + r.nextInt(5)*sign;
				if(r.nextBoolean()) 
					sign = 1;
				else 
					sign = -1;
				int printY = y + r.nextInt(5)*sign;
				comp.setLocation(printX, printY);
			}
		}

	}
	public static void main(String [] args) {
		new VibratingFrame();
	}
} 
