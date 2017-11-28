import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class WordCounterGUI {
	private JFrame frame;
	private JTextArea inputTextField;
	private WordCounter wordCounter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordCounterGUI window = new WordCounterGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WordCounterGUI() {
		initialize();
		wordCounter = new WordCounter();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button clicked");
				buttonClick();
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		inputTextField = new JTextArea();
		frame.getContentPane().add(inputTextField, BorderLayout.WEST);
		inputTextField.setColumns(10);
	}

	private String getTextFieldText() {
		return inputTextField.getText();
	}

	private void displayResults(String[] results) {
		String text = this.seperateTextArray(results);
		inputTextField.setText("");
		inputTextField.setText(text);
	}

	private String seperateTextArray(String[] input) {
		String toReturn = "";
		for(int i = 0; i < input.length; i++) {
			toReturn += input[i] + "\n";
		}
		return toReturn;
	}

	private void buttonClick() {
		String text = this.getTextFieldText();
		if((text.length()==0)||(text == null) || (text.equals("Please enter text:"))){
			inputTextField.setText("Please enter text:");	
		}else {
			String[] results = wordCounter.getWordCount(text);
			this.displayResults(results);
		}
	}
}
