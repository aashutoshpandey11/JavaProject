package oopFinalAssignment;
import uk.ac.leedsbeckett.oop.LBUGraphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MainClass extends LBUGraphics
{

    public static void main(String[] args) 
    {
        new MainClass();
    }

    
    
    //This code is the constructor of a class called "MainClass".
    //This code creates a JFrame, adds a turtle graphics panel to it, and makes the frame visible.
    //This code uses a method called "displayMessage" to show the message "LBU Graphics Assessment".
    // The constructor also invokes a method named "about". 
    // A variable called "command_file" is initialized with the value "command.txt" in the constructor, but it is not utilized within the constructor.
    
    public MainClass() 
    {
   
        JFrame MainFrame = new JFrame("AASHUTOSH - 77356780"); //This code creates a frame to show a turtle panel.
        MainFrame.setLayout(new FlowLayout());                // set the layout manager of the frame to FlowLayout
        MainFrame.add(this);                                 // add the turtle graphics panel to the frame
                                                          
        MainFrame.pack();                                  // resize the frame to fit the components
        MainFrame.setVisible(true);                       // make the frame visible
        displayMessage("LBU Graphics Assestment");       //display a message using the displayMessage method
        //about();
    }
    String command_file ="command.txt";

    @Override
    public void processCommand(String s)           //The processCommand() method is used to process the user input command.
    {         

        if (!s.equals("Load Commands")) 
        	
        {
            try (FileWriter file = new FileWriter(command_file, true)) //The FileWriter class is used to write the command to a text file named command.txt, except for the "Load Commands" command.
            {  
                file.write(s + System.lineSeparator());
            } catch (Exception e)
            {
                System.out.println("Error responding commands. Try Again. : " + e);
                return;
            }
        }
 
        
// (about) command:-  
  //If the command is "about", the about() method is called to display information about the program.
        if(s.equals("about"))
        {                 
            about();
            return;
        }        

 
        
// (clear) command:-  
  //If the command is "clear", the clear_screen() method is called to clear the screen.          
        if (s.equals("clear"))
        {
            clear_screen();
            return;
        }       
        

        
// (reset) command:-  
  //If the command is "reset", the hard_reset() method is called to reset the turtle position and orientation.       
        if (s.equals("reset")) 
        {
            hard_reset();
            return;
        }        


        
// (pendown) command:-   
 //If the command is "pendown", the pen_down() method is called to lower the pen.        
        if (s.equals("pendown")) 
        {
            pen_down();
            return;
        }        
  
        

// (penup) Command:-            
//If the command is "penup", the pen_up() method is called to raise the pen.
        if (s.equals("penup")) 
        {
            pen_up();
            return;
        }
        

        
// (white) Color Command:-            
        if (s.equals("white")) 
        {
            pen_colour(Color.white);
            return;
        }        
        

        
// (black) Color Command:-           
        if (s.equals("black")) 
        {
            pen_colour(Color.black);
            return;
        }        

 
        
// (red) Color Command:-         
        if (s.equals("red"))
        {
            pen_colour(Color.red);
            return;
        }
        

        
// (green) Color Command:-
        if (s.equals("green")) 
        {
            pen_colour(Color.green);
            return;
        }        
        
 
        
// (exit) command:-
 //f the command is "exit", the chk_save() method is called to check if the user wants to save the commands and exit the program.       
        if (!s.equals("")) 
        {

            if (s.equals("exit")) 
            {
                boolean val = chk_save();
                if (val)
                {
                    System.exit(1);
                }
            }

            

// (Load Command):- 
 //If the command is "Load Commands", the load_command() method is called to load commands from the text file command.txt.           
            if(s.equals("Load Commands"))
            {
                load_command();
                return;
            }

            String[] lst1 = s.split(" ");
            String cmd = lst1[0];

            if (cmd.equals("Save")) 
            {
                String name = lst1[1];
                save_image(name);
                return;
            }

            if (cmd.equals("Load"))
            {
                String name = lst1[1];
                boolean chk = chk_save();

                if (!chk) {
                    String message = "Do you want to save this image?";
                    int a = JOptionPane.showConfirmDialog(null, message, "Error saving image!", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(a==0)
                    {
                        String save = "Save "+ JOptionPane.showInputDialog("Enter the file name : ");
                        processCommand(save);
                        load_image(name);
                        return;
                    }
                    if(a==1)
                    {
                        load_image(name);
                        return;
                    }
                    if(a==2 || a==-1){
                        return;
                    }
                }
                load_image(name);
                return;
            }

            try {
                String[] lst2 = lst1[1].split(",");
                int p1 = Integer.parseInt(lst2[0]);
                int p2 = Integer.parseInt(lst2[1]);
                int p3 = Integer.parseInt(lst2[2]);

                if (p1 < 0 || p2 < 0 || p3 < 0)
                {
                    System.out.println("Negative Parameter . Please Try Again.");
                    return;
                }

                switch (cmd) 
                {
                    case "bg"->{
                        set_background(p1,p2,p3);
                    }
                    case "pencolour" ->
                    {
                        pen_colour(p1, p2, p3);
                    }
                    case "triangle" ->
                    {
                        make_triangle(p1, p2, p3);
                    }
                }
            } catch (Exception ignore1) 
            {
                try 
                {
                    int p3 = Integer.parseInt(lst1[1]);
                    if (p3 < 0) 
                    {
                        System.out.println("Negative Parameter . Please Try Again.");
                        return;
                    }
                    switch (cmd) {
                        case "circle" -> 
                        {
                            make_circle(p3);
                        }
                        case "turnleft" -> 
                        {
                            turn_left(p3);
                        }
                        case "turnright" ->
                        {
                            turn_right(p3);
                        }
                        case "forward" -> 
                        {
                            go_forward(p3);
                        }
                        case "backward" ->
                        {
                            go_backward(p3);
                        }
                        case "penwidth" ->
                        {
                            pen_size(p3);
                        }
                        case "square" ->
                        {
                            make_square(p3);
                        }
                        case "triangle" ->
                        {
                            make_triangle(p3);
                        }
                        default -> System.out.println(" Command invalid. Try Again.");
                    }
                } 
                catch (Exception ignore2)
                {
                    if (cmd.equals("pencolour") || cmd.equals("bg")) 
                    {
                        System.out.println("Invalid parameters to " + cmd);
                        return;
                    }

                    List<String> data = Arrays.asList("circle", "turnleft", "turnright", "forward", "backward", "triangle");
                    if (data.contains(cmd)) 
                    {
                        System.out.println("Non-numeric Parameter. Try Again.");
                    } 
                    else
                    {
                        System.out.println(" Command invalid. Try Again.");
                    }
                }
            }
        }
        else 
        {
            String message = "Input something and try again!";
            JOptionPane.showMessageDialog(null, message, "No Input!", JOptionPane.WARNING_MESSAGE);
        }
    }

    

//This code is used to create a triangle:-   
    public void make_square(int a)
    {
        penUp();
        forward(a / 2);
        penDown();
        turnRight();
        forward(a / 2);
        turnRight();
        forward(a);
        turnRight();
        forward(a);
        turnRight();
        forward(a);
        turnRight();
        forward(a / 2);
        reset();
        System.out.println("Making a square of side :" + a);
    }

    

//This code is used to create a triangle:-
    public void make_triangle(int a) 
    {
        int h = (int) ((a * Math.sqrt(3)) / 2);
        penUp();
        forward(h / 2);
        penDown();
        turnRight();
        forward(a / 2);
        turnRight(120);
        forward(a);
        turnRight(120);
        forward(a);
        turnRight(120);
        forward(a / 2);
        reset();
        System.out.println("Making a triangle of side : " + a);
    }

    public void make_triangle(int a, int b, int c) 
    {
        double angleA = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2.0 * b * c)));
        double angleB = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2.0 * a * c)));
        double angleC = Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2.0 * a * b)));
        double s = (a + b + c) / 2.0;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        double h = (2 * area) / a;

        penUp();
        forward((int) (h / 2));
        penDown();
        turnLeft();
        forward(a / 2);
        turnLeft((int) (180 - angleC));
        forward(b);
        turnLeft((int) (180 - angleA));
        forward(c);
        turnLeft((int) (180 - angleB));
        forward(a);
        reset();
        System.out.println("Making triangle of side " + a + ", " + b + " and " + c);
    }

    public void pen_size(int a) 
    {
        penSize = a;
    }

    public void pen_colour(Color name) 
    {
        setPenColour(name);
        System.out.println("Changing pen-colour to : " + getPenColour());
    }

    public void pen_colour(int a, int b, int c) 
    {
        Color temp_color = new Color(a, b, c);
        setPenColour(temp_color);
        System.out.println("Changing pen-colour to " + getPenColour());
    }

    public void hard_reset()
    {
        reset();
        penDown();
        penSize = 1;
        setPenColour(Color.RED);
        System.out.println("Reset Pressed");
    }
    
    
    
//Comments for load_image:-
    //(409) try-catch block to handle exceptions while reading the image file. 
    //(412) Set the buffered image object as the current image.
    //(415) Catch the IOException thrown by ImageIO.read() if the file cannot be found or read.
    
    public void load_image(String name) 
    {
        try {   
            File file = new File("images" + name + ".png");
            BufferedImage image = ImageIO.read(file);
            setBufferedImage(image);
            System.out.println("Loading the image : " + name);
        } 
        catch (IOException e) 
        {
            System.out.println( "file named cannot be load " + name + ".png.\nError : " + e);
        }
    }

    
    
//Comments for save_immage:-
    //(432) The ImageIO.write() method is then used to write the bufImg object to the file in PNG format.
    //(435) If an IOException occurs during the write operation, an error message is printed to the console.
    
    public void save_image(String name)
    {
        BufferedImage bufImg = getBufferedImage();
        File output = new File("Image" + name + ".png");
        try {
            ImageIO.write(bufImg, "png", output);
            JOptionPane.showMessageDialog(null,"Image Saved!");
        } 
        catch (IOException e)
        {
            System.out.println("file named cannot be saved" + name + ".png.\nError : " + e);
        }
    }


    public void clear_screen()
    {
        clear();
        System.out.println("Cleared ! commmand entered successfully");
    }

    public void pen_down() 
    {
        penDown();
        System.out.println("Pen Down ! commmand entered successfully");
    }

    public void pen_up() 
    {
        penUp();
        System.out.println("Pen Up ! commmand entered successfully");
    }

    public void make_circle(int a) 
    {
        circle(a);
        System.out.println("Creating a circle with radius : " + a);
    }

    public void turn_left(int a) 
    {
        turnLeft(a);
        System.out.println("Turning Left on " + a + " degree.");
    }

    public void turn_right(int a)
    {
        turnRight(a);
        System.out.println("Turning Right on " + a + " degree.");
    }

    public void go_forward(int a) 
    {
        forward(a);
        System.out.println("Moving " + a + " pixels forward.");
    }

    public void go_backward(int a) 
    {
        forward(-a);
        System.out.println("Moving " + a + " pixels backward.");
    }

    public void load_command()
    {
        String fileName = " command.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                processCommand(line);
            }
        } 
        catch (IOException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    
    
//Comments For Public Boolen chk_save:-
 //(515) Boolean function checks if the user has issued a "Save" command in the input history file. It takes no arguments and returns a boolean value.
 //(517) BufferedReader to read the command file. 
 //(524) "arr" reads each line of the file and stores it in an array.
 //(528) "val" variable is assigned the second-to-last command stored in the array.  
 //(533) "substring()" method is used to extract a specific portion of a string.    
    public boolean chk_save()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(command_file))) 
        {
            String line;
            int i = 0;
            String[] arr = new String[100];
            while ((line = reader.readLine()) != null) 
            {
                arr[i] = line;
                i = i + 1;
            }
            
            String val = arr[i - 2];
            int space_index = val.indexOf(" ");

            if (space_index != -1) 
            {
                String save = val.substring(0, space_index);
                if (save.equals("Save"))
                    return true;
            }
        } 
        catch (IOException e) 
        {
            String message="Can't Open the file";
            JOptionPane.showMessageDialog(null,message,"",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public void about() 
    {
        super.about();
        clear();
        penUp();
    setPenColour(Color.blue);
   
   
  //FOR LETTER "A"

    penUp();
   
    turnRight(90);
   
    setPenColour(Color.blue);

    forward(350);

    penDown();
   
    turnLeft(75);

    forward(130);

    forward(-60);

    turnLeft(103);

    forward(50);

    turnLeft(115);

    forward(70);

    forward(-130);
   
   
   //for letter A
   
   penUp();
   turnRight(100);
   forward(20);
   
   penDown();
   turnLeft(60);
   forward(120);
   forward(-60);
   
   turnRight(70);
   forward(40);
   turnRight(70);
   forward(60);
   forward(-120);
 
   
   //for letter S
   penUp();

   turnLeft(68);
   forward(60);
   
   setPenColour(Color.red);
   
   penDown();
   
   forward(50);
   
   forward(-50);
   
   turnRight(90);
   forward(55);
   
   turnLeft(90);
   forward(50);
   
   turnRight(90);
   forward(60);
   
   turnRight(90);
   forward(50);

   
   //for letter H
   
   penUp();

   setPenColour(Color.green);

   forward(-65);

   turnRight(90);

   penDown();

   forward(110);

   forward(-60);

   turnRight(90);

   forward(60);

   turnLeft(90);

   forward(60);

   forward(-110);

   
   //for letter "U"
   
   penUp();
   setPenColour(Color.GRAY);

   turnRight(90);
   forward(80);

   	penDown();

   	turnRight(270);

   	forward(110);

   	forward(-110);

   	turnLeft(90);

   	forward(60);

   	turnRight(90);

   	forward(110);
   
   	
   	//for letter T
   	penUp();
   	setPenColour(Color.pink);

   	turnRight(90);
   	forward(80);

   	penDown();
   	forward(100);
   	penUp();
   	forward(-50);
   	penDown();
   	turnRight(90);
   	forward(120);
   	
   	
   	//for letter O
   	
   	penUp();
   	setPenColour(Color.blue);
   	turnLeft(90);
   	forward(40);
   	
   	penDown();
   	
   	turnLeft(90);
   	forward(100);
   	
   	turnRight(90);
   	forward(70);
   	
   	turnRight(90);
   	forward(100);
   	
   	turnRight(90);
   	forward(70);
   
   	
   	
   	//for letter S
   	penUp();

    forward(-130);
    
    setPenColour(Color.red);
    
    penDown();
    
    forward(40);
    
    forward(-50);
    
    turnRight(90);
    forward(50);
    
    turnLeft(90);
    forward(50);
    
    turnRight(90);
    forward(50);
    
    turnRight(90);
    forward(50);
    
    // for letter H
    penUp();

    setPenColour(Color.green);

    forward(80);

    turnRight(90);

    penDown();

    forward(110);

    forward(-60);

    turnRight(90);

    forward(60);

    turnLeft(90);

    forward(60);

    forward(-110);

   	
        reset();
    }

    private void penUp(int i) 
    {
// TODO Auto-generated method stub

}

public void set_background(int a, int b, int c)
{
        Color col = new Color(a,b,c);
        setBackground_Col(col);
        clear();
    }
}