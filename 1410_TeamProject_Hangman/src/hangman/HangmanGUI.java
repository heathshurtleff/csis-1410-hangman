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
import java.awt.Font;

public class HangmanGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public HangmanGUI() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Hangman");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		
		JPanel panelGame = new JPanel();
		panelGame.setBackground(Color.WHITE);
		contentPane.add(panelGame, BorderLayout.CENTER);
		panelGame.setLayout(new BorderLayout(5, 5));
		
		GameStatus gameStatus = new GameStatus();
		panelGame.add(gameStatus, BorderLayout.CENTER);
		
		Keyboard keyboard = new Keyboard(gameStatus);
		panelGame.add(keyboard, BorderLayout.SOUTH);
		keyboard.setKeyboardState(true);
		
		gameStatus.setKeyboard(keyboard);
	}

}
