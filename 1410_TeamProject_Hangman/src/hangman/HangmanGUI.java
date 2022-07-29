package hangman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.awt.Font;

public class HangmanGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static String randomUpperWord = "";
	private static GameStatus gameStatus;
	private static HangmanGUIMenu menu;
	private static ArrayList<String> path = new ArrayList<String>();
	private static Random rand = new Random();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try (Scanner input = new Scanner(new File("src/hangman/files/words.txt"))){
			
			
			while (input.hasNextLine()) {
				String word = input.next();
				path.add(word);
				for (int i = 0; i < path.size(); i++) {
					String upper = path.get(i).toUpperCase(Locale.ROOT);
					path.set(i, upper);
					//System.out.println(path);
				}
				
			}
		} 
		catch (FileNotFoundException e1) {
			System.out.println("Not found");
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanGUI frame = new HangmanGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static String getRandomWord(){
		int randomNum = rand.nextInt(path.size());
		randomUpperWord = path.get(randomNum);
		return randomUpperWord;
	}

	/**
	 * Create the frame.
	 */
	public HangmanGUI() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Hangman");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelGame = new JPanel();
		panelGame.setBackground(Color.WHITE);
		contentPane.add(panelGame, BorderLayout.CENTER);
		panelGame.setLayout(new BorderLayout(5, 5));
		
		gameStatus = new GameStatus();
		panelGame.add(gameStatus, BorderLayout.CENTER);
		
		Keyboard keyboard = new Keyboard(gameStatus);
		panelGame.add(keyboard, BorderLayout.SOUTH);
		
		JPanel panelMenuWest = newPanelMenuWest();
		contentPane.add(panelMenuWest, BorderLayout.WEST);
		
		gameStatus.setKeyboard(keyboard);
		gameStatus.setMenu(menu);
		menu.setGame(gameStatus);
	}

	/**
	 * 
	 */
	private JPanel newPanelMenuWest() {
		JPanel panelMenuWest = new JPanel();
		menu = new HangmanGUIMenu();
		JPanel testMenuPanel = menu.myTestPanelMethod(this);
        panelMenuWest.add(testMenuPanel);
		
		return panelMenuWest;
	}

}
