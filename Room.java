import java.util.HashMap;

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

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param southEast The southEast exit.
     * @param northEast The northEast exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northEast) 
    {
        if(north != null)
            salidas.put("north", north);
        if(east != null)
            salidas.put("south", south);
        if(south != null)
            salidas.put("east", east);
        if(west != null)
            salidas.put("west", west);
        if(southEast != null){
            salidas.put("southEast", southEast);
        }
        if(northEast != null){
            salidas.put("northEast", northEast);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit (String direction){
        Room HabitacionDevolver = null;

        if (direction.equals("north")){
            HabitacionDevolver = salidas.get("north");
        }

        if (direction.equals("south")){
            HabitacionDevolver = salidas.get("south");
        }

        if (direction.equals("east")){
            HabitacionDevolver = salidas.get("east");
        }

        if (direction.equals("west")){
            HabitacionDevolver = salidas.get("west");
        }

        if (direction.equals("southEast")){
            HabitacionDevolver = salidas.get("southEast");
        }
        
        if (direction.equals("norhtEast")){
            HabitacionDevolver = salidas.get("northWest");
        }

        return HabitacionDevolver;
    }
    
    /**
       * Return a description of the room's exits.
       * For example: "Exits: north east west"
       *
       * @ return A description of the available exits.
       */
    public String getExitString()
    {
        String exitsDescription = "";

        if (salidas.get("north") != null) {
            exitsDescription += "north ";
        }
        if (salidas.get("south") != null) {
            exitsDescription += "south ";
        }
        if (salidas.get("east") != null) {
            exitsDescription += "east ";
        }
        if (salidas.get("west") != null) {
            exitsDescription += "west ";
        }
        if (salidas.get("southEast") != null) {
            exitsDescription += "southEast ";
        }
        if (salidas.get("northEast") != null) {
            exitsDescription += "norhtEast ";
        }
        return exitsDescription;
    }
}