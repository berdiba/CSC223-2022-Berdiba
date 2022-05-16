/**
 * @author Baxter J B Berdinner
 * @version 16/05/2022
 */

import java.util.Scanner;

public class Main
{
    public Main()
    {
        Scanner input = new Scanner(System.in);
        String StartPrompt = new String();
        
        //Clear screen and print out starting text.
        System.out.print('\u000c');
        System.out.println("MAP GENERATOR 9000 \n");
        System.out.println("Type \"START\" to begin.");
        System.out.println("Type \"MAP\" to customise map.");
        System.out.println("Type \"QUIT\" to quit. \n");

        //If statements for character input.
        StartPrompt = input.nextLine();
        if(StartPrompt.equalsIgnoreCase("START"))
        {
            System.out.print('\u000c');
            GenerateMap();
        } else 
        if(StartPrompt.equalsIgnoreCase("MAP"))
        {
            System.out.print('\u000c');
        } else 
        if(StartPrompt.equalsIgnoreCase("QUIT"))
        {
            System.out.print('\u000c');
        }
    }

    public void GenerateMap()
    {
        Scanner input = new Scanner(System.in);

        int width = 20;
        int height = 20;

        int map[][] = new int[width][height];
        for (int x=0;x<width;x++)
        {
            for (int y=0;y<height;y++)
            {
                map[x][y]=(x+1)*(y+1);
                System.out.print(map[x][y]+" ");
            }
            System.out.println();
        }
    }
}