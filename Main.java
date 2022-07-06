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
    
    int repeats = 4;

    //Create 2D array that holds map
    public String map[][];

    //Variables for types of pixels on map.
    public String Land = ("██");
    public String Hills = ("▓▓");
    public String Coast = ("▒▒");
    public String Sea = ("░░");

    //Creates new string "Prompt" which will be used to store keyboard input.
    String Prompt = new String();

    //Creates new keyboard scanner "input" which reads keyboard.
    Scanner input = new Scanner(System.in);
    
    String Style = new String();

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

        StartSettings(); //Runs function StartSettings.
    }

    public void AltStartText()
    {
        //Same as start text, but does not clear screen or display title.

        System.out.println("Type \"START\" to begin.");
        System.out.println("Type \"MAP\" to customise map.");
        System.out.println("Type \"INFO\" for info about this project.");
        System.out.println("Type \"QUIT\" to quit. \n");

        System.out.println("\nType \"3D\" for experimental 3D feature.\n");

        StartSettings(); //Runs function StartSettings.
    }

    public void StartSettings()
    {
        Prompt = input.nextLine(); //Set "Prompt" to  be keyboard input.

        if(Prompt.equalsIgnoreCase("START")) //Checks to see if "Prompt" is equal to "START." Not case-sensitive.
        {
            System.out.print('\u000c'); //Clears screen
            GenerateMap(); //runs function GenerateMap.
        } else 
        if(Prompt.equalsIgnoreCase("3D"))
        {
            System.out.print('\u000c');
            GenerateMap3D();
        } else 
        if(Prompt.equalsIgnoreCase("MAP"))
        {
            System.out.print('\u000c'); //Clear screen.

            //Set map width and height.
            System.out.println("Set map WIDTH: \n");
            String Width = input.nextLine().replaceAll("\\D", ""); //Sets new String "Width" to be keyboard input. Removes non-numerical text in input.
            if(!Width.equals("")) //If statement checks if Width string is empty
            {
                width = Integer.parseInt(Width); //Parses String "Width" into int "width."
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
        if(Prompt.equalsIgnoreCase("INFO"))
        {
            System.out.print('\u000c');

            System.out.println("█▀▄▀█ ▄▀█ █▀█   █▀▀ █▀▀ █▄░█ █▀▀ █▀█ ▄▀█ ▀█▀ █▀█ █▀█   █▀█ █▀█ █▀█ █▀█");
            System.out.println("█░▀░█ █▀█ █▀▀   █▄█ ██▄ █░▀█ ██▄ █▀▄ █▀█ ░█░ █▄█ █▀▄   ▀▀█ █▄█ █▄█ █▄█\n");     

            System.out.println("Made by BERDIBA");
            System.out.println("EXPORT code inspired by Bill Viggers.\n");
            System.out.println("All other code and images original.\n");

            System.out.println("Map Width: " + width);
            System.out.println("Map Height: " + height);
            System.out.println("Map Depth: " + depth + "\n");

            try {TimeUnit.MILLISECONDS.sleep(4000);} catch(InterruptedException e){}
            AltStartText();
        } else
        if(Prompt.equalsIgnoreCase("QUIT"))
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
        //Variables for chances of generating hills and land.
        double landChance = 0;
        double hillChance = 0.1;

        map = new String[height][width]; //2D string array in which the map will be stored.

        //Initial map generation
        for (int y=0;y<height;y++)
        {
            for (int x=0;x<width;x++)
            {
                if(y > 0 && y < height-1 && x > 0 && x < width-1) //Checks to see if specified pixel is within bounds of map.
                {
                    if(Math.random() <= landChance)
                    {
                        map[y][x] = Land;

                        if (y > 1)
                        {
                            if (map[y-1][x].equals(Land))
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
                        map[y][x] = Sea;

                        if (y > 1)
                        {
                            if (map[y-1][x].equals(Sea))
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
                    map[y][x] = Coast;
                }
            }
        }
        //Generation of coastline.
        for (int y=0;y<height;y++)
        {
            for (int x=0;x<width;x++)
            {
                if(y > 0 && y < height-1 && x > 0 && x < width-1)
                {
                    if(map[y-1][x].equals(Sea) || map[y][x-1].equals(Sea) || map[y+1][x].equals(Sea) || map[y][x+1].equals(Sea))
                    {
                        if(!map[y][x].equals(Sea))
                        {
                            map[y][x] = Coast;
                        }
                    } 
                } 
            }
        }
        //Generation of hills.
        for (int y=0;y<height;y++)
        {
            for (int x=0;x<width;x++)
            {
                if(y > 0 && y < height-1 && x > 0 && x < width-1)
                {
                    if(map[y][x].equals(Land))
                    {
                        if(Math.random() <= hillChance)
                        {
                            map[y][x] = Hills;
                        }
                    } 
                } 
            }
        }

        //Map printing.
        for (int y=0;y<height;y++)
        {
            for (int x=0;x<width;x++)
            {
                System.out.print(map[y][x]);
            }
            System.out.println();
            try {TimeUnit.MILLISECONDS.sleep(10);} catch(InterruptedException e){}
        }

        MapText();
        MapSettings();
    }

    public void MapText()
    {
        System.out.println("Type \"REGEN\" to generate new map.");
        System.out.println("Type \"EXPORT\" to convert map to image.");
        System.out.println("Type \"MENU\" to return to main menu.");
        System.out.println("Type \"QUIT\" to quit.");
    }

    public void MapText3D()
    {
        System.out.println("Type \"REGEN\" to generate new map.");
        System.out.println("Type \"MENU\" to return to main menu.");
        System.out.println("Type \"QUIT\" to quit.");
    }

    public void MapSettings()
    {
        //If statements for character input.
        Prompt = input.nextLine();
        if(Prompt.equalsIgnoreCase("REGEN"))
        {
            System.out.print('\u000c');
            GenerateMap();
        } else
        if(Prompt.equalsIgnoreCase("EXPORT"))
        {
            ExportMap();
        } else
        if(Prompt.equalsIgnoreCase("QUIT"))
        {
            //Terminates program.
            System.out.print('\u000c');

            System.exit(0);
        } else 
        if(Prompt.equalsIgnoreCase("MENU"))
        {
            System.out.print('\u000c');
            StartText();
        } else 
        {
            System.out.println("Unknown command. Please try again \n");
            MapSettings();
        }
    }

    public void ExportMap()
    {
        System.out.println("Select map style. Avalable styles: \"MINECRAFT\" \"PIXEL\"\n");
        Prompt = input.nextLine();
        if(Prompt.equalsIgnoreCase("PIXEL"))
        Style = "pixel";
        if(Prompt.equalsIgnoreCase("MINECRAFT"))
        Style = "minecraft";
        
        System.out.println("Exporting map...");
        MapDisplay md = new MapDisplay(map, Style);
    }

    public void MapSettings3D()
    {
        //If statements for character input.
        Prompt = input.nextLine();
        if(Prompt.equalsIgnoreCase("REGEN"))
        {
            System.out.print('\u000c');
            GenerateMap3D();
        } else
        if(Prompt.equalsIgnoreCase("QUIT"))
        {
            //Terminates program.
            System.out.print('\u000c');

            System.exit(0);
        } else 
        if(Prompt.equalsIgnoreCase("MENU"))
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
        boolean land; //Variable used to check if anx land is on screen.
        double landChance = 0;

        depth = 128; //Set depth back to 128, as it will have been changed if this map has been previouslx generated.

        String map[][][] = new String[depth][height][width];
        //Map generation
        for (int z=0;z<depth;z++)
        {
            for (int y=0;y<height;y++)
            {
                for (int x=0;x<width;x++)
                {
                    if(z == 0) //Generate the starting map from which the following maps will be variations of.
                    {
                        if(y > 0 && y < height-1 && x > 0 && x < width-1)
                        {
                            if(Math.random() <= landChance)
                            {
                                map[z][y][x] = Land;
                                if (y > 1)
                                {
                                    if (map[z][y-1][x].equals(Land))
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
                                map[z][y][x] = Sea;

                                if (y > 1)
                                {
                                    if (map[z][y-1][x].equals(Sea))
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
                            map[z][y][x] = Coast;
                        }
                    } else
                    {
                        //Changes coastline into sea.
                        if(z > 0 && z < depth-1 && y > 0 && y < height-1 && x > 0 && x < width-1) //Checks to see if map is within bounds.
                        {
                            if(map[z-1][y][x].equals(Coast))
                            {
                                map[z-1][y][x] = Sea;
                            } else
                            {
                                map[z][y][x] = map[z-1][y][x];
                            }
                        }
                        //Changes land into coastline.
                        if(z > 0 && z < depth-1 && y > 0 && y < height-1 && x > 0 && x < width-1) //Checks to see if map is within bounds.
                        {
                            if(map[z-1][y-1][x].equals(Sea) || map[z-1][y][x-1].equals(Sea) || map[z-1][y+1][x].equals(Sea) || map[z-1][y][x+1].equals(Sea))
                            {
                                if(!map[z-1][y][x].equals(Sea))
                                {
                                    map[z][y][x] = Coast;
                                } else
                                {
                                    map[z][y][x] = map[z-1][y][x];
                                }
                            } else
                            {
                                map[z][y][x] = map[z-1][y][x];
                            }
                        } else
                        {
                            map[z][y][x] = map[z-1][y][x];
                        }
                    }
                }
            }

            land = false;

            //Checks to see if there is anx land on the board.
            for (int y=0;y<height;y++)
            {
                for (int x=0;x<width;x++)
                {
                    if(map[z][y][x].equals(Land)) 
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
        for (int a=0;a<repeats;a++)
        {
            //Print map and go through slices upwards
            for (int z=0;z<depth;z++)
            {
                System.out.print('\u000c'); //Clear pervious sice of map before generating next one.
                for (int y=0;y<height;y++)
                {
                    for (int x=0;x<width;x++)
                    {
                        System.out.print(map[z][y][x]); //Print map.
                    }
                    System.out.println(); //Return after printing line.
                }
                try {TimeUnit.MILLISECONDS.sleep(20);} catch(InterruptedException e){}
            }
            //Go back doownwards through slices.
            for (int z=depth-1;z>-1;z--)
            {
                System.out.print('\u000c'); //Clear pervious sice of map before generating next one.
                for (int y=height-1;y>-1;y--)
                {
                    for (int x=width-1;x>-1;x--)
                    {
                        System.out.print(map[z][y][x]); //Print map.
                    }
                    System.out.println(); //Return after printing line.
                }
                try {TimeUnit.MILLISECONDS.sleep(20);} catch(InterruptedException e){}
            }
            try {TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e){}
        }

        MapText3D();
        MapSettings3D();
    }
}

//TO DO: Comment work. Make 3D map generation loop. 
//PROBLEMS: Map 3D first slice is not good. Try removing it.