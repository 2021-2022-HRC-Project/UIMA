package annotatorServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.darkprograms.speech.microphone.MicrophoneAnalyzer;
import com.darkprograms.speech.recognizer.GSpeechDuplex;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class GoogleSpeechRecognition{
	
	private MicrophoneAnalyzer mic;
	private JButton record;
	private JButton stop;
	private GSpeechDuplex duplex;
	private JTextArea response;
	private SpeechResponseListener sp;

	public GoogleSpeechRecognition() {

		this.initialize();
		this.SetUpGUI();
		this.initializeActionListeners();
	}

	private void initialize() {
		this.mic = new MicrophoneAnalyzer(FLACFileWriter.FLAC);
		this.duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
		duplex.setLanguage("en");
	}
	
	private void SetUpGUI() {
		
		JFrame frame = new JFrame("Speech Recorded");
		frame.setDefaultCloseOperation(3);
		response = new JTextArea();
		response.setEditable(false);
		response.setWrapStyleWord(true);
		response.setLineWrap(true);
		
		this.record = new JButton("Record");
		this.stop = new JButton("Stop");
		stop.setEnabled(false);
		

		JLabel infoText = new JLabel(
				"<html><div style=\"text-align: center;\">Just hit record and watch your voice be translated into text.\n<br>Only English is supported by this demo, but the full API supports dozens of languages.<center></html>",
				0);
		frame.getContentPane().add(infoText);
		infoText.setAlignmentX(0.5F);
		JScrollPane scroll = new JScrollPane(response);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 1));
		frame.getContentPane().add(scroll);
		JPanel recordBar = new JPanel();
		frame.getContentPane().add(recordBar);
		recordBar.setLayout(new BoxLayout(recordBar, 0));
		recordBar.add(record);
		recordBar.add(stop);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
	}

	private void initializeActionListeners() {

		this.sp = new SpeechResponseListener(response);
		duplex.addResponseListener(sp);
		
		record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Thread(() -> {
					try {
						duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}).start();
				record.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				record.setEnabled(true);
				stop.setEnabled(false);
				return;
			}
		});
		
	}

	
	public String getResponse() {
		String output =  this.sp.getRecorded();
		this.sp.clearRecorded();
		return output;
	}
	

}
