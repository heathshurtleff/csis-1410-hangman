/**
 * 
 */
package hangman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author hshurtleff
 *
 */
public class GameStatus extends JPanel {
	private boolean gameActive = false;
	private String activeWord = "BED";
	private List<Character> wordDisplay;
	private List<Character> wrongGuesses = new ArrayList<Character>();

	public GameStatus() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(5, 5));
		setPreferredSize(new Dimension(400,310));
		
		Collections.addAll(wrongGuesses, 'A', 'C');
		wordDisplay = new ArrayList<Character>(activeWord.length());
		for (int i = 0; i < activeWord.length(); i++) {
			wordDisplay.add('_');
		}
		
		JPanel panelImage = new JPanel();
		this.add(panelImage, BorderLayout.CENTER);
		
		JPanel panelWord = newWordPanel();
		this.add(panelWord, BorderLayout.SOUTH);
		
		JPanel panelGuesses = newGuessesPanel();
		this.add(panelGuesses, BorderLayout.EAST);
	}

	/**
	 * @return
	 */
	private JPanel newWordPanel() {
		JPanel panelWord = new JPanel();
		panelWord.setPreferredSize(new Dimension(400,35));
		panelWord.setBackground(Color.WHITE);
		
		JLabel lblWordDisplay = new JLabel("");
		StringBuilder wordCharacters = new StringBuilder();
		for (char letter : wordDisplay) {
			wordCharacters.append(letter + "  ");
		}
		wordCharacters.delete(wordCharacters.lastIndexOf("  "), wordCharacters.length() - 1);
		lblWordDisplay.setText(wordCharacters.toString());
		Color wordColor = new Color(9,107,0);
		lblWordDisplay.setFont(new Font("Monaco", Font.PLAIN, 12));
		lblWordDisplay.setForeground(wordColor);
		lblWordDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		panelWord.add(lblWordDisplay);
		
		return panelWord;
	}

	/**
	 * 
	 */
	private JPanel newGuessesPanel() {
		JPanel panelGuesses = new JPanel();
		panelGuesses.setPreferredSize(new Dimension(112,245));
		
		Color defaultText = new Color(17,17,17);
		JLabel lblGuesses = new JLabel("Wrong Guesses");
		lblGuesses.setFont(new Font("Monaco", Font.PLAIN, 14));
		lblGuesses.setForeground(defaultText);
		lblGuesses.setHorizontalAlignment(SwingConstants.CENTER);
		panelGuesses.add(lblGuesses);
		
		JLabel lblWrong = new JLabel("");
		StringBuilder wrongCharacters = new StringBuilder();
		for (char guess : wrongGuesses) {
			wrongCharacters.append(guess + ", ");
		}
		wrongCharacters.delete(wrongCharacters.lastIndexOf(", "), wrongCharacters.length() - 1);
		lblWrong.setText(wrongCharacters.toString());
		Color wrongText = new Color(192,0,12);
		lblWrong.setFont(new Font("Monaco", Font.PLAIN, 18));
		lblWrong.setForeground(wrongText);
		lblWrong.setHorizontalAlignment(SwingConstants.CENTER);
		panelGuesses.add(lblWrong);
		
		return panelGuesses;
	}
	
	public void startNewGame(String word) {
		// TODO start a new game
		activeWord = word;
	}
}
