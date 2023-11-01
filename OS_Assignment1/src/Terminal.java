import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
                if (args.length > 0 && args[0].equals("-r")) {
                    cpr(args);
                } else {
                    cp(args);
                }
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

            if (args[0] == "*"){

            }

        }
        else{
            history.remove(history.size()-1);
            System.out.println("Usage: mkdir [messages]");
        }
    }

    //======================================================================================================================

    public void rmdir(String[] args){
        if (args.length == 1) {
            String directoryName = args[0];

            if (directoryName.equals("*")) {
                removeEmptyDirectories(currentDirectory);
            } else {
                String fullPath = getFullPath(directoryName);
                File directory = new File(fullPath);

                if (directory.exists() && directory.isDirectory()) {
                    if (isDirectoryEmpty(directory)) {
                        directory.delete();
                    } else {
                        history.remove(history.size() - 1);
                        System.out.println("Error: Directory is not empty.");
                    }
                } else {
                    history.remove(history.size() - 1);
                    System.out.println("Error: Directory does not exist.");
                }
            }
        }
        else {
            history.remove(history.size()-1);
            System.out.println("Usage: rmdir [message]");
        }

    }

    // Get the full path for a given path (handling both relative and absolute paths)
    private String getFullPath(String path) {
        File file = new File(path);
        if (file.isAbsolute()) {
            return path;
        } else {
            return currentDirectory + File.separator + path;
        }
    }

    // Check if a directory is empty
    private boolean isDirectoryEmpty(File directory) {
        String[] files = directory.list();
        return files == null || files.length == 0;
    }

    // Recursively remove empty directories within the specified path
    private void removeEmptyDirectories(String path) {
        File currentDir = new File(path);
        File[] subDirs = currentDir.listFiles(File::isDirectory);

        if (subDirs != null) {
            for (File subDir : subDirs) {
                if (isDirectoryEmpty(subDir)) {
                    subDir.delete();
                } else {
                    removeEmptyDirectories(subDir.getAbsolutePath());
                }
            }
        }
    }




    //======================================================================================================================

    public void touch(String[] args){
        if(args.length == 1){
            String filePath = getFullPath(args[0]);
            try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
            } catch (IOException e) {
                System.out.println("Error creating the file: " + e.getMessage());
            }
        } else{
            history.remove(history.size()-1);
            System.out.println("Usage: touch [path]");
        }
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

    public  void cpr(String[] args) {
        // Ensure that two arguments (source and destination directories) are provided
        if (args.length != 3) {
            System.out.println("Usage: cp -r [source_directory] [destination_directory]");
            return;
        }

        // Extract source and destination paths from the arguments
        String source = args[1];
        String destination = args[2];

        // Create File objects for the source and destination directories
        File sourceDir = new File(source);
        File destinationDir = new File(destination);

        // Perform the recursive copy
        copyRecursive(sourceDir, destinationDir);

        System.out.println("Recursive copy completed successfully.");
    }

    private  void copyRecursive(File sourceDir, File destinationDir) {
        if (sourceDir.isDirectory()) {
            // Create directories in the destination if they don't exist
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            // Get a list of files and subdirectories in the source directory
            File[] files = sourceDir.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Recursively copy each file or subdirectory
                    File destinationFile = new File(destinationDir, file.getName());
                    copyRecursive(file, destinationFile);
                }
            }
        } else {
            // Copy files from source to destination
            try {
                Files.copy(sourceDir.toPath(), destinationDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error copying file: " + e.getMessage());
            }
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
