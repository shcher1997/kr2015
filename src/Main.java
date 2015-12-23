import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.PlaybackEvent;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends JFrame {
    FileInputStream fis;
    BufferedInputStream bis;
    public Player player;
    public long pauseLocation;
    public long songTotalLength;
    public  String fileLocation = "D:\\ФКН КС-12\\oop\\kr2015\\sounds\\astronaut.mp3";

    public void stop(){
        if (player!=null){
            player.close();
        }
    }


    public void pause(){
        if (player!=null){
            try {
                pauseLocation = fis.available();
                player.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void play(String path){
        try {
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            songTotalLength = fis.available();
            fileLocation = path + "";
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(){
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void resume(){
        try {
            fis = new FileInputStream(fileLocation);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            fis.skip(songTotalLength - pauseLocation);
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(){
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
               // new MainForm();
                new MainForm();
            }
        });

    }
}
