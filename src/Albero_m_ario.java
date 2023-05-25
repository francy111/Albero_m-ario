import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe Albero m-ario. Rappresenta un albero m-ario, contenente
 * 1 campo informazione di tipo String e M figli
 *
 * @author Francesco Biribò - Matricola 7111807
 * @version 1.0
 * Data 24.05.2023
 *
 * Nella seguente implementazione viene considerata questo criterio:
 *    - Nodo interno: Nodo con m figli
 *    - Nodo esterno: Puntatore "null"
 *    - Foglia: Nodo interno in cui ogni figlio è "null"
 */
public class Albero_m_ario {
    /* Attributi */


    /* Arietà dell'albero */
    private int arieta;

    /* Radice dell'albero */
    private Nodo_m_ario radice;


    /* Costruttori */

    /**
     * Costruttore della classe Albero_m_ario. Costruisce un albero m-ario con l'arietà data. Inizialmente è vuoto
     * ovvero la radice stessa è vuota. Controllare il metodo "inizializzaRadice" per inserire una radice nell'albero
     * @param arieta Numero di figli che avrà ogni nodo
     * @exception IllegalNodesNumberException Eccezione sollevata se viene fornito un valore di arietà non valido (minore di 1)
     */
    public Albero_m_ario(int arieta) throws IllegalNodesNumberException {
        if(arieta <= 0) throw new IllegalNodesNumberException("Non è consentito creare un albero di arietà minore o uguale a 0 (" + arieta + "). Prova con un intero maggiore di 0");
        this.setArieta(arieta);
        this.setRadice(null);
    }


    /* Setter e Getter */

    /**
     * Setter per l'arietà. Imposta l'arietà dell'albero
     * @param arieta Numero di figli che avrà ogni nodo
     */
    private void setArieta(int arieta){ this.arieta = arieta; }

    /**
     * Getter per l'arietà. Restituisce l'arietà dell'albero
     * @return Numero di figli di ogni nodo
     */
    public int getArieta(){ return this.arieta; }

    /**
     * Setter per la radice. Imposta la radice dell'albero
     * @param radice Nodo radice dell'albero
     */
    private void setRadice(Nodo_m_ario radice){ this.radice = radice; }

    /**
     * Getter per la radice. Restituisce la radice dell'albero
     * @return Nodo radice dell'albero
     */
    public Nodo_m_ario getRadice(){ return this.radice; }


    /* Metodi */


    /**
     * Inizializza la radice dell'albero. Ovvero va a creare il nodo (compresa l'informazione) radice dell'albero m-ario
     * @param info Informazione da salvare nella radice
     * @throws IllegalNodesNumberException Eccezione lanciata se l'arietà dell'albero non permette la creazione del nodo (minore di 1)
     *
     * N.B. In quanto la creazione dell'albero richiede che l'arietà sia maggiore di 0 non si presenterà mai il caso in cui
     * l'eccezione verrà lanciata
     *
     * N.B. In quanto il metodo serve a inizializzare la radice dell'albero, se viene chiamato su un albero non vuoto
     * cancellerà tutti i nodi, facendo rimanere solamente la nuova radice. Se si vuole aggiornare la radice mantenendo i nodi
     * controllare il metodo "aggiornaRadice"
     */
    public void inizializzaRadice(String info) throws IllegalNodesNumberException {
        this.setRadice(new Nodo_m_ario(info, arieta));
    }

    /**
     * Inserisce un nuovo nodo V (creato con la strina info) come figlio i-esimo del nodo U
     * @param u Nodo padre del nuovo nodo V
     * @param info Stringa da salvare nel nuovo nodo V
     * @param i Indica che il nodo V è l'i-esimo figlio di U
     * @return Restituisce il nodo inserito nell'albero
     * @throws IllegalNodesNumberException Eccezione causata se l'arietà dell'albero non permette la creazione del nodo (> 0)
     * @throws ArrayIndexOutOfBoundsException Eccezione causata se l'indice indicato non rientra nel range delle posizioni dell'array
     */
    public Nodo_m_ario inserisciNodo(Nodo_m_ario u, String info, int i) throws IllegalNodesNumberException, ArrayIndexOutOfBoundsException{
        Nodo_m_ario v = new Nodo_m_ario(info, arieta);
        u.setFiglio(v, i);
        return v;
    }

    /**
     * Aggiorna la radice dell'albero, spostando la vecchia radice come figlio i-esimo della nuova radice
     * @param info Informazione da salvare nella nuova radice
     * @param i Posizione, tra i figli della nuova radice, in cui verrà spostata la vecchia radice
     * @throws IllegalNodesNumberException Eccezione causata se l'arietà dell'albero non permette la creazione del nodo (> 0)
     * @throws ArrayIndexOutOfBoundsException Eccezione causata se l'indice indicato non rientra nel range delle posizioni dell'array
     */
    public void aggiornaRadice(String info, int i) throws IllegalNodesNumberException, ArrayIndexOutOfBoundsException {
        /* Creiamo il nuovo nodo radice */
        Nodo_m_ario nuovaRadice = new Nodo_m_ario(info, arieta);

        /* Impostiamo la vecchia radice come figlio i-esimo della nuova radice */
        nuovaRadice.setFiglio(this.radice, i);

        /* Impostiamo il nuovo nodo come radice dell'albero */
        this.setRadice(nuovaRadice);

    }

    /**
     * Visita l'albero tramite un attraversamento anticipato, salvando le informazioni dei nodi interni
     * @return Lista di stringhe che rappresentano le informazioni dei nodi interni visitati
     */
    public LinkedList<String> attraversamentoProfondita() {
        LinkedList<String> infoNodi = new LinkedList<>();
        LinkedList<Nodo_m_ario> nodi = new LinkedList<>();
        Nodo_m_ario temp;

        nodi.push(this.radice);
        while(!nodi.isEmpty()){
            temp = nodi.pop();

            /* Si aggiungono solo le info dei nodi interni */
            if(temp != null){
                infoNodi.add(temp.getInfo());
                Nodo_m_ario[] figli = temp.getFigli();
                for(int i = figli.length - 1; i >= 0; i--) nodi.push(figli[i]);

            }
        }

        return infoNodi;
    }

    /**
     * Visita l'albero tramite un attraversamento per livelli, salvando le informazioni dei nodi interni
     * @return Lista di stringe che rappresentano le informazioni dei nodi interni visitati
     */
    public LinkedList<String> attraversamentoAmpiezza() {
        LinkedList<String> infoNodi = new LinkedList<>();
        LinkedList<Nodo_m_ario> nodi = new LinkedList<>();
        Nodo_m_ario temp;

        nodi.add(this.radice);
        while(!nodi.isEmpty()){
            temp = nodi.remove();

            /* Si aggiungono solo le info dei nodi interni */
            if(temp != null){
                infoNodi.add(temp.getInfo());
                Nodo_m_ario[] figli = temp.getFigli();
                for(int i = 0; i < figli.length; i++) nodi.add(figli[i]);
            }
        }

        return infoNodi;
    }

    /**
     * Restituisce il numero di nodi interni dell'albero
     * @return Numero di nodi interni dell'albero
     * @exception ExternalNodeParameterException Eccezione sollevata quando l'albero non ha radice (albero vuoto, la radice è un nodo esterno)
     *
     * N.B. Questo caso non si può verificare, ma in quanto viene utilizzato dai
     */
    public int numeroNodiInterni() throws ExternalNodeParameterException {
        if(this.radice == null) throw new ExternalNodeParameterException("L'albero contiene solo un nodo esterno. Prova ad inizializzare la radice prima");
        int nodiInterni = 1;

        LinkedList<Nodo_m_ario> codaNodi = new LinkedList<>();
        Nodo_m_ario temp;
        codaNodi.add(this.radice);

        /* Per ogni nodo aggiungiamo il numero dei figli che sono nodi interni, se il nodo è esterno non viene aggiunto
         * alla lista (per evitare problemi con puntatori nulli). A ogni iterazione aggiungiamo i figli del nodo analizzato alla coda
         */
        while (!codaNodi.isEmpty()) {
            temp = codaNodi.remove();
            nodiInterni += numeroNodiInterni(temp);
            for (Nodo_m_ario figlio : temp.getFigli()) {

                try {
                    if (isNodoInterno(figlio))
                        codaNodi.add(figlio);
                }catch(NodeNotInTreeException e){
                    /* Caso non verificabile in quanto i nodi vengono raggiunti dal padre, come il padre è stato raggiungo da suo padre e così via, fino ad arrivare alla radice */
                }
            }
        }
        return nodiInterni;
    }

    /**
     * Restituisce il numero di figli di un nodo interno, che sono a loro volta nodi interni
     * @param m Nodo interno dell'albero
     * @return Numero dei suoi figli che sono a loro volta nodi interni
     * @exception ExternalNodeParameterException Eccezione sollevata quando viene fornito un nodo esterno
     */
    public int numeroNodiInterni(Nodo_m_ario m) throws ExternalNodeParameterException{
        if(m == null) throw new ExternalNodeParameterException("Il nodo fornito è un nodo esterno");
        int nodiInterni = 0;
        Nodo_m_ario[] figli = m.getFigli();
        for(Nodo_m_ario figlio : figli) {

            /* Se uno dei figli è nodo esterno (il nodo ha meno di m figli) quest'ultimo solleverà un'eccezione quando passato
             * al metodo isFoglia. Non lo aggiungiamo quindi al conto dei nodi interni (essendo ne interno ne foglia -> esterno)
             *
             * Il comportamento ottenuto è il seguente:
             *     Se il figlio è un nodo interno si aggiunge 1 al contatore
             *     Se il figlio è foglia non aggiungiamo niente
             *     Se il figlio è un nodo esterno catturiamo l'eccezione non facendogli fare niente (come se fosse foglia, non aggiunge niente al contatore di nodi interni)
             */
            if (figlio != null) nodiInterni++;
        }
        return nodiInterni;
    }

    /**
     * Controlla se il nodo passato è foglia
     * @param m Nodo da controllare
     * @return True, Il nodo è una foglia - False, Il nodo è un nodo interno con almeno 1 figlio o un nodo esterno
     * @exception NodeNotInTreeException Eccezione sollevata se il nodo fornito non fa parte dell'albero su cui il metodo è invocato
     */
    private boolean isFoglia(Nodo_m_ario m) throws NodeNotInTreeException{
        if(!isInTree(m)) throw new NodeNotInTreeException("Il nodo non fa parte dell'albero");
        return isNodoInterno(m) ? (!m.haFigli()) : false;
    }

    /**
     * Controlla se il nodo passato è un nodo interno
     * @param m Nodo da controllare
     * @return True, Il nodo è un nodo interno - False, Il nodo è un nodo esterno
     * @exception NodeNotInTreeException Eccezione sollevata se il nodo fornito non fa parte dell'albero su cui il metodo è invocato
     */
    private boolean isNodoInterno(Nodo_m_ario m) throws NodeNotInTreeException{
        if(!isInTree(m)) throw new NodeNotInTreeException("Il nodo non fa parte dell'albero");
        return m != null;
    }

    /**
     * Controlla se il nodo utilizzato è presente o meno nell'albero
     * @param m Nodo da controllare
     * @return True, il nodo è nell'albero - False, il nodo non è nell'albero
     */
    private boolean isInTree(Nodo_m_ario m){
        boolean check = false;

        Nodo_m_ario temp;
        LinkedList<Nodo_m_ario> listaNodi = new LinkedList<>();
        listaNodi.add(this.radice);
        while(!listaNodi.isEmpty()){
            temp = listaNodi.remove();

            if(temp == m) {
                check = true;
                break;
            }
            if(temp != null) {
                for (int i = 0; i < temp.getFigli().length; i++) {
                    listaNodi.add(temp.getFiglio(i));
                }
            }
        }
        return check;
    }

    /**
     * Restituisce le informazioni dei figli di un nodo m, se questi figli sono a loro volta nodi interni
     * @param m Nodo padre dei figli dei quali vogliamo le informazioni
     * @return Lista di stringhe, contenenti le informazioni di ogni nodo interno figlio di m, partendo dal primo fino all'm-esimo
     * @exception ExternalNodeParameterException Eccezione sollevata quando viene fornito un nodo esterno
     * @exception NodeNotInTreeException Eccezione sollevata quando il nodo fornito non è parte dell'albero
     */
    public LinkedList<String> infoFigli(Nodo_m_ario m) throws ExternalNodeParameterException, NodeNotInTreeException {
        if(m == null) throw new ExternalNodeParameterException("Il nodo passato è esterno");
        LinkedList<String> listaInfo = new LinkedList<>();
        for(Nodo_m_ario figlio : m.getFigli()) {

            /* Se uno dei figli è un nodo esterno non lo consideriamo per l'aggiornamento della lista delle informazioni */
            if (isNodoInterno(figlio))
                listaInfo.add(figlio.getInfo());
        }
        return listaInfo;
    }

    /**
     * Restituisce l'informazione contenuta nel nodo se il nodo è interno
     * @param m Nodo da controllare
     * @return Stringa che rappresenta l'informazione contenuta nel nodo
     * @exception ExternalNodeParameterException Eccezione sollevata quando viene fornito un nodo esterno
     * @exception NodeNotInTreeException Eccezione sollevata quando il nodo fornito non fa parte dell'albero
     */
    public String getInfoNodoInterno(Nodo_m_ario m) throws ExternalNodeParameterException, NodeNotInTreeException {
        if(!isNodoInterno(m)) throw new ExternalNodeParameterException("Il nodo fornito è un nodo esterno");
        return m.getInfo();
    }

    /**
     * Modifica l'informazione salvata su un nodo interno dell'albero
     * @param m Nodo da modificare
     * @param newInfo Stringa contenente la nuova informazione da salvere nel nodo indicato
     * @throws ExternalNodeParameterException Eccezione sollevata se il nodo fornito è un nodo esterno
     * @exception NodeNotInTreeException Eccezione sollevata se il nodo fornito non fa parte dell'albero
     */
    public void setInfoNodoInterno(Nodo_m_ario m, String newInfo) throws ExternalNodeParameterException, NodeNotInTreeException{
        if(!isNodoInterno(m)) throw new ExternalNodeParameterException("Il nodo fornito è un nodo esterno");
        m.setInfo(newInfo);
    }

    /**
     * Restituisce il padre di un nodo interno
     * @param m Nodo interno del quale cerchiamo il padre
     * @return Il nodo padre del nodo interno (interno a sua volta) - "null" se viene fornita la radice dell'albero
     * @throws ExternalNodeParameterException Eccezione sollevata se il nodo fornito è un nodo esterno
     * @exception NodeNotInTreeException Eccezione sollevata se il nodo fornito non fa parte dell'albero
     */
    public Nodo_m_ario getPadre(Nodo_m_ario m) throws ExternalNodeParameterException, NodeNotInTreeException {
        if(!isNodoInterno(m)) throw new ExternalNodeParameterException("Il nodo fornito è un nodo esterno");
        Nodo_m_ario padre = null;

        Nodo_m_ario temp;
        LinkedList<Nodo_m_ario> listaNodi = new LinkedList<>();
        listaNodi.add(this.radice);
        while(!listaNodi.isEmpty()){
            temp = listaNodi.remove();

            if(temp != null) {
                for (int i = 0; i < temp.getFigli().length; i++) {
                    listaNodi.add(temp.getFiglio(i));
                    if(temp.getFiglio(i) == m){
                        padre = temp;
                        break;
                    }
                }
            }
        }
        return padre;
    }

    /**
     * Restituisce il numero di foglie dell'albero
     * @return Numero di foglie dell'albero
     * @throws ExternalNodeParameterException Eccezione sollevata quando l'albero non ha radice
     */
    public int numeroFoglie() throws ExternalNodeParameterException {
        if(this.radice == null) throw new ExternalNodeParameterException("L'albero contiene solo un nodo esterno. Prova ad inizializzare la radice prima");
        int foglie = 0;

        LinkedList<Nodo_m_ario> codaNodi = new LinkedList<>();
        Nodo_m_ario temp;
        codaNodi.add(this.radice);

        /* Per ogni nodo aggiungiamo 1 al contatore se è una foglia, altrimenti non aggiungiamo niente al contatore
         * e aggiorniamo la lista di nodi da visitare
         */
        while (!codaNodi.isEmpty()) {
            temp = codaNodi.remove();
            /* Controlliamo se il nodo è interno o esterno */
            try {
                if (isNodoInterno(temp)) {
                    if (isFoglia(temp)) foglie++;

                    for (Nodo_m_ario figlio : temp.getFigli()) {

                        if (isNodoInterno(figlio))
                            codaNodi.add(figlio);
                    }
                }
            } catch (NodeNotInTreeException e) {
                /* Caso non verificabile in quanto i nodi vengono raggiunti dal padre, come il padre è stato raggiungo da suo padre e così via, fino ad arrivare alla radice */
            }
        }
        return foglie;
    }

    /**
     * Restituisce il numero di nodi esterni dell'albero
     * @return Numero nodi esterni (quanti "null" sono presenti come figli di nodi)
     */
    public int numeroNodiEsterni(){
        int nodiEsterni = 0;

        LinkedList<Nodo_m_ario> codaNodi = new LinkedList<>();
        Nodo_m_ario temp;
        codaNodi.add(this.radice);

        while (!codaNodi.isEmpty()) {
            temp = codaNodi.remove();
            if(temp == null){
                nodiEsterni++;
            }else{
                Nodo_m_ario[] figli = temp.getFigli();
                for(Nodo_m_ario figlio : figli) codaNodi.add(figlio);
            }
        }
        return nodiEsterni;
    }

    /**
     * Restituisce l'altezza dell'albero
     * @return Altezza dell'albero
     */
    public int altezzaAlbero(){
        /* Possiamo usufruire del metodo per calcolare l'altezza di un sottoalbero dato un nodo, fornendo come radice del sotto albero la radice dell'albero stesso */
        return altezzaAlberoRicorsivo(this.radice);
    }

    /**
     * Restituisce l'altezza del sottoalbero che ha come radice il nodo fornito
     * @param nodo Nodo "radice" del sottoalbero di cui vogliamo calcolare l'altezza
     * @return Restituisce l'altezza del sottoalbero con radice "nodo"
     */
    private static int altezzaAlberoRicorsivo(Nodo_m_ario nodo){
        int altezza;
        if(nodo == null) altezza = 0;
        else {

            /* Calcoliamo tutte le altezze dei sottoalberi che hanno come radice i figli del nodo m */
            ArrayList<Integer> altezze = new ArrayList<>();
            for (Nodo_m_ario figlio : nodo.getFigli()) {
                altezze.add(altezzaAlberoRicorsivo(figlio));
            }

            /* Tra questi andiamo a prendere l'altezza maggiore */
            altezze.sort( (o1, o2) -> o2-o1);
            altezza = altezze.get(0) + 1;
        }
        return altezza;
    }

    /**
     * Restituisce il livello del nodo fornito nell'albero
     * @param m Nodo cercato
     * @return Livello
     * @throws ExternalNodeParameterException Eccezione sollevata quando viene fornito un nodo esterno ("null") in quanto essendocene tanti presenti, non sapremo quale dei tanti
     * @throws NodeNotInTreeException Eccezione sollevata quando il nodo fornito non fa parte dell'albero
     */
    public int livelloNodo(Nodo_m_ario m) throws ExternalNodeParameterException, NodeNotInTreeException{
        if(!this.isNodoInterno(m)) throw new ExternalNodeParameterException("Il nodo fornito è un nodo esterno. Prova con un nodo interno");
        int livello = -1;
        Nodo_m_ario temp = m;
        while(temp != null){
            temp = this.getPadre(temp);
            livello++;
        }
        return livello;
    }

    /**
     * Restituisce una stringa contenente le informazioni dell'albero, ovvero arietà e numero di nodi (interni/esterni)
     * @return Stringa contenente le informazioni dell'albero
     */
    public String infoAlbero() {
        String info = "Arieta: " + this.getArieta();

        int interni;
        int foglie;
        int esterni;
        int altezza = this.altezzaAlbero();
        try {
            interni = this.numeroNodiInterni();
            foglie = this.numeroFoglie();
            esterni = this.numeroNodiEsterni();
        }catch(ExternalNodeParameterException e){
            interni = 0;
            foglie = 0;
            esterni = 1;
        }

        info = info + "\n" + "Altezza: " + altezza;
        info = info + "\n" + "Nodi totali: " + (interni + esterni) + " di cui:\n";
        info = info + "\n" + "Nodi interni: " + interni + (interni != 0 ? (" di cui " + foglie + ((foglie) == 1 ? " foglia" : " foglie")) : "");
        info = info + "\n" + "Nodi esterni: " + esterni;
        return info;
    }

    @Override
    public String toString() {
        return this.radice == null ? "[null]" : this.radice.toString();
    }

    /**
     * Permette una rappresentazione dell'albero con o senza i nodi esterni
     * @param nulls Check che indica se comprendere o meno i nodi esterni nella rappresentazione dell'albero
     * @return Stringa che rappresenta l'albero
     */
    public String toString(boolean nulls){
        return (nulls) ? this.toString() : ((this.radice == null) ? "[]" : this.radice.toString(false));
    }
}
