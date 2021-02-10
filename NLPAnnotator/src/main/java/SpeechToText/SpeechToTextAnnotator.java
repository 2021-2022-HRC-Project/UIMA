package SpeechToText;

import annotatorServer.Annotator;
//import annotatorServer.SpeechResponseListener;
import annotatorServer.SpokenTextJava;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.google.gson.Gson;
import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeechToTextAnnotator extends Annotator {
    private SpeechToTextResponseObserver responseObserver;
    private Gson gson;
    private String resultString = "";
    private JTextArea response;
    private JButton record;
    private JButton stop;
    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.SpokenText\":";
    private boolean terminationFlag = false;

    //TODO: add a confirm button
    private JButton confirm;
//    public static void main(String[] args) {
//        try {
//            SpeechToTextAnnotator annotator = new SpeechToTextAnnotator();
//            annotator.SetUpGUI();
//            annotator.initializeActionListeners();
////            streamingMicRecognize();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public SpeechToTextAnnotator(){
        super();
        try {
            this.SetUpGUI();
            this.initializeActionListeners();
//            streamingMicRecognize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String process(String request) {

        // you can comment the following line out if you care what it says
//        output = "Pick up the red block.";
//        SpokenTextJava type = new SpokenTextJava(resultString);
        String output = "{"+unitWrapper+"["+"\"text\":\""+resultString+"\"]"+"}";
        System.out.println("Output Here: " + output + "************");
        return output;
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
        record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                record.setEnabled(false);
                stop.setEnabled(true);
                try {
                    new Thread(() -> {
                        terminationFlag = false;
                        try {
                            streamingMicRecognize();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                terminationFlag = true;
                record.setEnabled(true);
                stop.setEnabled(false);

                return;
            }
        });
    }


    /** Performs microphone streaming speech recognition with a duration of 1 minute. */
    public void streamingMicRecognize() throws Exception {

        this.responseObserver = null;
        try (SpeechClient client = SpeechClient.create()) {

            responseObserver = new SpeechToTextResponseObserver();

            ClientStream<StreamingRecognizeRequest> clientStream =
                    client.streamingRecognizeCallable().splitCall(responseObserver);

            RecognitionConfig recognitionConfig =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            .setSampleRateHertz(16000)
                            .build();
            StreamingRecognitionConfig streamingRecognitionConfig =
                    StreamingRecognitionConfig.newBuilder().setConfig(recognitionConfig).build();

            StreamingRecognizeRequest request =
                    StreamingRecognizeRequest.newBuilder()
                            .setStreamingConfig(streamingRecognitionConfig)
                            .build(); // The first request in a streaming call has to be a config

            clientStream.send(request);
            // SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed: true,
            // bigEndian: false
            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
            DataLine.Info targetInfo =
                    new Info(
                            TargetDataLine.class,
                            audioFormat); // Set the system information to read from the microphone audio stream

            if (!AudioSystem.isLineSupported(targetInfo)) {
                System.out.println("Microphone not supported");
                System.exit(0);
            }
            // Target data line captures the audio stream the microphone produces.
            // TODO use gui to start
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            System.out.println("Start speaking");
            long startTime = System.currentTimeMillis();
            // Audio Input Stream
            AudioInputStream audio = new AudioInputStream(targetDataLine);
            while (true) {
                long estimatedTime = System.currentTimeMillis() - startTime;
                byte[] data = new byte[6400];
                audio.read(data);
                if (terminationFlag || estimatedTime > 10000) { // 60 seconds
                    System.out.println("Stop speaking.");
                    targetDataLine.stop();
                    targetDataLine.close();
                    break;
                }
                request =
                        StreamingRecognizeRequest.newBuilder()
                                .setAudioContent(ByteString.copyFrom(data))
                                .build();
                clientStream.send(request);
            }
//            record.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
//                    targetDataLine.open(audioFormat);
//                    targetDataLine.start();
//                    System.out.println("Start speaking");
//                    record.setEnabled(false);
//                    stop.setEnabled(true);
//                }
//            });
//            stop.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent arg0) {
//                    System.out.println("Stop speaking.");
//                    targetDataLine.stop();
//                    targetDataLine.close();
//                    request =
//                            StreamingRecognizeRequest.newBuilder()
//                                    .setAudioContent(ByteString.copyFrom(data))
//                                    .build();
//                    clientStream.send(request);
//                    return;
//                }
//            });
        } catch (Exception e) {
            System.out.println(e);
        }
        responseObserver.onComplete();
        resultString = responseObserver.getResult();
        System.out.println(resultString);
        response.setText(resultString);
    }

}
