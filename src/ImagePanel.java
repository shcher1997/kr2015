import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;
    private String line;

    public ImagePanel(String path){
        this(new ImageIcon(path).getImage());
        this.line = line;
    }

    public ImagePanel(Image img){
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

         g.drawImage(img,0,0,700,500, null);
      ///  this.paintChildren(g);       ???????????????
        g.drawImage(new ImageIcon(img).getImage(),700,500,null);
     //   repaint();
    }
}
