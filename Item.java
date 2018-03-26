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
    
    /**
     * Constructor de objetos Item
     * 
     * @param nombre nombre de objeto 
     * @param peso peso de objeto
     */
    public Item(String nombre, int peso) {
        this.name = nombre;
        this.weigh = peso;
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
}