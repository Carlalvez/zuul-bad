import java.util.HashMap;
import java.util.Set;
/**
 * @author  Carlitos Alvarez
 * @version 12/03/2018
 */
public class Room 
{
    private String description;
        
    private HashMap <String, Room> salidas;
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
        Set<String> direcciones = salidas.keySet();
        String salidasDescripciones = "Salidas ";
        
        for (String direccion : direcciones)
        {
            salidasDescripciones += direcciones + " ";
        }

        return salidasDescripciones;
    }
}