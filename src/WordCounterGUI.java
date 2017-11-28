import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		frame.setBounds(100, 100, 650, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Count Words From Text Box");
		btnNewButton.setBounds(12, 63, 242, 54);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processInputText();
			}
		});
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(btnNewButton);
		
		inputTextField = new JTextArea();
		inputTextField.setBounds(12, 124, 626, 130);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);
		
		btnOpenFiles = new JButton("Count Words from file");
		btnOpenFiles.setBounds(12, 12, 242, 51);
		frame.getContentPane().add(btnOpenFiles);
		
		ImageIcon image = new ImageIcon("src/logo.png");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(266, 12, 372, 105);
		frame.getContentPane().add(panel);
		panel.add( label, BorderLayout.CENTER );
		
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
		String text = "Results: \n"; 
		text += this.seperateTextArray(results);
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
		if((text.length()==0)||(text == null) || (text.equals("Please enter text:")) || (text.length()>10 && text.substring(0, 10).equals("Results: \n"))){
			inputTextField.setText("Please enter text:");	
		}else {
			String[] results = wordCounter.getWordCount(text);
			this.displayResults(results);
		}
	}
}
