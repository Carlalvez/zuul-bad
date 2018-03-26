/**
 * Clase que simulan objetos de nuestro mundo.
 * 
 * @author Carlos Alvarez
 * @version 16/03/2018
 */
public class Item {
    // Variable nombre
    String name;
    // Variable peso
    int weigh;
    // Boleano "si es true lo coge"
    boolean movible;
    
    /**
     * Constructor de objetos Item
     * 
     * @param nombre nombre de objeto 
     * @param peso peso de objeto
     */
    public Item(String nombre, int peso, boolean isMovible) {
        this.name = nombre;
        this.weigh = peso;
        this.movible = isMovible;
    }
    
    /**
     * Nombre del item.
     * @return Devuelve el nombre del item.
     */
    public String getNombre() {
        return name;
    }
    
    /**
     * Nombre del item.
     * @return Devuelve el nombre del item.
     */
    public void setNombre(String nuevoNombre) {
        name = nuevoNombre;
    }
    
    /**
     * Peso del item.
     * @return Devuelve el peso del item.
     */
    public void setWeigh(int nuevoWeigh) {
        weigh = nuevoWeigh;
    }
        
    /**
     * Devuelve el peso del item.
     * @return Devuelve el peso del item.
     */
    public int getItem() {
        return weigh;
    }
    
    /**
     *  Boolean true es movible.
     */
    public void setMovible(boolean isMovible) {
        movible = isMovible;
    }
}