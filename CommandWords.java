import java.util.HashMap;
/**
 *
 * @author  Carlos Alvarez
 * @version 13/04/2018
 */

public class CommandWords
{   
    private HashMap <String,CommandWord>validCommands;
    public CommandWords() 
    {
        validCommands = new HashMap<>();
        validCommands.put("go", CommandWord.GO);
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("back", CommandWord.BACK);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("items", CommandWord.ITEMS);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("fight", CommandWord.FIGHT);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.size(); i++) {
            if(validCommands.containsKey(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Imprime por pantalla todos los comandos válidos
     */
    public String getCommandlist()
    {
        String commandList = " ";
        for (String command : validCommands.keySet()) {
            commandList = commandList + command + " ";
        }
        return commandList;
    }

    /**
     * Return the CommandWord associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord corresponding to the String commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord){
        CommandWord palabra = CommandWord.UNKNOWN;
             
        for (String command : validCommands.keySet()) {
            if (commandWord.equals(command)){
                palabra = validCommands.get(command);
            }
        }
        return palabra;
    }
}
