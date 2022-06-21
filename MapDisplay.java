/**
 * @author Baxter J B Berdinner - Taken from Bill
 * @version 15/06/2022
 */

//Import nessesary java extensions
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MapDisplay extends JFrame
{
    final int width = 800;
    final int height = 800;

    //Images
    final String landFile="land.png";
    ImageIcon landImage = new ImageIcon(landFile);

    String displayMap[][];

    public MapDisplay(String[][] m)
    {
        setTitle("Map Generator 3000 - Export");  //Whateveryou want the window to be called.

        this.getContentPane().setPreferredSize(new Dimension(width,height));  
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);

        displayMap = m;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.fillRect(20, 40, 100, 200);

        landImage.paintIcon(this, g, 100,100);
    }
}