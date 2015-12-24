import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    private Image img;
    private String line;

    public LogoPanel(String path){
        this(new ImageIcon(path).getImage());
        this.line = line;
    }

    public LogoPanel(Image img){
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(img,0,0,200,200, null);
        ///  this.paintChildren(g);       ???????????????
        g.drawImage(new ImageIcon(line).getImage(),200,200,null);
           repaint();
    }
}
