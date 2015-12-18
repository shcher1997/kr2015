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
        g.drawImage(img,0,0,null);
        g.drawImage(img,10,0,490,300, null);
      //  g.drawImage(new ImageIcon(line).getImage(),120,50,null);
    }
}
