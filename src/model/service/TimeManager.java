package model.service;

/**
 * Singleton responsabile della misurazione, memorizzazione e distribuzione
 * delle metriche temporali del motore. Centralizza il calcolo del Delta Time
 * e dei frame per secondo (FPS), esponendoli in sola lettura all'intera applicazione.
 */
public class TimeManager {

    /**
     * L'unica istanza globale allocata in memoria della classe TimeManager (Pattern Singleton).
     */
    private static TimeManager instance;

    /**
     * Tempo trascorso tra la fine del frame precedente e l'inizio del frame attuale,
     * espresso in frazioni di secondo. Variabile critica per i calcoli cinematici
     * (es. equazione del moto: spostamento = velocità * deltaTime).
     */
    private double deltaTime;

    /**
     * Valore intero che rappresenta il conteggio dei frame renderizzati con successo
     * nel corso dell'ultimo secondo di esecuzione reale. Utilizzato per profilazione e debug.
     */
    private int currentFPS;

    /**
     * Costruttore privato.
     * Inizializza le variabili temporali interne a zero. Impedisce l'istanziazione diretta
     * dall'esterno per forzare l'uso del pattern Singleton.
     */
    private TimeManager() {
        this.deltaTime = 0.0;
        this.currentFPS = 0;
    }

    /**
     * Restituisce l'accesso globale all'istanza univoca della classe.
     * Implementa la logica di "Lazy Initialization", allocando la memoria per l'oggetto
     * solo al momento della prima invocazione.
     *
     * @return L'istanza statica globale di TimeManager.
     */
    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }
        return instance;
    }

    /**
     * Aggiorna lo stato interno delle variabili temporali.
     * Questo metodo deve essere richiamato esclusivamente dal modulo GameFlow
     * all'interno del ciclo iterativo principale.
     *
     * @param deltaTime  Il tempo calcolato tra i frame correnti, espresso in secondi.
     * @param currentFPS Il numero di frame completati nell'ultimo ciclo temporale di un secondo.
     */
    public void updateTimeData(double deltaTime, int currentFPS) {
        this.deltaTime = deltaTime;
        this.currentFPS = currentFPS;
    }

    /**
     * Fornisce il valore del Delta Time calcolato nell'ultimo tick del motore.
     *
     * @return Il valore del deltaTime corrente in secondi.
     */
    public double getDeltaTime() {
        return this.deltaTime;
    }

    /**
     * Fornisce la misurazione degli FPS effettivi aggiornata all'ultimo secondo di elaborazione.
     *
     * @return Il numero intero rappresentante i frame per secondo correnti.
     */
    public int getCurrentFPS() {
        return this.currentFPS;
    }
}