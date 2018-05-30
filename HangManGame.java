import java.util.*;
import java.io.*;

public class HangManGameApp {
	private final int HIDDENCHAR = 3; 
	private StringBuffer hiddenWord; 
	private String gameWord; 
	private Scanner scanner; 
	private int failCounter; 
	
	public HangManGameApp() { 
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		HangManGameApp app = new HangManGameApp();
		app.run();
	}

	public void run() {
		System.out.println("지금부터 행맨 게임을 시작합니다.");
		Words words = new Words("words.txt"); 	
		while(true) {
			gameWord = words.getRandomWord(); 
			if(gameWord == null) break; 
			makeHidden();
			go();
			System.out.print("Next(y/n)?");
			String answer = scanner.next();
			if(answer.equals("n"))
				break;
		}
	}
	
	private void makeHidden() {
		hiddenWord = new StringBuffer(gameWord);
		Random r = new Random();
		
		for(int k=0; k<HIDDENCHAR; k++) {
			int index = r.nextInt(gameWord.length());
			if(hiddenWord.charAt(index) == '-') {
				k--;
				continue;
			}
			char c = gameWord.charAt(index);
			for(int i=0; i<gameWord.length(); i++) {
				if(hiddenWord.charAt(i) == c)
					hiddenWord.setCharAt(i,'-');
			}
		}
	}

	private void go() {
		failCounter=0;
		char key;
		do {
			if(failCounter == 3) {
				System.out.println("**실패**");
				System.out.println("정답은 " + gameWord);
				return;
			}
			System.out.println(hiddenWord);
			System.out.print(">>");
			String text = scanner.next();
			key =  text.charAt(0);
		}while(!complete(key));
		System.out.println("**성공**");
		System.out.println("정답은 " + gameWord);
	}
	
	private boolean complete(char key) {
		boolean success = false;
		for(int i=0; i<gameWord.length(); i++) {
			if(hiddenWord.charAt(i) == '-' && gameWord.charAt(i) == key) {
				hiddenWord.setCharAt(i, key);
				success = true;
			}
		}
		if(!success)
			failCounter++;
		for(int i=0; i<gameWord.length(); i++) {
			if(hiddenWord.charAt(i) == '-')
				return false;
		}
		return true;
	}
}

class Words {
	Vector<String> wordVector = new Vector<String>();

	public Words(String fileName) {
		try {
			Scanner scanner = new Scanner(new FileReader(fileName));
			while(scanner.hasNext()) { 
				String word = scanner.nextLine();
				wordVector.add(word);
			}
			scanner.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("file not found error");
			System.exit(0);
		}
	}
	
	public String getRandomWord() {
		final int WORDNUM = wordVector.size();
		int index = (int)(Math.random()*WORDNUM);
		return wordVector.get(index);
	}	
}
