package hangman;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HangmanGUIMenu {
	public JTextField usernameText;

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
		
		JLabel lblEasyWins = new JLabel("Easy:");
		lblEasyWins.setHorizontalAlignment(SwingConstants.LEFT);
		testMenuPanel.add(lblEasyWins);
		
		JLabel lblMediumWins = new JLabel("Medium:");
		lblMediumWins.setHorizontalAlignment(SwingConstants.LEFT);
		testMenuPanel.add(lblMediumWins);
		
		JLabel lblHardWins = new JLabel("Hard:");
		lblHardWins.setHorizontalAlignment(SwingConstants.LEFT);
		testMenuPanel.add(lblHardWins);
		
		JLabel lblDifficulty = new JLabel("DIFFICULTY");
		lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 11));
		testMenuPanel.add(lblDifficulty);
		
		JRadioButton BttnEasy = new JRadioButton("Easy");
		testMenuPanel.add(BttnEasy);
		
		JRadioButton BttnMedium = new JRadioButton("Medium");
		testMenuPanel.add(BttnMedium);
		
		JRadioButton BttnHard = new JRadioButton("Hard");
		testMenuPanel.add(BttnHard);
		
		JButton GameButton = new JButton("Start Game");
		GameButton.setMargin(new Insets(1, 7, 1, 7));
		testMenuPanel.add(GameButton);
		
		return testMenuPanel;
	}
}