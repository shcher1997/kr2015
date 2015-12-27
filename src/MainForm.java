import com.sun.deploy.config.JCPConfig;
import com.sun.deploy.panel.GeneralPanel;
import javazoom.jl.player.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Scanner;

public class MainForm extends JFrame implements ActionListener, PLayList {
    private Player player;
    private AudioPlayer player1 = new AudioPlayer();

    private JPanel playerPanel;
    private JPanel logoPanel;

    private JButton buttonOpen ;
    private JButton buttonPlay;
    private JButton buttonPause;
    private JButton open = new JButton("Open");

    private JLabel labelTimeCounter;
    private JLabel labelDuration;
    private JLabel labelFileName;

    private JSlider sliderTime = new JSlider();

    private JComboBox song;

    private String filename;
    private  String lastOpenPath;
    private String audioFilePath;

    private JTextPane textArea;

    private Thread playbackThread;

    ImagePanel imagePanel;

    private boolean isPause;
    private boolean isPlaying;
    private PlayingTimer timer;

    private JList playlist;

    private DefaultListModel listModel;

    public MainForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);

        setTitle("MediaPlayer");

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        playerPanel.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;

        buttonPlay = new JButton("Play");
        buttonPlay.setEnabled(false);

        buttonPause = new JButton("Pause");
        buttonPause.setEnabled(false);

        buttonOpen = new JButton("Open");

        setResizable(false);

        sliderTime.setOpaque(false);
        sliderTime.setPreferredSize(new Dimension(400, 20));
        sliderTime.setEnabled(false);
        sliderTime.setValue(0);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        labelFileName = new JLabel("Playing Time");
        labelFileName.setFont(new Font("Arial",Font.BOLD,16));
        labelFileName.setForeground(new Color(254,52,180));
        playerPanel.add(labelFileName,c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        c.gridwidth = 1;

        labelTimeCounter = new JLabel("00:00:00");
        labelTimeCounter.setFont(new Font("Arial",Font.BOLD,20));
        labelTimeCounter.setForeground(new Color(254,52,180));
        playerPanel.add(labelTimeCounter,c);

        c.gridx = 1;
        playerPanel.add(sliderTime,c);

        c.gridx = 2;
        labelDuration = new JLabel("00:00:00");
        labelDuration.setFont(new Font("Arial",Font.BOLD,20));
        labelDuration.setForeground(new Color(254,52,180));
        playerPanel.add(labelDuration,c);

        buttonPlay.setPreferredSize(new Dimension(100,30));
        buttonPause.setPreferredSize(new Dimension(100,30));
        buttonOpen.setPreferredSize(new Dimension(100,30));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        buttonPanel.add(buttonPause);
        buttonPanel.add(buttonPlay);
        buttonPanel.add(buttonOpen);
        buttonPanel.setOpaque(false);

        buttonOpen.addActionListener(this);
        buttonPlay.addActionListener(this);
        buttonPause.addActionListener(this);

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        playerPanel.add(buttonPanel,c);

        setBounds(0, 0, 700, 500);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane.setLayout(null);

        imagePanel = new ImagePanel("D:\\ФКН КС-12\\oop\\kr2015\\panel\\music.jpg");
        imagePanel.setBounds(0,0,700,500);

        contentPane.add(imagePanel);
        imagePanel.setLayout(null);

        song = new JComboBox(items);
        filename = String.valueOf(song.getItemAt(0));
        song.setBounds(231,112,250,20);
        open.setBounds(485,112,100,20);
        imagePanel.add(open);
        imagePanel.add(song);

        textArea = new JTextPane();

        JScrollPane text = new JScrollPane(textArea);
        text.setBounds(10, 143, 652, 185);
      //  imagePanel.add(text);

        JLabel lblNewLabel = new JLabel("Information:");
        lblNewLabel.setForeground(new Color(254,253,25));
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 29));
        lblNewLabel.setBounds(10, 82, 211, 64);
        imagePanel.add(lblNewLabel);

        JLabel Media = new JLabel("MediaPlayer");
        Media.setForeground(new Color(250,251,2));
        Media.setFont(new Font("Verdana", Font.BOLD, 42));
        Media.setBounds(200, 15, 300, 64);
        imagePanel.add(Media);

        playerPanel.setBounds(10,338,654,101);
        imagePanel.add(playerPanel);

      //  logoPanel = new LogoPanel(itemsImage[2]);

       // logoPanel.setPreferredSize(new Dimension(400,200));
     //   logoPanel.setBounds(190,150,300,200);
      //  imagePanel.add(logoPanel);
       // repaint();

        ImageIcon icon = new ImageIcon(itemsImage[2]);
        JLabel label = new JLabel(icon);

        label.setBounds(190,150,300,200);
      //  imagePanel.add(label);

        listModel = new DefaultListModel();

        playlist = new JList(listModel);

        JScrollPane scroolList = new JScrollPane(playlist);
        scroolList.setBounds(10, 143, 673, 204);
        imagePanel.add(scroolList);

        loadList();

        Actions();

        setVisible(true);
    }

    public void Actions() {
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                if (isPause) {
                    resumePlaying();
                }
                if (isPlaying) {
                    stopPlaying();
                }
                openF();
            }
        });
        song.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent ev) {
                        if (ev.getStateChange() == ItemEvent.SELECTED) {
                            filename = String.valueOf(song.getSelectedItem());
                            System.out.println(filename);
                        }
                        int i = 0;
                        while(i<items.length){
                            if(filename == song.getItemAt(i)){
                                logoPanel = new LogoPanel(itemsImage[i]);

                                repaint();

                            }
                            i++;
                        }
                    }

                });

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            if (button == buttonOpen) {
                textArea.setText("");
                if (isPause) {
                    resumePlaying();
                }
                if (isPlaying) {
                    stopPlaying();
                }

                System.out.println();
                try {
                    openFile();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else if (button == buttonPlay) {
                if (!isPlaying) {
                    try {
                        playBack();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                } else {
                    stopPlaying();
                }
            } else if (button == buttonPause) {
                if (!isPause) {
                    pausePlaying();
                } else {
                    resumePlaying();
                }
            }
        }
    }

    private void openF(){
        JFileChooser fileChooser;

        if (lastOpenPath != null && !lastOpenPath.equals("")) {
            fileChooser = new JFileChooser(lastOpenPath);
        } else {
            fileChooser = new JFileChooser();
        }

        FileFilter wavFilter = new FileFilter() {
            @Override
            public String getDescription() {
                return "Sound file (*.WAV)";
            }

            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".wav");
            }
        };


        fileChooser.setFileFilter(wavFilter);
        fileChooser.setDialogTitle("Open Audio File");
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showOpenDialog(this);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            audioFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            lastOpenPath = fileChooser.getSelectedFile().getParent();
            if (isPlaying || isPause) {
                stopPlaying();
                while (player1.getAudioClip().isRunning()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            try {
                playBack();
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }
    private void openFile() throws AWTException {
        audioFilePath = "sounds\\"+filename;
        if (isPlaying || isPause) {
            stopPlaying();
            while (player1.getAudioClip().isRunning()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        playBack();
    }
    private void playBack() throws AWTException {
        timer = new PlayingTimer(labelTimeCounter, sliderTime);
        timer.start();
        isPlaying = true;

        playbackThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    buttonPlay.setText("Stop");
                    buttonPlay.setEnabled(true);

                    buttonPause.setText("Pause");
                    buttonPause.setEnabled(true);

                    player1.load(audioFilePath);
                    timer.setAudioClip(player1.getAudioClip());
                    labelFileName.setText("Playing File: " + audioFilePath);
                    sliderTime.setMaximum((int) player1.getClipSecondLength());

                    labelDuration.setText(player1.getClipLengthString());
                    player1.play();

                    resetControls();

                } catch (UnsupportedAudioFileException ex) {
                    JOptionPane.showMessageDialog(MainForm.this,
                            "The audio format is unsupported!", "Error", JOptionPane.ERROR_MESSAGE);
                    resetControls();
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    JOptionPane.showMessageDialog(MainForm.this,
                            "Could not play the audio file because line is unavailable!", "Error", JOptionPane.ERROR_MESSAGE);
                    resetControls();
                    ex.printStackTrace();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainForm.this,
                            "I/O error while playing the audio file!", "Error", JOptionPane.ERROR_MESSAGE);
                    resetControls();
                    ex.printStackTrace();
                }
            }
        });
        playbackThread.start();

    }
    private void stopPlaying() {
        isPause = false;
        buttonPause.setText("Pause");
        buttonPause.setEnabled(false);
        timer.reset();
        timer.interrupt();
        player1.stop();
        playbackThread.interrupt();
    }
    private void pausePlaying() {
        buttonPause.setText("Resume");
        isPause = true;
        player1.pause();
        timer.pauseTimer();
        playbackThread.interrupt();
    }
    private void resumePlaying() {
        buttonPause.setText("Pause");
        isPause = false;
        player1.resume();
        timer.resumeTimer();
        playbackThread.interrupt();
    }
    private void resetControls() {
        timer.reset();
       // timer.interrupt();
        buttonPlay.setText("Play");
        buttonPause.setEnabled(false);

        isPlaying = false;
    }

    Scanner sc;

    File file = new File("Playlist.txt");
//    PrintWriter pw = new PrintWriter(file);

    private void loadList (){

        try {
           // sc = new Scanner(new FileReader("Playlist.txt"));
            BufferedReader br = new BufferedReader(new FileReader("Playlist.txt"));
            String str;
            while ((str = br.readLine())!=null)
                //s +=str+"\n" ;
              //playlist.add(str);
            listModel.addElement(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void editPlaylist(){

    }
}


