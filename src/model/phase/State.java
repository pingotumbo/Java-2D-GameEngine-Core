package model.phase;

/**
 * Contratto base per tutti i micro-stati locali o stanze.
 * In un'architettura ECS pura, lo Stato non esegue aggiornamenti ciclici (update).
 * Si limita a caricare la Scene nel motore al momento dell'ingresso (onEnter)
 * e a ripulire la memoria al momento dell'uscita (onExit).
 */
public interface State {

    /**
     * Metodo di inizializzazione dello Stato locale.
     * Invocato dallo StateManager al momento della transizione.
     * Responsabile del caricamento della Scene e della sua iniezione nel GameEngine.
     */
    void onEnter();

    /**
     * Metodo di terminazione dello Stato locale.
     * Invocato dallo StateManager al momento dell'abbandono della stanza.
     * Responsabile della distruzione delle entità e dello svuotamento della scena.
     */
    void onExit();
}