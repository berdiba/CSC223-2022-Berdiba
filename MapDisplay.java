/**
 * @author Baxter J B Berdinner - Taken from Bill
 * @version 15/06/2022
 */

//Import nessesary java extensions.
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MapDisplay extends JFrame
{
    final int width = 1000;
    final int height = 1000;
    
    final int yOffset = 9;
    final int xOffset = 32;

    public String Land = ("██");
    public String Hills = ("▓▓");
    public String Coast = ("▒▒");
    public String Sea = ("░░");

    //Images
    final String landFile="land.png";
    ImageIcon landImage = new ImageIcon(landFile);
    final String hillsFile="hills.png";
    ImageIcon hillsImage = new ImageIcon(hillsFile);
    final String coastFile="coast.png";
    ImageIcon coastImage = new ImageIcon(coastFile);
    final String seaFile="sea.png";
    ImageIcon seaImage = new ImageIcon(seaFile);
    
    final String borderFile="border.png";
    ImageIcon borderImage = new ImageIcon(borderFile);

    String displayMap[][];

    public MapDisplay(String[][] m)
    {
        setTitle("Map Generator 3000 - Export");

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

        for (int x=0;x<32;x++)
        {
            for (int y=0;y<32;y++)
            {
                if(x > 0 && x < 32-1 && y > 0 && y < 32-1) //!!!!!!!!!CHANGE 32 to make heights link to height variables in Main
                {
                    if(displayMap[x][y].equals(Land))
                        landImage.paintIcon(this, g, y*32+yOffset,x*32+xOffset);
                    if(displayMap[x][y].equals(Hills))
                        hillsImage.paintIcon(this, g, y*32+yOffset,x*32+xOffset);
                    if(displayMap[x][y].equals(Coast))
                        coastImage.paintIcon(this, g, y*32+yOffset,x*32+xOffset);
                    if(displayMap[x][y].equals(Sea))
                        seaImage.paintIcon(this, g, y*32+yOffset,x*32+xOffset);
                } else
                    borderImage.paintIcon(this, g, y*32+yOffset,x*32+xOffset);
            }
        }
    }
}