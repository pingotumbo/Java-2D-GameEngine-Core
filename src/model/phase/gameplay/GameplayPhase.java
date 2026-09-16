package model.phase.gameplay;

import model.phase.Phase;

import java.util.Observable;
import java.util.Observer;

/**
 * Rappresenta la macro-fase di gioco attivo (Gameplay).
 * Attualmente implementata come stub (segnaposto strutturale). In futuro
 * gestirà i micro-stati delle stanze e ascolterà gli eventi in-game.
 * Implementa java.util.Observer per integrarsi nativamente con l'EventManager.
 */
@SuppressWarnings("deprecation")
public class GameplayPhase implements Phase, Observer {

    /**
     * Costruttore di default della classe GameplayPhase.
     * Inizializzazione attualmente vuota, in attesa dei futuri servizi di gioco.
     */
    public GameplayPhase() {
        // Nessuna inizializzazione richiesta al momento
    }

    /**
     * Invocato dal gestore delle macro-fasi al momento della transizione verso il gameplay.
     * Stampa un messaggio di log in console.
     */
    @Override
    public void onEnter() {
        System.out.println("[System] Transizione a GameplayPhase (In fase di sviluppo).");
        // TODO: In futuro qui andrà inserita la registrazione all'EventManager
    }

    /**
     * Invocato dal gestore delle fasi prima di abbandonare definitivamente il gameplay.
     * Stampa un messaggio di chiusura.
     */
    @Override
    public void onExit() {
        System.out.println("[System] Uscita da GameplayPhase.");
        // TODO: In futuro qui andrà inserita la deregistrazione dall'EventManager
    }

    /**
     * Metodo di callback per il pattern Observer.
     * Intercetta e gestisce gli eventi globali (es. PLAYER_DEATH) durante la partita.
     *
     * @param o   L'istanza dell'EventManager globale.
     * @param arg L'oggetto EventMessage trasportato dal bus di sistema.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Spazio riservato allo smistamento degli eventi di gioco
    }
}