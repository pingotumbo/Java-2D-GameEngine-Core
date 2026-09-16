package model.phase;

/**
 * Contratto base per tutti i macro-stati dell'applicazione (es. MenuPhase, GameplayPhase).
 * Gestisce il ciclo di vita a lungo termine e i dati persistenti tra le stanze.
 * In un'architettura ECS pura, non possiede un metodo di update(), ma agisce
 * solo come interruttore (onEnter/onExit) per preparare l'ambiente.
 */
public interface Phase {

    /**
     * Metodo di inizializzazione della Fase.
     * Invocato dal PhaseManager nel momento in cui la Fase diventa attiva.
     * Responsabile della registrazione agli eventi e dell'avvio del primo micro-stato.
     */
    void onEnter();

    /**
     * Metodo di terminazione e pulizia della Fase.
     * Invocato dal PhaseManager immediatamente prima di un cambio di Fase globale.
     * Responsabile della deallocazione delle risorse, della chiusura dello stato
     * locale attivo e della deregistrazione dall'EventManager.
     */
    void onExit();
}