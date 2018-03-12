/**
 * @author  Carlitos Alvarez
 * @version 12/03/2018
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northEastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null){
            southEastExit = southEast;
        }
        if(northEast != null){
            northEastExit = northEast;
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
            HabitacionDevolver = northExit;
        }

        if (direction.equals("south")){
            HabitacionDevolver = southExit;
        }

        if (direction.equals("east")){
            HabitacionDevolver = eastExit;
        }

        if (direction.equals("west")){
            HabitacionDevolver = westExit;
        }

        if (direction.equals("southEast")){
            HabitacionDevolver = southEastExit;
        }
        
        if (direction.equals("norhtEast")){
            HabitacionDevolver = northEastExit;
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

        if (northExit != null) {
            exitsDescription += "north ";
        }
        if (southExit != null) {
            exitsDescription += "south ";
        }
        if (eastExit != null) {
            exitsDescription += "east ";
        }
        if (westExit != null) {
            exitsDescription += "west ";
        }
        if (southEastExit != null) {
            exitsDescription += "southEast ";
        }
        if (northEastExit != null) {
            exitsDescription += "norhtEast ";
        }
        return exitsDescription;
    }
}