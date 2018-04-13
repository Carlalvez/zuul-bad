
/**
 * @author Carlos Alvarez
 * @version 09/03/2018
 */

public class Game 
{
    private Parser parser;
    private Player frodo;   
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        frodo = new Player(createRooms());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room frodo, frowaith, eriador, lindon, moria, rohan, rhun, gondor, mordor, comarca, rhovanion;

        // Crear Localidades
        comarca = new Room("La Comarca");
        frowaith = new Room("Frowaith");
        rhovanion = new Room ("Rhovanion");
        eriador = new Room("Eriador");
        lindon = new Room("Lindon");
        moria = new Room("Moria");
        rohan = new Room("Rohan");
        rhun = new Room("Rhun");
        gondor = new Room("Gondor");
        mordor = new Room("Estás en Mordor y has entregado el anillo");

        // Indicaciones
        comarca.setExit ("north", frowaith);
        comarca.setExit ("east", rhovanion);
        comarca.setExit ("south", moria);
        comarca.setExit ("west", eriador);

        frowaith.setExit ("south", comarca);

        eriador.setExit ("north", lindon);
        eriador.setExit ("east", comarca);

        lindon.setExit ("east", eriador);

        rhovanion.setExit ("east", comarca);

        moria.setExit ("north", comarca);
        moria.setExit ("south", rohan);

        rohan.setExit ("north", moria);
        rohan.setExit("east", rhun);
        rohan.setExit ("south", gondor);
        rohan.setExit ("southEast", mordor);
        rohan.setExit ("northEast", rhovanion);

        gondor.setExit ("north", rohan);
        gondor.setExit ("south", mordor);

        mordor.setExit  ("north", rhun);
        mordor.setExit ("west", gondor);

        rhun.setExit ("south", mordor);
        rhun.setExit ("west", rohan);

        comarca.addItem ("fuente",250,false);
        comarca.addItem ("Sting",10,true);

        moria.addItem ("Balrog",1500,false);

        return comarca;
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
        System.out.println("Gracias por jugar, hasta la vista");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al Señor de los Anillos");
        System.out.println("Escribe 'help' si necesitas ayuda");
        System.out.println("\n");
        frodo.look();
        System.out.println("\n");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        switch (commandWord){
            case UNKNOWN:
            System.out.println ("No te he entendido");
            break;

            case HELP:
            printHelp();
            break;

            case LOOK:
            frodo.look();
            break;

            case GO:
            frodo.goRoom(command);
            break;

            case BACK:
            frodo.back();
            break;

            case EAT:
            frodo.eat();
            break;

            case TAKE:
            frodo.take(command);
            break;

            case ITEMS:
            frodo.inventarioMochila();
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;

            case DROP:
            frodo.drop(command);
            break;

            case FIGHT:
            frodo.fight(command);
            break;
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
        System.out.println("Estas perdido eh...");
        System.out.println();
        System.out.println("Tu comando es: ");
        System.out.println(parser.showCommands());

    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("¿Que deseas quitar?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}