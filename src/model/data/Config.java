package model.data;

/**
 * Classe di configurazione globale del motore grafico e logico.
 * Contiene esclusivamente costanti statiche (immutabili) che definiscono
 * i parametri operativi di base del sistema. Permette a qualsiasi modulo
 * di accedere a questi valori senza necessità di allocare memoria per un'istanza.
 */
public class Config {

    /**
     * Frequenza di aggiornamento target del motore logico e di rendering,
     * espressa in frame per secondo (FPS). Regola la velocità del Main Loop.
     */
    public static final int TARGET_FPS = 60;

    /**
     * Valore calcolato in nanosecondi che rappresenta la durata ideale di un singolo frame.
     * Viene utilizzato dal modulo Flow per calcolare il Delta Time e mantenere
     * costante la frequenza del ciclo di aggiornamento.
     */
    public static final long TARGET_TIME_NS = 1000000000 / TARGET_FPS;

    /**
     * Larghezza fissa predefinita della finestra principale, misurata in pixel.
     */
    public static final int WINDOW_WIDTH = 800;

    /**
     * Altezza fissa predefinita della finestra principale, misurata in pixel.
     */
    public static final int WINDOW_HEIGHT = 600;

    /**
     * Dimensione fissa in pixel di una singola cella (tile) della griglia spaziale.
     * Fondamentale per i calcoli di posizionamento della mappa procedurale,
     * per le collisioni e per la traduzione delle coordinate vettoriali in coordinate schermo.
     */
    public static final int TILE_SIZE = 64;

    /**
     * Costruttore privato.
     * Impedisce l'istanziazione di questa classe, garantendo che venga utilizzata
     * esclusivamente come contenitore di variabili statiche globali.
     */
    private Config() {
        // Nessuna inizializzazione permessa
    }
}