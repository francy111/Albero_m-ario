/**
 * Eccezione che si verifica quando viene fornito un nodo esterno a un metodo
 * che lavora con un nodo interno (viene passato "null")
 *
 * @author Francesco Biribò - Matricola 7111807
 * @version 1.0
 * Data 24.05.2023
 */
public class ExternalNodeParameterException extends Exception{
    /**
     * Costruttore default
     */
    public ExternalNodeParameterException(){
        super();
    }

    /**
     * Costruttore con parametro
     * @param message Messaggio di errore
     */
    public ExternalNodeParameterException(String message) {
        super(message);
    }
}
