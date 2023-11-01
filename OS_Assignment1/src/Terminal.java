import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//error detection
//2- input the two command > and >>

public class Terminal {
    private final Parser parser = new Parser();

    //======================================================================================================================
    private final String currentDirectory = System.getProperty("user.dir");

    //======================================================================================================================
    private final List<String> history = new ArrayList<>();




    //======================================================================================================================

    public void chooseCommandAction(String command, String[] args ) {
        switch (command) {
            case "echo":
                echo(args);
                break;
            case "pwd":
                pwd(args);
                break;
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            case "mkdir":
                mkdir(args);
                break;
            case "rmdir":
                rmdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "cp":
                cp(args);
                break;
            case "rm":
                rm(args);
                break;
            case "cat":
                cat(args);
                break;
            case "wc":
                wc(args);
                break;
            case "history":
                history(args);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                history.remove(history.size()-1);
                System.out.println("Unknown command: " + command);
        }
    }

    //======================================================================================================================

    public void echo(String[] args) {
        if (args.length == 1) {

        } else if (args.length > 1) {

        } else {
            history.remove(history.size()-1);
            System.out.println("Usage: echo [message]");
        }
    }

    //======================================================================================================================

    public void pwd(String[] args) {
        if(args.length ==0){

        }
        else {
            history.remove(history.size()-1);
            System.out.println("Usage: pwd");
        }
    }

    //======================================================================================================================

    public void cd(String[] args) {
        if(args.length == 0){

        }
        else if(args.length == 1){

        }

        else{
            history.remove(history.size()-1);
            System.out.println("Usage: cd [directory]");
        }
    }

    //======================================================================================================================

    public void ls(String[] args) {
        if(args.length == 0){

        }
        else if(args[0].equals("-r")){
            if(args.length==1){


            }
            else{
                history.remove(history.size()-1);
                System.out.println("Usage: ls -r");
            }
        }
        else{
            history.remove(history.size()-1);
            System.out.println("Usage: ls");
        }
    }

    //======================================================================================================================

    public void mkdir(String[] args){
        if(args.length != 0){

        }
        else{
            history.remove(history.size()-1);
            System.out.println("Usage: mkdir [messages]");
        }
    }

    //======================================================================================================================

    public void rmdir(String[] args){
        if (args.length == 1){

        }
        else {
            history.remove(history.size()-1);
            System.out.println("Usage: rmdir [message]");
        }

    }

    //======================================================================================================================

    public void touch(String[] args){

    }

    //======================================================================================================================

    public void cp(String[] args){
        if(args.length!=0){
            if(args.length == 2){
                String file1Name = args[0];
                String file2Name = args[1];
                try (BufferedReader br = new BufferedReader(new FileReader(file1Name));
                     BufferedWriter bw = new BufferedWriter(new FileWriter(file2Name, false)) ) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }

                } catch (IOException e) {
                    history.remove(history.size()-1);
                    System.out.println("Error: Unable to read the file.");
                }
                
            }

            else if(args[0].equals("-r")){
                if(args.length == 3){

                }
                else{
                    history.remove(history.size()-1);
                    System.out.println("Usage: cp -r directory1 directory2");
                }

            }
            else{
                history.remove(history.size()-1);
                System.out.println("Usage: cp file.txt file1.txt");

            }

        }
        else{
            history.remove(history.size()-1);
            System.out.println("Usage: cp file.txt file1.txt");
        }

    }

    //======================================================================================================================

    public void rm(String[] args){
        if (args.length == 1) {
            String fileName = args[0];
            File file = new File(fileName);

            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else {
                    System.out.println("Error: '" + fileName + "' is not a file.");
                }
            } else {
                System.out.println("Error: File '" + fileName + "' does not exist.");
            }
        } else {
            history.remove(history.size() - 1);
            System.out.println("Usage: rm file.txt");
        }
    }

    //======================================================================================================================

    public void cat(String[] args){
        if(args.length==1){
            String fileName = args[0];
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                history.remove(history.size()-1);
                System.out.println("Error: Unable to read the file.");
            }

        }
        else if (args.length==2){
            String file1Name = args[0];
            String file2Name = args[1];
            try (BufferedReader br1 = new BufferedReader(new FileReader(file1Name));
                 BufferedReader br2 = new BufferedReader(new FileReader(file2Name)) ) {
                String line;
                while ((line = br1.readLine()) != null) {
                    System.out.println(line);
                }
                while ((line = br2.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                history.remove(history.size()-1);
                System.out.println("Error: Unable to read the file.");
            }

        }
        else {
            history.remove(history.size()-1);
            System.out.println("Usage: cat [file1.txt] [file2.txt]");
        }

    }

    //======================================================================================================================

    public void wc(String[] args){
        if(args.length==1){
            int numOfLines =1 , numOfWords =1 , numOfCharacters =0;
            String fileName =args[0];
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                int charCode;
                boolean inSpace = false;  // To track if we're inside a space sequence

                while ((charCode = br.read()) != -1) {
                    char character = (char) charCode;

                    // to skip \r and \n 
                    if(charCode != 13 && charCode != 10) {
                        numOfCharacters++;
                    }


                    // Count lines when a newline character is founded
                    if (character == '\n') {
                        numOfLines++;
                        numOfWords++;
                    }

                    // Count words when a space character is founded
                    if (character == ' ') {
                        if (inSpace) {
                            numOfWords++;
                            inSpace = false;

                        }
                    } else {
                        inSpace = true;
                    }
                }

                System.out.println(numOfLines +" " + numOfWords + " " + numOfCharacters +" " +args[0]);

            } catch (IOException e) {
                history.remove(history.size()-1);
                System.out.println("Error: Unable to read the file.");
            }

        }
        else{
            history.remove(history.size()-1);
            System.out.println("Usage: wc file.txt");
        }

    }

    //======================================================================================================================

    public void history(String[] args){
        if(args.length==0){
            for(int i =0 ; i<history.size() ; i++){
                System.out.println((i+1)+ "- "+ history.get(i));
            }
        }
        else{
            history.remove(history.size()-1);
            System.out.println("Usage: history");
        }

    }

    //======================================================================================================================

    public void run() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(currentDirectory + "> ");
            String input = scanner.nextLine();
            history.add(input);

            if (parser.parse(input)) {
                if(parser.getFoundCommand()){

                    // code

                }
                else {
                    String command = parser.getCommandName();
                    String[] args = parser.getArgs();
                    chooseCommandAction(command, args);
                }
            } else {
                history.remove(history.size()-1);
                System.out.println("Invalid command");
            }
        }
    }

    //======================================================================================================================

    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        terminal.run();
    }

    //======================================================================================================================
}
