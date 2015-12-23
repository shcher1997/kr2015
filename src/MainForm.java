import com.sun.deploy.config.JCPConfig;
import com.sun.deploy.panel.GeneralPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame implements ActionListener, PLayList {
    private JPanel playerPanel;

    private JButton buttonOpen ;
    private JButton buttonPlay;
    private JButton buttonPause;
    private JButton open = new JButton("Open");

    private JLabel labelTimeCounter = new JLabel("00:00:00");
    private JLabel labelDuration = new JLabel("00:00:11");
    private JLabel labelFileName = new JLabel("Playing File: ");

    private JSlider sliderTime = new JSlider();

    private JComboBox song;

    private String filename;

    private JTextPane textArea;

    JPanel contentPane;
    ImagePanel imagePanel;
    Main main;

    JLayeredPane layeredPane;
    JPanel background;

    public MainForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        playerPanel.setOpaque(false);

        GridBagLayout gridBagLayout = new GridBagLayout();
        //setLayout(gridBagLayout);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;

        buttonPlay = new JButton("Play");
        buttonPlay.setEnabled(false);

        buttonPause = new JButton("Pause");
        buttonPause.setEnabled(false);

        buttonOpen = new JButton("Open");


      /*  c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0,0,0,0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;*/

       // JPanel panel = new JPanel();

      //  setContentPane(panel);

     //   panel.setLayout(new GridBagLayout());

       // panel.setBackground(new Color(241,156,165));
        setResizable(false);



        sliderTime.setOpaque(false);
        sliderTime.setPreferredSize(new Dimension(400, 20));
        sliderTime.setEnabled(false);
        sliderTime.setValue(0);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        playerPanel.add(labelFileName,c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(labelTimeCounter,c);

        c.gridx = 1;
        playerPanel.add(sliderTime,c);

        c.gridx = 2;
        playerPanel.add(labelDuration,c);

        //c.insets = new Insets(220,0,0,0);

       // panel.add(buttonPanel,c);
        buttonPlay.setPreferredSize(new Dimension(100,30));
        buttonPause.setPreferredSize(new Dimension(100,30));
        buttonOpen.setPreferredSize(new Dimension(100,30));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        buttonPanel.add(buttonPause);
        buttonPanel.add(buttonPlay);
        buttonPanel.add(buttonOpen);
        buttonPanel.setOpaque(false);

              /*  JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.gridwidth = 3;
        c.ipadx = 60;
        buttonPanel.add(buttonPlay,c);

        c.ipadx = 60;
        buttonPanel.add(buttonPause,c);
        c.ipadx = 60;
        buttonPanel.add(buttonOpen,c);

        c.insets = new Insets(220,0,0,0);
        buttonPanel.setOpaque(false);*/


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

        ImagePanel imagePanel = new ImagePanel("D:\\ФКН КС-12\\oop\\kr2015\\panel\\music.jpg");
        imagePanel.setBounds(0,0,700,500);

        contentPane.add(imagePanel);
        imagePanel.setLayout(null);

      //  song = new JComboBox(items);
       // filename = String.valueOf(song.getItemAt(0));

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
        Media.setFont(new Font("Verdana", Font.ITALIC, 48));
        Media.setBounds(200, 15, 300, 64);
        imagePanel.add(Media);

        playerPanel.setBounds(10,338,654,101);
        imagePanel.add(playerPanel);


        ////////////

        /*c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,0,0,0);
        c.gridy = 2;
        c.gridwidth = 2;
        playerPanel.add(labelFileName,c);

     //   c.anchor = GridBagConstraints.CENTER;
        c.gridy = 1;
        c.gridwidth = 1;
        playerPanel.add(labelTimeCounter,c);

        c.gridx = 1;
        c.gridwidth = 2;

        playerPanel.add(sliderTime, c);

        c.gridx = 2;
     //   c.insets = new Insets(150,100,0,0);
        playerPanel.add(labelDuration, c);



        c.insets = new Insets(150,10,0,0);
        c.gridx = 0 ;
        c.gridwidth = 2;
        c.gridy = 2;

        imagePanel.add(playerPanel,c);

       /* ImagePanel imagePanel = new ImagePanel("D:\\ФКН КС-12\\oop\\kr2015\\panel\\music.jpg");
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.setBounds(0,0,500,200);
        c.ipadx = 500;
        c.ipady = 200;
        c.gridwidth = 0;
        c.insets = new Insets(0,0,0,0);*/



        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
