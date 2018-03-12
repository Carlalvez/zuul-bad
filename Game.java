/**

 * @author Carlos Alvarez
 * @version 09/03/2018
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room frodo, frowaith, eriador, lindon, moria, rohan, rhun, gondor, mordor, comarca, rhovanion;

        // Crear Localidades
        comarca = new Room("Ubicaci�n actual de Frodo");
        frowaith = new Room("Frowaith");
        rhovanion = new Room ("Rhovanion");
        eriador = new Room("Eriador");
        lindon = new Room("Lindon");
        moria = new Room("Moria");
        rohan = new Room("Rohan");
        rhun = new Room("Rhun");
        gondor = new Room("Gondor");
        mordor = new Room("Est�s en Mordor y has entregado el anillo");

        // Indicaciones 
        comarca.setExits(frowaith, rhovanion, moria, eriador, null);
        frowaith.setExits(null, null, comarca, null, null);
        eriador.setExits(lindon, comarca, null, null, null);
        lindon.setExits(null, eriador, null, null, null);
        rhovanion.setExits(null, comarca, null, null, null);
        moria.setExits(comarca, null, rohan, null, null);
        rohan.setExits(moria, rhun, gondor, null, mordor);
        gondor.setExits(rohan, mordor, null, null, null);
        mordor.setExits(rhun, null, null, gondor, null);
        rhun.setExits(null, null, mordor, rohan, null);

        currentRoom = comarca;  // Inicias aqu�
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al Se�or de los Anillos");
        System.out.println("Escribe 'help' si necesitas ayudas");
        printLocationInfo ();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo ();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Eliminando duplicidades de c�digo y modificando la cohesi�n
     * 
     */
    private void printLocationInfo () {
        System.out.println("Frodo est� en " + currentRoom.getDescription());
        System.out.print(currentRoom.getExitString());
        
        System.out.println();        
    }
}