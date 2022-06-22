/**
 * @author Baxter J B Berdinner
 * @version 16/05/2022
 */

//Import nessesary java extensions
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main
{
    //Declaration of default map dimensions.
    int width = 32;
    int height = 32;
    int depth = 128;
    
    //Create 2D array that holds map
    String map[][] = new String[height][width];
    
    //Variables for types of pixels on map.
    public String Land = ("██");
    public String Hills = ("▓▓");
    public String Coast = ("▒▒");
    public String Sea = ("░░");
    
    String StartPrompt = new String();

    public Main()
    {
        StartText();
    }

    public void StartText()
    {
        //Clear screen and print out starting text.
        System.out.print('\u000c');

        System.out.println("█▀▄▀█ ▄▀█ █▀█   █▀▀ █▀▀ █▄░█ █▀▀ █▀█ ▄▀█ ▀█▀ █▀█ █▀█   █▀█ █▀█ █▀█ █▀█");
        System.out.println("█░▀░█ █▀█ █▀▀   █▄█ ██▄ █░▀█ ██▄ █▀▄ █▀█ ░█░ █▄█ █▀▄   ▀▀█ █▄█ █▄█ █▄█\n");     

        System.out.println("Type \"START\" to begin.");
        System.out.println("Type \"MAP\" to customise map.");
        System.out.println("Type \"INFO\" for info about this project.");
        System.out.println("Type \"QUIT\" to quit. \n");

        System.out.println("\nType \"3D\" for experimental 3D feature.\n");

        StartSettings();
    }

    public void AltStartText()
    {
        //Same as start text, but does not clear screen or display title.

        System.out.println("Type \"START\" to begin.");
        System.out.println("Type \"MAP\" to customise map.");
        System.out.println("Type \"INFO\" for info about this project.");
        System.out.println("Type \"QUIT\" to quit. \n");

        System.out.println("\nType \"3D\" for experimental 3D feature.\n");

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
        if(StartPrompt.equalsIgnoreCase("3D"))
        {
            System.out.print('\u000c');
            GenerateMap3D();
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
                try {TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){} //Wait 1 second
            } else
            {
                System.out.println("Failed to set HEIGHT: non-numerical input \n");
                try {TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){} //Wait 1 second
            }

            AltStartText();
        } else 
        if(StartPrompt.equalsIgnoreCase("INFO"))
        {
            System.out.print('\u000c');

            System.out.println("█▀▄▀█ ▄▀█ █▀█   █▀▀ █▀▀ █▄░█ █▀▀ █▀█ ▄▀█ ▀█▀ █▀█ █▀█   █▀█ █▀█ █▀█ █▀█");
            System.out.println("█░▀░█ █▀█ █▀▀   █▄█ ██▄ █░▀█ ██▄ █▀▄ █▀█ ░█░ █▄█ █▀▄   ▀▀█ █▄█ █▄█ █▄█\n");     

            System.out.println("Made by BERDIBA\n");
            System.out.println("Map Width: " + width);
            System.out.println("Map Height: " + height);
            System.out.println("Map Depth: " + depth + "\n");

            try {TimeUnit.MILLISECONDS.sleep(4000);} catch(InterruptedException e){}
            AltStartText();
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
        double hillChance = 0.1;

        //Initial map generation
        for (int x=0;x<height;x++)
        {
            for (int y=0;y<width;y++)
            {
                if(x > 0 && x < height-1 && y > 0 && y < width-1)
                {
                    if(Math.random() <= landChance)
                    {
                        map[x][y] = Land;

                        if (x > 1)
                        {
                            if (map[x-1][y].equals(Land))
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
                        map[x][y] = Sea;

                        if (x > 1)
                        {
                            if (map[x-1][y].equals(Sea))
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
                    map[x][y] = Coast;
                }
            }
        }
        //Generation of coastline.
        for (int x=0;x<height;x++)
        {
            for (int y=0;y<width;y++)
            {
                if(x > 0 && x < height-1 && y > 0 && y < width-1)
                {
                    if(map[x-1][y].equals(Sea) || map[x][y-1].equals(Sea) || map[x+1][y].equals(Sea) || map[x][y+1].equals(Sea))
                    {
                        if(!map[x][y].equals(Sea))
                        {
                            map[x][y] = Coast;
                        }
                    } 
                } 
            }
        }
        //Generation of hills.
        for (int x=0;x<height;x++)
        {
            for (int y=0;y<width;y++)
            {
                if(x > 0 && x < height-1 && y > 0 && y < width-1)
                {
                    if(map[x][y].equals(Land))
                    {
                        if(Math.random() <= hillChance)
                        {
                            map[x][y] = Hills;
                        }
                    } 
                } 
            }
        }

        //Map printing.
        for (int x=0;x<height;x++)
        {
            for (int y=0;y<width;y++)
            {
                System.out.print(map[x][y]);
            }
            System.out.println();
            try {TimeUnit.MILLISECONDS.sleep(10);} catch(InterruptedException e){}
        }
        
        MapText();
    }

    public void MapText()
    {
        System.out.println("Type \"REGEN\" to generate new map.");
        System.out.println("Type \"EXPORT\" to convert map to image.");
        System.out.println("Type \"MENU\" to return to main menu.");
        System.out.println("Type \"QUIT\" to quit.");

        MapSettings();
    }

    public void MapText3D()
    {
        System.out.println("Type \"REGEN\" to generate new map.");
        System.out.println("Type \"MENU\" to return to main menu.");
        System.out.println("Type \"QUIT\" to quit.");

        MapSettings3D();
    }

    public void MapSettings()
    {
        Scanner input = new Scanner(System.in);

        //If statements for character input.
        StartPrompt = input.nextLine();
        if(StartPrompt.equalsIgnoreCase("REGEN"))
        {
            System.out.print('\u000c');
            GenerateMap();
        } else
        if(StartPrompt.equalsIgnoreCase("EXPORT"))
        {
            System.out.print("Exporting map...");
            MapDisplay md = new MapDisplay(map);
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
            StartText();
        } else 
        {
            System.out.println("Unknown command. Please try again \n");
            MapSettings();
        }
    }

    public void MapSettings3D()
    {
        Scanner input = new Scanner(System.in);

        //If statements for character input.
        StartPrompt = input.nextLine();
        if(StartPrompt.equalsIgnoreCase("REGEN"))
        {
            System.out.print('\u000c');
            GenerateMap3D();
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
            StartText();
        } else 
        {
            System.out.println("Unknown command. Please try again \n");
            MapSettings3D();
        }
    }

    public void GenerateMap3D()
    {
        boolean land; //Variable used to check if any land is on screen.
        double landChance = 0;

        depth = 128; //Set depth back to 128, as it will have been changed if this map has been previously generated.

        String map[][][] = new String[depth][height][width];
        //Map generation
        for (int z=0;z<depth;z++)
        {
            for (int x=0;x<height;x++)
            {
                for (int y=0;y<width;y++)
                {
                    if(z == 0) //Generate the starting map from which the following maps will be variations of.
                    {
                        if(x > 0 && x < height-1 && y > 0 && y < width-1)
                        {
                            if(Math.random() <= landChance)
                            {
                                map[z][x][y] = Land;
                                if (x > 1)
                                {
                                    if (map[z][x-1][y].equals(Land))
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
                                map[z][x][y] = Sea;

                                if (x > 1)
                                {
                                    if (map[z][x-1][y].equals(Sea))
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
                            map[z][x][y] = Coast;
                        }
                    } else
                    {
                        //Changes coastline into sea.
                        if(z > 0 && z < depth-1 && x > 0 && x < height-1 && y > 0 && y < width-1) //Checks to see if map is within bounds.
                        {
                            if(map[z-1][x][y].equals(Coast))
                            {
                                map[z-1][x][y] = Sea;
                            } else
                            {
                                map[z][x][y] = map[z-1][x][y];
                            }
                        }
                        //Changes land into coastline.
                        if(z > 0 && z < depth-1 && x > 0 && x < height-1 && y > 0 && y < width-1) //Checks to see if map is within bounds.
                        {
                            if(map[z-1][x-1][y].equals(Sea) || map[z-1][x][y-1].equals(Sea) || map[z-1][x+1][y].equals(Sea) || map[z-1][x][y+1].equals(Sea))
                            {
                                if(!map[z-1][x][y].equals(Sea))
                                {
                                    map[z][x][y] = Coast;
                                } else
                                {
                                    map[z][x][y] = map[z-1][x][y];
                                }
                            } else
                            {
                                map[z][x][y] = map[z-1][x][y];
                            }
                        } else
                        {
                            map[z][x][y] = map[z-1][x][y];
                        }
                    }
                }
            }

            land = false;

            //Checks to see if there is any land on the board.
            for (int x=0;x<height;x++)
            {
                for (int y=0;y<width;y++)
                {
                    if(map[z][x][y].equals(Land)) 
                    {
                        land = true;
                    }
                }
            }

            if (land == false) //If no land is found on the given layer
            {
                depth = z + 1;
            }
        }

        //Map printing
        for (int a=0;a<100;a++)
        {
            //Print map and go through slices upwards
            for (int z=0;z<depth;z++)
            {
                System.out.print('\u000c'); //Clear pervious sice of map before generating next one.
                for (int x=0;x<height;x++)
                {
                    for (int y=0;y<width;y++)
                    {
                        System.out.print(map[z][x][y]); //Print map.
                    }
                    System.out.println(); //Return after printing line.
                }
                try {TimeUnit.MILLISECONDS.sleep(20);} catch(InterruptedException e){}
            }
            //Go back doownwards through slices.
            for (int z=depth-1;z>-1;z--)
            {
                System.out.print('\u000c'); //Clear pervious sice of map before generating next one.
                for (int x=height-1;x>-1;x--)
                {
                    for (int y=width-1;y>-1;y--)
                    {
                        System.out.print(map[z][x][y]); //Print map.
                    }
                    System.out.println(); //Return after printing line.
                }
                try {TimeUnit.MILLISECONDS.sleep(20);} catch(InterruptedException e){}
            }
            try {TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e){}
        }

        MapText3D();
    }
}

//TO DO: Seperate map generation from map display. Comment work. Make 3D map generation loop. Make Mountains.
//PROBLEMS: Map 3D first slice is not good. Try removing it.