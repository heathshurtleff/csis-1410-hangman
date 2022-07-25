package hangman;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Keyboard extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Character> letters = new ArrayList<Character>();
	private List<JButton> keyboardButtons = new ArrayList<JButton>();

	public Keyboard() {
		Dimension keyboardSize = new Dimension(410, 125);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setBackground(UIManager.getColor("window"));
		setBorder(new LineBorder(UIManager.getColor("windowBorder")));
		setPreferredSize(keyboardSize);

		Collections.addAll(letters, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		
		for(char letter : letters) {
			JButton newButton = createLetterButton(letter);
			keyboardButtons.add(newButton);
			this.add(newButton);
		}
	}
	
	private JButton createLetterButton(char letter) {
		JButton keyboardButton = new JButton(Character.toString(letter));
		Color btnDefaultText = new Color(17,17,17);
		Color btnDefaultBorder = new Color(40,40,40);
		Color btnDefaultBG = new Color(245,245,245);
		Color btnHover = new Color(220, 255, 255);
		Dimension btnSize = new Dimension(30,30);
		keyboardButton.setForeground(btnDefaultText);
		keyboardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		keyboardButton.setBorder(new LineBorder(btnDefaultBorder, 1, true));
		keyboardButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		keyboardButton.setPreferredSize(btnSize);
		keyboardButton.setOpaque(true);
		keyboardButton.setBackground(btnDefaultBG);
		keyboardButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				keyboardButton.setBackground(btnHover);
			}
			
			public void mouseExited(MouseEvent evt) {
				keyboardButton.setBackground(btnDefaultBG);
			}
		});
		
		return keyboardButton;
	}
}