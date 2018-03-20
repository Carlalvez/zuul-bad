import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 * @author  Carlitos Alvarez
 * @version 12/03/2018
 */
public class Room 
{
    private String description;
    private HashMap <String, Room> salidas;
    private Item item;
    public ArrayList<Item> itemList;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
        itemList = new ArrayList<>();
    }

    public void setExit(String direccion, Room nextRoom)
    {
        salidas.put(direccion, nextRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit (String direction){
        return salidas.get(direction);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        Set<String> nombreDirecciones = salidas.keySet();
        String salidasDescripciones = "";
        for (String direccion : nombreDirecciones)
        {
            salidasDescripciones += direccion + " ";
        }

        return salidasDescripciones;
    }

    /**
     * Return a long description of this room, of the form:
     *  
     *  
     *  Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "Frodo esta en " + description + ".\n" + "objetos: "+ infoObjHabitacion()+ ".\n" + "Tienes estas posibilidades " + getExitString();
    }

    /**
     * Añade más de un objeto 
     */
    public void addItem(Item itemHabitacion)
    {
        itemList.add(itemHabitacion);        
    }
    
    /**
     * Muestra la informacion de los objetos de cada habitacion
     * 
     */
    public String infoObjHabitacion(){
         String infoObjHabitacion="";
        if(itemList.size() <= 0)
        {
            infoObjHabitacion="No hay objetos en esta ubicacion";
        } else {
            for(Item objetoDeLaLista : itemList)
            {
                infoObjHabitacion += objetoDeLaLista.itemInfo() + " - ";
            }
        }
        return infoObjHabitacion;
    }
}