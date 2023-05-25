/**
 * Eccezione che si verifica quando viene fornito un nodo non appartenente all'albero
 * a un metodo che lavora sui nodi DI un albero
 *
 * @author Francesco Biribò - Matricola 7111807
 * @version 1.0
 * Data 24.05.2023
 */
public class NodeNotInTreeException extends Exception{
    /**
     * Costruttore default
     */
    public NodeNotInTreeException(){
        super();
    }

    /**
     * Costruttore con parametro
     * @param message Messaggio di errore
     */
    public NodeNotInTreeException(String message) {
        super(message);
    }
}
