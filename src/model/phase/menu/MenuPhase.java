package model.phase.menu;

import model.event.EventManager;
import model.event.EventMessage;
import model.event.EventType;
import model.phase.GameStatus;
import model.phase.Phase;
import model.phase.PhaseManager;
import model.phase.StateManager;
import model.phase.menu.state.CinematicPrologueState;
import model.phase.menu.state.MainMenuState;

import java.util.Observable;
import java.util.Observer;

/**
 * Rappresenta la macro-fase dedicata alla navigazione dei menu.
 * Delega la gestione dei micro-stati interni allo StateManager e
 * ascolta gli eventi di sistema per decidere le transizioni logiche.
 * Implementa java.util.Observer per ricevere i messaggi dall'EventManager.
 */
@SuppressWarnings("deprecation")
public class MenuPhase implements Phase, Observer {

    /**
     * Il gestore globale della macchina a stati, per accedere allo status e ai servizi core.
     */
    private final PhaseManager phaseManager;

    /**
     * Il gestore locale dei micro-stati (es. Prologo, Main Menu) generato da questa fase.
     */
    private final StateManager stateManager;

    /**
     * Flag booleano per determinare se è il primo avvio, utile per decidere se mostrare il prologo.
     */
    private boolean isFirstLaunch = true;

    /**
     * Costruttore della fase dei menu.
     *
     * @param phaseManager Riferimento al context globale del gioco.
     */
    public MenuPhase(PhaseManager phaseManager) {
        this.phaseManager = phaseManager;
        // Inizializza lo StateManager locale prendendo i servizi dal PhaseManager
        this.stateManager = new StateManager(phaseManager.getSceneLoader(), phaseManager.getEngine());
    }

    /**
     * Invocato dal gestore delle fasi al momento dell'ingresso nei menu.
     * Si registra agli eventi e instrada verso il Prologo o il Menu Principale.
     */
    @Override
    public void onEnter() {
        System.out.println("[System] Transizione a MenuPhase.");

        // Registrazione all'Event Bus per ascoltare i trigger (es. fine timer prologo)
        EventManager.getInstance().addObserver(this);

        if (this.isFirstLaunch) {
            System.out.println("[MenuPhase] Primo avvio rilevato: lancio il Prologo Cinematografico.");
            this.phaseManager.setStatus(GameStatus.CINEMATIC_PROLOGUE);
            // Usa lo StateManager locale per cambiare stato
            this.stateManager.changeState(new CinematicPrologueState(this.stateManager));
            this.isFirstLaunch = false;
        } else {
            System.out.println("[MenuPhase] Ritorno al menu: salto il prologo.");
            this.phaseManager.setStatus(GameStatus.MENU);
            this.stateManager.changeState(new MainMenuState(this.stateManager));
        }
    }

    /**
     * Metodo ereditato dall'interfaccia Observer.
     * Riceve le notifiche di cambiamento di stato dagli oggetti osservati.
     *
     * @param o   L'oggetto Observable (EventManager).
     * @param arg Eventuali dati aggiuntivi passati insieme all'evento (EventMessage).
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof EventMessage) {
            /** Cast sicuro dell'argomento alla classe del nostro payload. */
            EventMessage msg = (EventMessage) arg;

            // Se il timer scade, intercetta l'evento e chiude il programma per test
            if (msg.getType() == EventType.PROLOGUE_END && this.phaseManager.getCurrentStatus() == GameStatus.CINEMATIC_PROLOGUE) {
                System.out.println("[MenuPhase] EVENTO RILEVATO: Timer ECS di 3 secondi concluso con successo!");
                System.out.println("[System] Test superato. Chiusura forzata dell'applicazione in corso...");

                // Termina il processo con codice di uscita 0 (esecuzione completata senza errori)
                System.exit(0);
            }
        }
    }

    /**
     * Invocato dal gestore delle fasi prima di abbandonare i menu.
     * Si assicura di chiudere lo stato attivo e deregistrarsi dagli eventi.
     */
    @Override
    public void onExit() {
        // Forza l'uscita dallo stato corrente
        this.stateManager.changeState(null);
        // Previene i memory leak
        EventManager.getInstance().deleteObserver(this);
        System.out.println("[System] Uscita da MenuPhase completata.");
    }
}