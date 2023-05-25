/**
 * Classe Run. Propone un main di prova per i metodi della classe Albero m-ario
 *
 * @author Francesco Biribò - Matricola 7111807
 * @version 1.0
 * Data 24.05.2023
 */
public class Run {
    /**
     * Classe non instanziabile
     */
    private Run(){}

    /**
     * Metodo con lo scopo di testare i metodi della classe Albero m-ario
     * @param args Args
     * @throws IllegalNodesNumberException Eccezione che si verifica quando non viene fornito un numero corretto di nodi figli durante la creazione di un nodo (n.figli minore di 1)
     * @throws ExternalNodeParameterException Eccezione che si verifica quando viene fornito un nodo esterno a un metodo che lavora con un nodo interno (viene passato "null")
     * @throws ArrayIndexOutOfBoundsException Eccezione che si verifica quando proviamo a lavorare con l'I-esimo figlio di un albero, con I maggiore dell'arietà dell'albero
     * @throws NodeNotInTreeException Eccezione che si verifica quando viene fornito un nodo non appartenente all'albero a un metodo che lavora sui nodi DI un albero
     */
    public static void main(String[] args) throws IllegalNodesNumberException, ExternalNodeParameterException, ArrayIndexOutOfBoundsException, NodeNotInTreeException {

        /* Lavoriamo con un albero vuoto (primo.png) */
        System.out.println("\nPrimo albero\n");
        try {
            Albero_m_ario a = new Albero_m_ario(3);

            System.out.println("Informazioni sull'albero:\n" + a.infoAlbero());
            System.out.println("Struttura dell'albero: " + a);
            System.out.println("Attraversamento in ampiezza: " + a.attraversamentoAmpiezza());
            System.out.println("Attraversamento in profondita: " + a.attraversamentoProfondita());

            /* Una delle due operazioni è commentata in quanto entrambe generano un'eccezione, ExternalNodeParameterException, si può commentare una o l'altra per provare entrambi gli output
             * Ma finiremo in ogni caso nel catch(ExternalNodeParameterException e1)
             */
            //System.out.println("Padre della radice: " + a.getPadre(a.getRadice()));
            //System.out.println("Livello dell'unico nodo presente: " + a.livelloNodo(a.getRadice()));
        }catch(IllegalNodesNumberException e) {
            System.err.println("Albero non creato. " + e.getMessage());
        }catch(Exception e1){
            System.err.print(e1.getMessage());
        }

        /* Lavoriamo con l'albero rappresentato nell'immagine (secondo.png) */
        System.out.println("\n\n\nSecondo albero\n");
        try{
            Albero_m_ario a = new Albero_m_ario(4);

            /* Costruzione dell'albero */
            a.inizializzaRadice("A");
            Nodo_m_ario b = a.inserisciNodo(a.getRadice(),"B",0);
            Nodo_m_ario c = a.inserisciNodo(a.getRadice(),"C",3);
            a.inserisciNodo(b, "D", 1);
            a.inserisciNodo(c,"E",0);
            Nodo_m_ario f = a.inserisciNodo(c, "F", 2);
            Nodo_m_ario g = a.inserisciNodo(f, "G", 1);

            System.out.println("Informazioni albero: " + a.infoAlbero());
            System.out.println("Struttura albero: " + a);
            System.out.println("Attraversamento anticipato: " + a.attraversamentoProfondita());
            System.out.println("Attraversamento per livelli: " + a.attraversamentoAmpiezza());
            System.out.print("Padre del nodo " + b.getInfo() + ": " + a.getPadre(b).getInfo());

            a.setInfoNodoInterno(b, "NUOVA B");
            System.out.println("Albero con nodo sostituito:" + a.attraversamentoAmpiezza());
            System.out.println("Prendiamo i nodi interni figli del nodo C: " + a.infoFigli(c));
            System.out.println("Prendiamo i nodi interni figli della radice: " + a.infoFigli(a.getRadice()));
            System.out.println("Prendiamo i nodi interni figli del nodo G: " + a.infoFigli(g));

            System.out.print("Modifichiamo la radice: ");
            a.setInfoNodoInterno(a.getRadice(), "A2");
            System.out.println(a.attraversamentoAmpiezza());

            System.out.print("Aggiungiamo una nuova radice: ");
            a.aggiornaRadice("NUOVA RADICE", 3);
            System.out.println(a.attraversamentoAmpiezza());
            System.out.println("Livello del nodo G: " + a.livelloNodo(g));
            System.out.println("Livello del nodo radice: " + a.livelloNodo(a.getRadice()));
            System.out.println("Info del nodo F: " + a.getInfoNodoInterno(f));

        }catch(IllegalNodesNumberException e) {
            System.err.println("Albero non creato. " + e.getMessage());
        }catch(Exception e1){
            System.err.print(e1.getMessage());
        }

        /* Lavoriamo con l'albero rappresentato nell'immagine (terzo.png) */
        System.out.println("\n\n\nTerzo albero\n");
        try{
            Albero_m_ario a = new Albero_m_ario(4);

            /* Costruzione dell'albero */
            a.inizializzaRadice("A");
            Nodo_m_ario b = a.inserisciNodo(a.getRadice(),"B",0);
            Nodo_m_ario c = a.inserisciNodo(a.getRadice(),"C",1);
            Nodo_m_ario d = a.inserisciNodo(a.getRadice(),"D",2);
            Nodo_m_ario e = a.inserisciNodo(a.getRadice(),"E",3);

            a.inserisciNodo(b,"F",0);
            Nodo_m_ario g = a.inserisciNodo(b,"G",1);
            a.inserisciNodo(b,"H",2);
            a.inserisciNodo(b,"I",3);

            a.inserisciNodo(c,"J",0);
            a.inserisciNodo(c,"K",1);
            a.inserisciNodo(c,"L",2);
            a.inserisciNodo(c,"M",3);

            a.inserisciNodo(d,"N",0);
            a.inserisciNodo(d,"O",1);
            a.inserisciNodo(d,"P",2);
            a.inserisciNodo(d,"Q",3);

            a.inserisciNodo(e,"R",0);
            a.inserisciNodo(e,"S",1);
            a.inserisciNodo(e,"T",2);
            a.inserisciNodo(e,"U",3);

            System.out.println("Informazioni albero: " + a.infoAlbero());
            System.out.println("Struttura albero: " + a);
            System.out.println("Struttura albero senza nodi esterni: " + a.toString(false));
            System.out.println("Attraversamento anticipato: " + a.attraversamentoProfondita());
            System.out.println("Attraversamento per livelli: " + a.attraversamentoAmpiezza());

            a.setInfoNodoInterno(b, "NUOVA B");
            System.out.println("Sostituiamo il nodo B:" + a.attraversamentoAmpiezza());
            System.out.println("Prendiamo i nodi interni figli del nodo C: " + a.infoFigli(c));
            System.out.println("Prendiamo i nodi interni figli della radice: " + a.infoFigli(a.getRadice()));
            System.out.println("Prendiamo i nodi interni figli del nodo G: " + a.infoFigli(g));

            System.out.print("Modifichiamo la radice: ");
            a.setInfoNodoInterno(a.getRadice(), "RADICE");
            System.out.println(a.attraversamentoAmpiezza());
            System.out.println("Livello del nodo G: " + a.livelloNodo(g));
            System.out.println("Livello del nodo radice: " + a.livelloNodo(a.getRadice()));
            System.out.print("Aggiungiamo una nuova radice: ");
            a.aggiornaRadice("NUOVA RADICE", 3);
            System.out.println(a.attraversamentoAmpiezza());
            System.out.println(a.toString(false));

            System.out.println("Padre del nodo B: " + a.getPadre(b).getInfo());
            System.out.println("Padre della radice: " + a.getPadre(a.getRadice()));

            a.getPadre(new Nodo_m_ario("", 4));
        }catch(IllegalNodesNumberException e) {
            System.err.println("Albero non creato. " + e.getMessage());
        }catch(NodeNotInTreeException e0){
            System.err.println(e0.getMessage());
        }catch(Exception e1){
            System.err.print(e1.getMessage());
        }
    }
}
