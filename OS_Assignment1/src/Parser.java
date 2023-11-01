import java.util.Arrays;

public class Parser {
    String commandName;
    String[] args;

    boolean foundCommand ;

    String specialChar;

    String fileName;

    


    //======================================================================================================================

    public boolean parse(String input) {
        foundCommand = false;
        // check if command contains > and >> or not
        if(input.contains(">")){
            specialChar = ">";
            foundCommand= true;
        }
        if(input.contains(">>")){
            specialChar = ">>";
            foundCommand = true;
        }

        String[] tokens;
        // if contains > or >>
        if(foundCommand){
            if(input.endsWith(">") || input.endsWith(">>")) return false; // if the input ends with > or >> return false
            tokens = input.split(">+|>>+");           // split string into to string
            if (tokens.length !=2) return false;           // if the input != 2 return false

            fileName = tokens[1];                           // the second string is the file name
            String[] command = tokens[0].split("\\s+");        // the rest is the command with it args

            commandName = command[0];
            args = Arrays.copyOfRange(command , 1 , command.length);

        }
        else {

            tokens = input.split("\\s+");
            if (tokens.length == 0) return false;

            commandName = tokens[0];
            args = Arrays.copyOfRange(tokens, 1, tokens.length);

        }
        return true;
    }

    //======================================================================================================================

    public String getCommandName() {
        return commandName;
    }


    //======================================================================================================================

    public String[] getArgs() {
        return args;
    }

    //=======================================================================================================================

    public  String getSpecialChar(){
        return specialChar;
    }

    //=======================================================================================================================

    public  String getFileName(){
        return  fileName;
    }

    //=======================================================================================================================

    public boolean getFoundCommand(){
        return foundCommand;
    }

}
