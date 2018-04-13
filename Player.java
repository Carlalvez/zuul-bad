import java.util.Stack;
import java.util.ArrayList;
/**
 * Implementación clase player incluirá en una primera version.
 * -- currentRoom
 * -- objeto Stack de la clase Game.
 *
 * @author (Carlos Alvarez)
 * @version implementada a fecha (23/03/2018)
 */
public class Player
{
    private Stack<Room> roomBack;
    private Room currentRoom;
    private ArrayList <Item> mochila;
    int pesoLimite;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room comarca)
    {
        roomBack = new Stack<>();
        currentRoom = comarca;
        mochila = new ArrayList<>();
        pesoLimite = 30;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿Donde quieres ir?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay ninguna localidad aqui");
        }
        else {
            roomBack.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("\n");
        }
    }

    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());

    }

    public void back() 
    {
        if (!roomBack.empty()) {
            currentRoom = roomBack.pop();
        } 
    }

    public void eat() 
    {
        System.out.println("You have eaten now and you are not hungry any more");

    }   

    public void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿que item necesitas?");
            return;
        }
        String item = (command.getSecondWord());
        ArrayList<Item> prueba = currentRoom.itemListA();

        for (Item itemFor : prueba)
        {              
            if (itemFor.getNombre().equals (item))
            {

                if(itemFor.movible){
                    if (itemFor.getItem() <= pesoLimite){
                        System.out.println("Objeto cogido");
                        mochila.add(itemFor);
                        currentRoom.removeItem(itemFor);

                    } else {
                        System.out.println("Pesa demasiado, no puedo con ese objeto");
                    }
                } 
                else {
                    System.out.println ("No se puede mover");
                }
                break;
            }
        }        
    } 

    /**
     * Objetos que Frodo lleva encima.
     */
    public void inventarioMochila()
    {
        if (mochila.size() == 0){
            System.out.println ("la mochila está vacia");
        } else{
            for (Item itemFor : mochila)
            {  
                System.out.println(itemFor.getNombre() + "-" + itemFor.getItem() + "kgs.");
            } 
        }
    }

    public void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿que item necesitas?");
            return;
        }
        String item = (command.getSecondWord());

        for (Item itemFor : mochila)
        {              
            if (itemFor.getNombre().equals (item))
            {
                System.out.println ("Objeto depositado");
                mochila.remove(itemFor);
                currentRoom.addItem(itemFor);
                break;
            } 
        }
    }

    /** 
     * Método que permite a la espada ser usada o no contra el monstruo. 
     * 
     */
    public boolean sting(Command command) 
    {
        boolean sting = false; 
        for (Item itemFor : mochila)
        {              
            if (itemFor.getNombre().equals ("Sting"))
            {
                sting = true;  
                System.out.println ("Tiene espada");

            } else {
                System.out.println ("No tiene espada");
            }
        } 
        return sting;
    }

    /** 
     * Método que permite enfrentamiento con el Balrog
     * 
     */
    public void fight(Command command) 
    {   ArrayList<Item> prueba = currentRoom.itemListA();

        boolean borradoObjeto = true;
        int i = 0;
        while (borradoObjeto == true && i<prueba.size ()){
            Item objetoActual = prueba.get(i);
            if (objetoActual.getNombre().equals("Balrog")){
                if (sting(command) == true)
                {   
                    System.out.println ("Has conseguido matar al monstruo");

                    currentRoom.removeItem("Balrog");

                } else {
                    System.out.println ("Has muerto");
                }
            }
            i++;
        }
    }
}