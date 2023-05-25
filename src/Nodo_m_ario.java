/**
 * Classe Nodo m-ario. Rappresenta un nodo di un albero m-ario, contenente
 * 1 campo informazione di tipo String e M figli
 *
 * @author Francesco Biribò - Matricola 7111807
 * @version 1.0
 * Data 24.05.2023
 */
public class Nodo_m_ario {
    /* Attributi */


    /* Informazione contenuta nel nodo */
    private String info;

    /* Array contenente i puntatori agli m figli del nodo
     * In quanto l'arietà del nodo è fissa, possiamo utilizzare una semplice struttura array;
     * Infatti al più saranno presenti m figli, se ce ne fossero meno sarà sufficiente assegnare "null"
     * alle posizioni dell'array che non conterranno figli
     */
    private Nodo_m_ario[] figli;


    /* Costruttori */

    /**
     * Costruttore della classe Nodo_m_ario. Costruisce un nodo di arietà indicata,
     * inizialmente senza figli
     * @param info Stringa da salvare all'interno del nodo
     * @param arieta Numero di figli che potrà avere il nodo
     * @exception IllegalNodesNumberException Eccezione che si verifica quando non viene fornito un numero corretto di nodi figli (minore di 1)
     */
    public Nodo_m_ario(String info, int arieta) throws IllegalNodesNumberException {
        this.setInfo(info);
        /* Inizialmente senza figli si traduce nello istanziare un vettore di nodi m-ari e inizializzare ogni posizione a "null"
         * Se viene passata una dimensione minore di 1 lanciamo un'eccezione, che gestirà poi l'utente
         *
         * Di default gli elementi dell'array saranno inizializzati a null, quindi oltre a creare l'array non dobbiamo fare nient'altro
         */
        if(arieta <= 0) throw new IllegalNodesNumberException("Numero di nodi non consentito. Prova con un intero maggiore di 0");
        this.setFigli(new Nodo_m_ario[arieta]);
    }

    /**
     * Costruttore di copia. Crea una copia profonda del nodo
     * @param n Nodo da copiare
     */
    public Nodo_m_ario(Nodo_m_ario n) {
        this.setInfo(n.info);
        this.setFigli(n.figli);
    }


    /* Setter e Getter */


    /**
     * Setter per l'informazione del nodo. Salva la stringa passata in input nel nodo
     * @param info Informazione da salvare all'interno del nodo
     */
    protected void setInfo(String info){ this.info = info; }

    /**
     * Getter per l'informazione del nodo. Restituisce l'informazione salvata nel nodo
     * @return Informazione salvata nel nodo
     */
    protected String getInfo(){ return info; }

    /**
     * Setter per l'array dei figli. Imposta in una sola volta tutti i figli al nodo
     * @param figli Array contenente i (nuovi) figli del nodo
     */
    private void setFigli(Nodo_m_ario[] figli){ this.figli = figli; }

    /**
     * Getter per l'array dei figli. Restituisce il riferimento all'array dei figli del nodo
     * @return Array dei figli del nodo
     */
    protected Nodo_m_ario[] getFigli(){ return figli; }


    /* Metodi */


    /**
     * Salva un nodo in una posizione a scelta dell'array come figlio del nodo
     * @param figlio Nodo da aggiungere come figli
     * @param posizione Posizione in cui aggiungere il nodo figlio
     */
    protected void setFiglio(Nodo_m_ario figlio, int posizione) throws ArrayIndexOutOfBoundsException{
        this.figli[posizione] = figlio;
    }

    /**
     * Restituisce il nodo presente in una posizione a scelta
     * @param posizione Posizione dalla quale restituire il nodo
     * @return Nodo presente alla posizione scelta
     */
    protected Nodo_m_ario getFiglio(int posizione) throws ArrayIndexOutOfBoundsException{
        return this.figli[posizione];
    }

    /**
     * Controlla se il nodo ha figli o meno
     * @return True - Il nodo ha almeno un figlio, False - Il nodo non ha nemmeno un figlio
     */
    protected boolean haFigli() {
        boolean check = false;
        for(Nodo_m_ario figlio : figli)
            if (figlio != null) {
                check = true;
                break;
            }
        return check;
    }
    @Override
    public String toString(){
        String nodo = "[";
        nodo += this.getInfo() + ", [";
        for(int i = 0; i < getFigli().length; i++){
            if(getFiglio(i) == null) nodo += "null";
            else nodo += getFiglio(i).toString();
            if(i != getFigli().length-1) nodo += ", ";
        }
        return nodo + "]]";
    }

    /**
     * Permette una rappresentazione del nodo ignorando i campi null
     * @param nulls Check che indica se includere i campi null nella rappresentazione o meno
     * @return Stringa che rappresenta il nodo
     */
    public String toString(boolean nulls){
        String nodo ="[";
        if(nulls) nodo = this.toString();
        else{
            nodo += this.getInfo() + (this.haFigli() ? ", " : "");
            for(int i = 0; i < getFigli().length; i++){
                if(getFiglio(i) != null){
                    nodo += getFiglio(i).toString(false);
                    if(i != getFigli().length-1) nodo += ", ";
                }
            }
            nodo += "]";
        }
        return nodo;
    }
}
