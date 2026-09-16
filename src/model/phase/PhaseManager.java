package model.phase;

import model.service.Engine;
import model.service.SceneLoader;
import view.component.Panel;

/**
 * Gestore gerarchico di massimo livello (Macro-Stati).
 * Controlla esclusivamente le transizioni tra le grandi fasi dell'applicazione
 * (es. Menu, Esplorazione) e funge da contenitore (Service Locator) per i servizi core.
 * In un'architettura ECS pura, non esegue aggiornamenti ciclici.
 */
public class PhaseManager {

    /**
     * Il capitolo (macro-fase) attualmente in esecuzione.
     */
    private Phase currentPhase;

    /**
     * L'identificatore globale dello stato in cui si trova l'applicazione.
     */
    private GameStatus currentStatus;

    /**
     * Servizio delegato al calcolo matematico della logica ECS.
     */
    private final Engine engine;

    /**
     * Servizio delegato al disegno grafico a schermo.
     */
    private final Panel panel;

    /**
     * Servizio delegato alla lettura e costruzione delle stanze.
     */
    private final SceneLoader sceneLoader;

    /**
     * Costruttore base. Inizializza i servizi e imposta lo status di caricamento.
     *
     * @param engine      Il motore ECS.
     * @param panel       La finestra grafica.
     * @param sceneLoader Il costruttore delle scene.
     */
    public PhaseManager(Engine engine, Panel panel, SceneLoader sceneLoader) {
        this.engine = engine;
        this.panel = panel;
        this.sceneLoader = sceneLoader;
        this.currentPhase = null;
        this.currentStatus = GameStatus.LOADING;
    }

    /**
     * Esegue la transizione sicura tra due Macro-Fasi.
     * Chiama le rispettive routine di ingresso e uscita.
     *
     * @param newPhase La nuova implementazione di Phase da attivare.
     */
    public void changePhase(Phase newPhase) {
        if (this.currentPhase != null) {
            this.currentPhase.onExit();
        }

        this.currentPhase = newPhase;

        if (this.currentPhase != null) {
            this.currentPhase.onEnter();
        }
    }

    /**
     * Modifica lo stato globale dell'applicazione.
     *
     * @param status Il nuovo identificatore enumerativo.
     */
    public void setStatus(GameStatus status) {
        this.currentStatus = status;
        System.out.println("[PhaseManager] Status globale: " + status);
    }

    // --- Metodi Getter ---
    public Engine getEngine() {
        return this.engine;
    }

    public Panel getPanel() {
        return this.panel;
    }

    public SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }

    public GameStatus getCurrentStatus() {
        return this.currentStatus;
    }
}