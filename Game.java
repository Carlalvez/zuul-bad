import java.util.Stack;
import java.util.ArrayList;
/**
 * @author Carlos Alvarez
 * @version 09/03/2018
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> roomBack;
    private ArrayList <Item> mochila;
    private int limitePeso;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomBack = new Stack<>();
        mochila = new ArrayList<>();
        limitePeso = 30;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
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

        Item fuente_de_piedra = new Item("fuente de piedra",250,1);
        Item abrigo = new Item("abrigo",5,2);

        comarca.addItem (fuente_de_piedra);
        comarca.addItem (abrigo);

        currentRoom = comarca;  // Inicias aquí
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
            System.out.println("l");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("items")) {
            items();
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
        System.out.println(parser.showCommands());

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
            System.out.println("No hay ubicación en la dirección que solicitas");
        }
        else {
            roomBack.push(currentRoom);
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
            System.out.println("¿Que deseas quitar?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Eliminando duplicidades de código y modificando la cohesión
     * 
     */
    private void printLocationInfo () {
        System.out.println(currentRoom.getLongDescription());

        System.out.println();        
    }

    private void look() 
    {
        System.out.println(currentRoom.getLongDescription());

    }

    private void eat() 
    {
        System.out.println("You have eaten now and you are not hungry any more");

    }

    private void back() 
    {
        if (!roomBack.empty()) {
            currentRoom = roomBack.pop();
            printLocationInfo ();
        } 
    }

    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿que item necesitas?");
            return;
        }

        int item = Integer.parseInt(command.getSecondWord());

        for (int i=0 ; currentRoom.itemList.size()>i ; i++){

            if (currentRoom.itemList.get(i).id == item) {

                if (currentRoom.itemList.get(i).weigh<=limitePeso) {
                    mochila.add(currentRoom.itemList.get(i));
                    currentRoom.itemList.remove(i);
                    System.out.println ("Objeto recogido");
                } else {
                    System.out.println ("Objeto muy pesado");                    
                }
                i = currentRoom.itemList.size();
            }
        }
    }

    private void drop(Command command) {
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿que necesitas soltar?");
            return;
        }

        int item = Integer.parseInt(command.getSecondWord());

        for (int i=0 ; mochila.size()>i ; i++){

            if (mochila.get(i).id == item) {

                currentRoom.itemList.add(mochila.get(i));
                mochila.remove(i);
                System.out.println ("Objeto depositado");
                
                i = currentRoom.itemList.size();
            }
        }

    }

    private void items() 
    {
        String infoObjHabitacion="";
        if(mochila.size() <= 0)
        {
            infoObjHabitacion="Frodo no lleva objetos en la mochila";
        } else {
            for(Item objetoDeLaLista : mochila)
            {
                infoObjHabitacion += objetoDeLaLista.itemInfo() + " - ";
            }
        }
        System.out.print (infoObjHabitacion);

    }
}