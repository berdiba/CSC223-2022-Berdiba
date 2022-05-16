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

        System.out.println("MAP GENERATOR \n");
        System.out.println("Type START to begin. \n");

        StartPrompt = input.nextLine();
        if(StartPrompt == "START")
        {
            System.out.println('\u000c');
        }
    }

    public void GenerateMap()
    {
        Scanner input = new Scanner(System.in);

        int width = 20;
        int height = 20;

        int table[][] = new int[width][height];
        for (int x=0;x<width;x++)
        {
            for (int y=0;y<height;y++)
            {
                table[x][y]=(x+1)*(y+1);
                System.out.print(table[x][y]+" ");
            }
            System.out.println();
        }
    }
}