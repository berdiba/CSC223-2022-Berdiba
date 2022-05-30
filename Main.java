/**
 * @author Baxter J B Berdinner
 * @version 16/05/2022
 */

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main
{
    int width = 32;
    int height = 32;

    String StartPrompt = new String();

    public Main()
    {
        Start();
    }

    public void Start()
    {
        //Clear screen and print out starting text.
        System.out.print('\u000c');
        System.out.println("MAP GENERATOR 9000 \n");
        System.out.println("Type \"START\" to begin.");
        System.out.println("Type \"MAP\" to customise map.");
        System.out.println("Type \"QUIT\" to quit. \n");
        
        StartSettings();
    }

    public void StartSettings()
    {
        Scanner input = new Scanner(System.in);

        //If statements for character input.
        StartPrompt = input.nextLine();
        if(StartPrompt.equalsIgnoreCase("START"))
        {
            System.out.print('\u000c');
            GenerateMap();
        } else 
        if(StartPrompt.equalsIgnoreCase("MAP"))
        {
            //Change map width and height.
            System.out.print('\u000c');

            System.out.println("Set map WIDTH: \n");
            String Width = input.nextLine().replaceAll("\\D", "");
            if(!Width.equals("")) //If statement checks if Width string is empty
            {
                width = Integer.parseInt(Width);
                System.out.println("WIDTH set to "+width+"\n");
            } else
            {
                System.out.println("Failed to set WIDTH: non-numerical input \n");
            }

            System.out.println("Set map HEIGHT: \n");
            String Height = input.nextLine().replaceAll("\\D", "");
            if(!Height.equals("")) //If statement checks if Height string is empty
            {
                height = Integer.parseInt(Height);
                System.out.println("HEIGHT set to "+height+"\n");
                try {TimeUnit.SECONDS.sleep(2);} catch(InterruptedException e){} //Wait 1 second
            } else
            {
                System.out.println("Failed to set HEIGHT: non-numerical input \n");
                try {TimeUnit.SECONDS.sleep(2);} catch(InterruptedException e){} //Wait 1 second
            }

            Start();
            StartSettings();
        } else 
        if(StartPrompt.equalsIgnoreCase("QUIT"))
        {
            //Terminates program.
            System.out.print('\u000c');

            System.exit(0);
        } else 
        {
            System.out.println("Unknown command. Please try again \n");
            StartSettings();
        }
    }

    public void GenerateMap()
    {
        Scanner input = new Scanner(System.in);

        double landChance = 0;

        String map[][] = new String[height][width];
        for (int x=0;x<height;x++)
        {
            for (int y=0;y<width;y++)
            {
                if(x > 0 && x < height-1 && y > 0 && y < width-1)
                {
                    if(Math.random() <= landChance)
                    {
                        map[x][y] = "██";
                        System.out.print(map[x][y]);

                        if (x > 1)
                        {
                            if (map[x-1][y].equals("██"))
                            {
                                landChance = .98;
                            } else
                            {
                                landChance = .60;
                            }
                        } else
                        {
                            landChance = .90;
                        }
                    } else
                    {
                        map[x][y] = "░░";
                        System.out.print(map[x][y]);

                        if (x > 1)
                        {
                            if (map[x-1][y].equals("░░"))
                            {
                                landChance = .02;
                            } else
                            {
                                landChance = .40;
                            }
                        } else
                        {
                            landChance = .10;
                        }
                    }
                } else
                {
                    map[x][y] = "▒▒";
                    System.out.print(map[x][y]);
                }
            }
            System.out.println();
            try {TimeUnit.MILLISECONDS.sleep(10);} catch(InterruptedException e){}
        }

        System.out.print('\u000c');

        for (int x=0;x<height;x++)
        {
            for (int y=0;y<width;y++)
            {
                if(x > 0 && x < height-1 && y > 0 && y < width-1)
                {
                    if(map[x-1][y].equals("░░") || map[x][y-1].equals("░░") || map[x+1][y].equals("░░") || map[x][y+1].equals("░░"))
                    {
                        if(!map[x][y].equals("░░"))
                        {
                            map[x][y] = "▒▒";
                            System.out.print(map[x][y]);
                        } else
                        {
                            System.out.print(map[x][y]);
                        }
                    } else
                    {
                        System.out.print(map[x][y]);
                    }
                } else
                {
                    System.out.print(map[x][y]);
                }
            }
            System.out.println();
        }

        MapSettings();
    }

    public void MapSettings()
    {
        System.out.println("Type \"REGEN\" to generate new map.");
        System.out.println("Type \"MENU\" to return to main menu.");
        System.out.println("Type \"QUIT\" to quit.");
        
        Scanner input = new Scanner(System.in);

        //If statements for character input.
        StartPrompt = input.nextLine();
        if(StartPrompt.equalsIgnoreCase("REGEN"))
        {
            System.out.print('\u000c');
            GenerateMap();
        } else
        if(StartPrompt.equalsIgnoreCase("QUIT"))
        {
            //Terminates program.
            System.out.print('\u000c');

            System.exit(0);
        } else 
        if(StartPrompt.equalsIgnoreCase("MENU"))
        {
            System.out.print('\u000c');
            Start();
        }
    }
}