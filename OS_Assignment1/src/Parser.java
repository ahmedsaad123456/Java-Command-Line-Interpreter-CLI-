import java.util.Arrays;

public class Parser {
    String commandName;
    String[] args;

    


    //======================================================================================================================

    public boolean parse(String input) {
        String[] tokens = input.split("\\s+");
        if (tokens.length == 0) return false;

        commandName = tokens[0];
        args = Arrays.copyOfRange(tokens, 1, tokens.length);

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
}
