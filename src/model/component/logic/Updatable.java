package model.component.logic;

/**
 * Interfaccia funzionale che definisce il contratto esecutivo per i componenti logici.
 * Qualsiasi classe deputata a manipolare lo stato di un GameObject (es. intelligenza
 * artificiale, controller del giocatore, script fisici) deve implementare questo contratto.
 */
public interface Updatable {

    /**
     * Esegue l'aggiornamento logico e matematico dell'entità.
     * Viene invocato ad ogni ciclo (frame) dal GameEngine.
     * All'interno di questo metodo, il componente ha il compito di leggere il TimeManager,
     * elaborare la logica specifica e sovrascrivere i valori nel componente Transform.
     */
    void update();
}