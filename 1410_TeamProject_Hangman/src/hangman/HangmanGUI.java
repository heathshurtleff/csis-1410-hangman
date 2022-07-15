package hangman;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;

public class HangmanGUI extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Hangman");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		
		JPanel panelGame = new JPanel();
		contentPane.add(panelGame, BorderLayout.CENTER);
		panelGame.setLayout(new BorderLayout(0, 0));
		
		JPanel panelKeyboard = new JPanel();
		panelGame.add(panelKeyboard, BorderLayout.SOUTH);
		
		JPanel panelGameStatus = new JPanel();
		panelGame.add(panelGameStatus, BorderLayout.CENTER);
		panelGameStatus.setLayout(new BorderLayout(0, 0));
		
		JPanel panelImage = new JPanel();
		panelGameStatus.add(panelImage, BorderLayout.CENTER);
		
		JPanel panelWord = new JPanel();
		panelGameStatus.add(panelWord, BorderLayout.SOUTH);
		
		JPanel panelGuesses = new JPanel();
		panelGameStatus.add(panelGuesses, BorderLayout.EAST);
	}

}
