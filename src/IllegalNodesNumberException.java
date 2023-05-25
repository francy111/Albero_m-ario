/**
 * Eccezione che si verifica quando non viene fornito un numero
 * corretto di nodi figli durante la creazione di un nodo (n.figli minore di 1)
 *
 * @author Francesco Biribò - Matricola 7111807
 * @version 1.0
 * Data 24.05.2023
 */
public class IllegalNodesNumberException extends Exception {
    /**
     * Costruttore default
     */
    public IllegalNodesNumberException(){
        super();
    }

    /**
     * Costruttore con parametro
     * @param message Messaggio di errore
     */
    public IllegalNodesNumberException(String message) {
        super(message);
    }
}
