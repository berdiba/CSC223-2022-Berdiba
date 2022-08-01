/**
 * @author Baxter J B Berdinner - Inspired by Bill Viggers
 * @version 15/06/2022
 */

//Import nessesary java extensions.
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MapDisplay extends JFrame
{
    final int IMGSIZE = 24; //The size in "+Style+"s of all images.

    final int width = IMGSIZE*32;
    final int height = IMGSIZE*32; //Window size.

    final int yOffset = 8;
    final int xOffset = 32;

    public String Land = ("██");
    public String Hills = ("▓▓");
    public String Coast = ("▒▒");
    public String Sea = ("░░");

    //Declaration of image variables
    ImageIcon landImage;
    ImageIcon hillsImage;
    ImageIcon seaImage;
    ImageIcon forestImage;

    ImageIcon coastImage;
    ImageIcon coastMultiImage;
    ImageIcon coastUpImage;
    ImageIcon coastDownImage;
    ImageIcon coastLeftImage;
    ImageIcon coastRightImage;

    ImageIcon borderImage;

    String displayMap[][];
    String Style = new String();

    public MapDisplay(String[][] m, String style)
    {
        displayMap = m;
        Style = style;
        
        if(!Style.equals("minecraft") && !Style.equals("pixel"))
        {
            System.out.println("Invalid style selected. Reverting to default style.");
            Style = "pixel";
        }
        
        //Image variables are here after style has been set.
        landImage = new ImageIcon("images/"+Style+"/land.png");
        hillsImage = new ImageIcon("images/"+Style+"/hills.png");
        seaImage = new ImageIcon("images/"+Style+"/sea.png");
        forestImage = new ImageIcon("images/"+Style+"/forest.png");

        coastImage = new ImageIcon("images/"+Style+"/coast.png");
        coastMultiImage = new ImageIcon("images/"+Style+"/coastmulti.png");
        coastUpImage = new ImageIcon("images/"+Style+"/coastup.png");
        coastDownImage = new ImageIcon("images/"+Style+"/coastdown.png");
        coastLeftImage = new ImageIcon("images/"+Style+"/coastleft.png");
        coastRightImage = new ImageIcon("images/"+Style+"/coastright.png");
        
        borderImage = new ImageIcon("images/"+Style+"/border.png");
        
        setTitle("Map Generator 3000 - Export");

        this.getContentPane().setPreferredSize(new Dimension(width,height));  
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);
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
                    if(displayMap[x][y].equals(Land)) //Checks to see if specified image is of type Land.
                        if(!displayMap[x][y-1].equals(Land) || !displayMap[x-1][y].equals(Land) || !displayMap[x][y+1].equals(Land) || !displayMap[x+1][y].equals(Land))
                        {
                            if(Math.random() > 0.8)
                            {
                                forestImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                            } else
                                landImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset); //Paints land image on screen.
                        } else
                            forestImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                    if(displayMap[x][y].equals(Hills)) //Checks to see if specified image is of type Hills.
                        hillsImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset); //Paints hills image on screen.
                    if(displayMap[x][y].equals(Sea)) //Checks to see if specified image is of type Sea.
                        seaImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset); //Paints sea image on screen.

                    int adjacentLand = 0; //Resets adjacentland int to zero each time.
                    if(displayMap[x][y].equals(Coast)) //Checks to see if specified image is of type Coast.
                    {
                        if(displayMap[x][y-1].equals(Land) || displayMap[x][y-1].equals(Hills))
                        {
                            coastLeftImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                            adjacentLand++;
                        }
                        if(displayMap[x][y+1].equals(Land) || displayMap[x][y+1].equals(Hills))
                        {
                            coastRightImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                            adjacentLand++;
                        }
                        if(displayMap[x-1][y].equals(Land) || displayMap[x-1][y].equals(Hills))
                        {
                            coastUpImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                            adjacentLand++;
                        }
                        if(displayMap[x+1][y].equals(Land) || displayMap[x+1][y].equals(Hills))
                        {
                            coastDownImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                            adjacentLand++;
                        }
                        if(adjacentLand >= 2)
                            coastMultiImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                        if(adjacentLand == 0)
                            coastImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                    }
                 } else
                 try{
                 borderImage.paintIcon(this, g, y*IMGSIZE+yOffset,x*IMGSIZE+xOffset);
                } catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println("//");
                    System.out.println(borderImage);
                    System.out.println("//");
                }
            }
        }
    }
}