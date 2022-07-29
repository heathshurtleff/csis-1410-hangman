package hangman;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HangmanGUIMenu {
	public JTextField usernameText;
	private GameStatus game;
	private Difficulty difficulty = Difficulty.EASY;
	private int[] scores = {0,0,0};
	private JLabel lblEasyWins = new JLabel("Easy: " + scores[0]);
	private JLabel lblMediumWins = new JLabel("Medium: "  + scores[1]);
	private JLabel lblHardWins = new JLabel("Hard: "  + scores[2]);
	private JLabel[] winLabels = {lblEasyWins, lblMediumWins, lblHardWins};

	public HangmanGUIMenu() {
	}

	JPanel myTestPanelMethod(HangmanGUI hangmanGUI) {
		JPanel testMenuPanel = new JPanel();
		
		testMenuPanel.setLayout(new GridLayout(10, 1, 0, 0));
		
		usernameText = new JTextField();
		testMenuPanel.add(usernameText);
		usernameText.setColumns(10);
		
		JLabel lblWins = new JLabel("WINS");
		lblWins.setFont(new Font("Tahoma", Font.BOLD, 11));
		testMenuPanel.add(lblWins);
		
		lblEasyWins.setHorizontalAlignment(SwingConstants.LEFT);
		testMenuPanel.add(lblEasyWins);
		
		lblMediumWins.setHorizontalAlignment(SwingConstants.LEFT);
		testMenuPanel.add(lblMediumWins);
		
		lblHardWins.setHorizontalAlignment(SwingConstants.LEFT);
		testMenuPanel.add(lblHardWins);
		
		JLabel lblDifficulty = new JLabel("DIFFICULTY");
		lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 11));
		testMenuPanel.add(lblDifficulty);
		
		JRadioButton BttnEasy = new JRadioButton("Easy");
		BttnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty = Difficulty.EASY;
			}
		});
		testMenuPanel.add(BttnEasy);
		
		JRadioButton BttnMedium = new JRadioButton("Medium");
		BttnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty = Difficulty.MEDIUM;
			}
		});
		testMenuPanel.add(BttnMedium);
		
		JRadioButton BttnHard = new JRadioButton("Hard");
		BttnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty = Difficulty.HARD;
			}
		});
		testMenuPanel.add(BttnHard);
		
		JButton GameButton = new JButton("Start Game");
		GameButton.setMargin(new Insets(1, 7, 1, 7));
		GameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String GameWord = HangmanGUI.getRandomWord();
				game.startNewGame(GameWord, difficulty);
			}
		});	
		testMenuPanel.add(GameButton);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(BttnEasy);
		buttonGroup.add(BttnMedium);
		buttonGroup.add(BttnHard);
		BttnEasy.setSelected(true);
		
		return testMenuPanel;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public void setScores(int[] newScores) {
		scores = newScores;
		try(PrintWriter writer = new PrintWriter(new File("src/hangman/files/scores.txt"))) {
			for (int i = 0; i < winLabels.length; i++) {
				StringBuilder labelText = new StringBuilder(winLabels[i].getText());
				labelText.delete(labelText.indexOf(" "), labelText.length());
				labelText.append(" " + scores[i]);
				winLabels[i].setText(labelText.toString());
				writer.println(scores[i]);
			}
		} catch (IOException e) {
			System.out.println("Unable to save scores");
			e.printStackTrace();
		}
	}
	
	public void setGame(GameStatus game) {
		this.game = game;
	}
}