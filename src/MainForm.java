import com.sun.deploy.config.JCPConfig;
import com.sun.deploy.panel.GeneralPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame implements ActionListener {
    private JPanel playerPanel;

    private JButton buttonOpen ;
    private JButton buttonPlay;
    private JButton buttonPause;
    private JButton open = new JButton("Open");

    private JLabel timeLabel = new JLabel("00:00:00");
    private JLabel durationLabel = new JLabel("00:00:00");
    private JLabel filenameLabel = new JLabel("Playing File: ");

    private JSlider timeSlider = new JSlider();

    private JComboBox song;

    private String filename;

    JPanel contentPane;
    ImagePanel imagePanel;
    Main main;

    JLayeredPane layeredPane;
    JPanel background;



    public MainForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0,0,0,0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        playerPanel.setOpaque(false);

        JPanel panel = new JPanel();

        setContentPane(panel);

        panel.setLayout(new GridBagLayout());

        panel.setBackground(new Color(241,156,165));
        setResizable(false);


        buttonPlay = new JButton("Play");
        buttonPlay.setEnabled(false);

        buttonPause = new JButton("Pause");
        buttonPause.setEnabled(false);

        buttonOpen = new JButton("Open");

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.gridwidth = 3;
        c.ipadx = 60;
        buttonPanel.add(buttonPlay,c);

        c.ipadx = 60;
        buttonPanel.add(buttonPause,c);
        c.ipadx = 60;
        buttonPanel.add(buttonOpen,c);

        ///////////////////////correct button panel

      /*  c.insets = new Insets(220,0,0,0);
        buttonPanel.setOpaque(false);

        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 2;

     //   playerPanel.add(buttonPanel,c);

        c.insets = new Insets(220,0,0,0);

     //   panel.add(imagePanel,c);
      //  imagePanel.add(buttonPanel);

        panel.add(buttonPanel,c);*/


     /*   ImagePanel imagePanel = new ImagePanel("D:\\ФКН КС-12\\oop\\kr2015\\panel\\music.jpg");
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.setBounds(0,0,500,200);
       // imagePanel.setSize(500,200);
        c.ipadx = 500;
        c.ipady = 200;
        c.gridwidth = 0;
        c.insets = new Insets(0,0,0,0);

        panel.add(imagePanel,c);*/

        ////////////////////////////////////
        c.ipadx = 60;


        c.insets = new Insets(220,0,0,0);
        buttonPanel.setOpaque(false);

        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 2;

        //   playerPanel.add(buttonPanel,c);

        c.insets = new Insets(220,0,0,0);

        //   panel.add(imagePanel,c);
        //  imagePanel.add(buttonPanel);

        panel.add(buttonPanel,c);


       // iPanel.add(playerPanel);

       // iPanel.add()


        setVisible(true);

    }



    public void initPlayer(){
        playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        playerPanel.setOpaque(true);//прозорий

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.CENTER;

        buttonPlay = new JButton("Play");
        buttonPlay.setEnabled(false);

        JPanel buttonPanel = new JPanel();//layout
        buttonPanel.setOpaque(true);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        playerPanel.add(buttonPanel,c);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
