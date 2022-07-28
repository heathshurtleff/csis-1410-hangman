package hangman;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private GameStatus game;
	private List<Character> letters = new ArrayList<Character>();
	private List<JButton> keyboardButtons = new ArrayList<JButton>();
	private boolean mouseListenersActive = false;

	public Keyboard(GameStatus game) {
		this.game = game;
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
		Color btnDisabledBorder = new Color(150,150,150);
		Color btnDefaultBG = new Color(245,245,245);
		Color btnHover = new Color(220, 255, 255);
		Dimension btnSize = new Dimension(30,30);
		keyboardButton.setForeground(btnDefaultText);
		keyboardButton.setBorder(new LineBorder(btnDisabledBorder, 1, true));
		keyboardButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		keyboardButton.setPreferredSize(btnSize);
		keyboardButton.setOpaque(true);
		keyboardButton.setEnabled(false);
		
		MouseAdapter hoverController = new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (mouseListenersActive && keyboardButton.isEnabled()) {
					keyboardButton.setBackground(btnHover);
				}
			}
			
			public void mouseExited(MouseEvent evt) {
				if (mouseListenersActive && keyboardButton.isEnabled()) {
					keyboardButton.setBackground(btnDefaultBG);
				}
			}
		};
		keyboardButton.addMouseListener(hoverController);
		
		keyboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	if (mouseListenersActive) {
            		JButton clickedButton = (JButton) event.getSource();
                    clickedButton.setEnabled(false);
                    clickedButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    clickedButton.setBackground(btnDefaultBG);
                    clickedButton.setBorder(new LineBorder(btnDisabledBorder, 1, true));
                    game.handleGuess(clickedButton.getText().charAt(0));
            	}
            }
        });
		
		return keyboardButton;
	}
	
	private void activate() {
		this.mouseListenersActive = true;
		Component[] components = this.getComponents();
		Color btnDefaultBG = new Color(245,245,245);
		Color btnDefaultBorder = new Color(40,40,40);
		
		for (Component component : components) {
			if (component instanceof JButton) {
				JButton keyboardButton = (JButton) component;
				keyboardButton.setBackground(btnDefaultBG);
				keyboardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				keyboardButton.setBorder(new LineBorder(btnDefaultBorder, 1, true));
				keyboardButton.setEnabled(true);
			}
		}
	}
	
	private void deactivate() {
		this.mouseListenersActive = true;
		Component[] components = this.getComponents();
		Color btnDefaultBG = new Color(245,245,245);
		Color btnDisabledBorder = new Color(150,150,150);
		
		for (Component component : components) {
			if (component instanceof JButton) {
				JButton keyboardButton = (JButton) component;
				keyboardButton.setBackground(btnDefaultBG);
				keyboardButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				keyboardButton.setBorder(new LineBorder(btnDisabledBorder, 1, true));
				keyboardButton.setEnabled(false);
			}
		}
	}
	
	public void setKeyboardState(boolean active) {
		if (active) {
			activate();
		} else {
			deactivate();
		}
	}
}