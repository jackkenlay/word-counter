import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class WordCounterGUI {
	private JFrame frame;
	private JTextArea inputTextField;
	private WordCounter wordCounter;
	private JButton btnOpenFiles;

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
		
		JButton btnNewButton = new JButton("Count Words");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processInputText();
			}
		});

		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		inputTextField = new JTextArea();
		frame.getContentPane().add(inputTextField, BorderLayout.WEST);
		inputTextField.setColumns(10);
		
		btnOpenFiles = new JButton("Open files");
		frame.getContentPane().add(btnOpenFiles, BorderLayout.NORTH);
		
		btnOpenFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = getFileName();
				String[] results = wordCounter.getWordCountFromFile(fileName);
				displayResults(results);
			}
		});
		
	}

	private String getFileName() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		String path = "";
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath(); 
		}
		return path;
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

	private void processInputText() {
		String text = this.getTextFieldText();
		if((text.length()==0)||(text == null) || (text.equals("Please enter text:"))){
			inputTextField.setText("Please enter text:");	
		}else {
			String[] results = wordCounter.getWordCount(text);
			this.displayResults(results);
		}
	}
}
