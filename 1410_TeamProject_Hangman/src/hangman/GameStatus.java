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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * @author hshurtleff
 *
 */
public class GameStatus extends JPanel {
	private boolean gameActive = false;
	private List<ImageIcon> images = new ArrayList<ImageIcon>();
	private String activeWord = "";
	private List<Character> wordDisplay;
	private List<Character> wrongGuesses = new ArrayList<Character>();
	private JTextPane taWrong = new JTextPane();
	private JLabel lblWordDisplay = new JLabel("");
	private JLabel lblImage = new JLabel();
	private int maxWrongGuesses = 2;
	private Keyboard keyboard;

	public GameStatus() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(5, 5));
		setPreferredSize(new Dimension(400,310));
		
		JPanel panelImage = newImagePanel();
		this.add(panelImage, BorderLayout.CENTER);
		
		JPanel panelWord = newWordPanel();
		this.add(panelWord, BorderLayout.SOUTH);
		
		JPanel panelGuesses = newGuessesPanel();
		this.add(panelGuesses, BorderLayout.EAST);
	}

	/**
	 * @return
	 */
	private JPanel newImagePanel() {
		JPanel panelImage = new JPanel();
		String imagePath = "src/hangman/HangmanPics/";
		String[] imageNames = {"StandOnly.jpg", "HeadOnly.jpg", "HeadBody.jpg", "HeadBodyLeftArm.jpg", "HeadBodyBothArms.jpg",
				"LeftLeg.jpg", "WholeBody.jpg"};
		for(String image : imageNames) {
			ImageIcon icon = new ImageIcon(imagePath + image);
			images.add(icon);
		}
		
		lblImage.setIcon(images.get(wrongGuesses.size()));
		
		panelImage.add(lblImage);
		return panelImage;
	}

	/**
	 * @return
	 */
	private JPanel newWordPanel() {
		JPanel panelWord = new JPanel();
		panelWord.setPreferredSize(new Dimension(400,35));
		panelWord.setBackground(Color.WHITE);
		
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
		
		Color wrongText = new Color(192,0,12);
		taWrong.setFont(new Font("Monaco", Font.PLAIN, 18));
		taWrong.setForeground(wrongText);
		taWrong.setOpaque(false);
		taWrong.setEditable(false);
		taWrong.setPreferredSize(new Dimension(112, 150));
		
		StyledDocument doc = taWrong.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		updateWrongGuessesDisplay();
		
		panelGuesses.add(taWrong);
		
		return panelGuesses;
	}
	
	public void handleGuess(Character guess) {
		boolean wrongGuess = activeWord.indexOf(guess) == -1;
		if (wrongGuess) {
			wrongGuesses.add(guess);
			updateWrongGuessesDisplay();
			updateImageDisplay();
			
			if (wrongGuesses.size() == maxWrongGuesses) {
				keyboard.setKeyboardState(false);
			}
		} else {
			int guessIndex = activeWord.indexOf(guess);
			
			while(guessIndex >= 0) {
				wordDisplay.set(guessIndex, guess);
				guessIndex = activeWord.indexOf(guess, guessIndex + 1);
			}
			
			updateWordDisplay();
		}
	}
	
	private void updateImageDisplay() {
		int imageIncrement = 6 / maxWrongGuesses;
		int imageToShow = wrongGuesses.size() * imageIncrement;

		lblImage.setIcon(images.get(imageToShow));
	}
	
	private void updateWrongGuessesDisplay() {
		StringBuilder wrongCharacters = new StringBuilder();
		for (char guess : wrongGuesses) {
			wrongCharacters.append(guess + ", ");
		}
		
		if (wrongGuesses.size() > 0) {
			wrongCharacters.delete(wrongCharacters.lastIndexOf(", "), wrongCharacters.length() - 1);
		}

		taWrong.setText(wrongCharacters.toString());
	}
	
	private void updateWordDisplay() {
		StringBuilder wordCharacters = new StringBuilder();
		for (char letter : wordDisplay) {
			wordCharacters.append(letter + "  ");
		}
		
		wordCharacters.delete(wordCharacters.lastIndexOf("  "), wordCharacters.lastIndexOf("  ") + 1);
		lblWordDisplay.setText(wordCharacters.toString());
		
		String noSpaces = wordCharacters.toString().replaceAll(" ", "");
		
		if (noSpaces.equals(activeWord)) {
			//TODO handle actual win display
			System.out.println("You Win!");
			keyboard.setKeyboardState(false);
		}
	}
	
	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
	public void startNewGame(String word, Difficulty level) {
		this.activeWord = word;
		this.wrongGuesses = new ArrayList<Character>();
		updateWrongGuessesDisplay();
		wordDisplay = new ArrayList<Character>(activeWord.length());
		updateWordDisplay();
		
		for (int i = 0; i < activeWord.length(); i++) {
			wordDisplay.add('_');
		}
		if (level == Difficulty.EASY) {
			maxWrongGuesses = 6;
		} else if (level == Difficulty.MEDIUM) {
			maxWrongGuesses = 3;
		} else if (level == Difficulty.HARD) {
			maxWrongGuesses = 2;
		}
		
		updateWordDisplay();
		keyboard.setKeyboardState(true);
	}
}
