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
    // Clave del objeto
    int id;

    /**
     * Constructor de objetos Item
     * 
     * @param nombre nombre de objeto 
     * @param peso peso de objeto
     */
    public Item(String nombre, int peso, int identificador) {
        this.name = nombre;
        this.weigh = peso;
        this.id = identificador;
    }

    /**
     * Nombre del item.
     * @Obtiene Devuelve el nombre del item.
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
     * @Obtiene Devuelve el peso del item.
     */
    public void setWeigh(int nuevoWeigh) {
        weigh = nuevoWeigh;
    }
        
    /**
     * Devuelve el peso del item.
     * @return Devuelve el peso del item.
     */
    public int getWeith() {
        return weigh;
    }
    
    public String itemInfo()
    {
        return name + ": " + weigh + "kgs, identificador: " + id;
    }
}