package model.phase.menu.state;

import model.phase.State;
import model.phase.StateManager;

/**
 * Rappresenta il micro-stato dedicato al Menu Principale del gioco.
 * Subentra automaticamente al termine del CinematicPrologueState.
 * Si occuperà di caricare la scena contenente i bottoni e la grafica del menu,
 * per poi iniettarla nell'Engine.
 */
public class MainMenuState implements State {

    /**
     * Il gestore locale per l'accesso ai servizi di caricamento e al motore ECS.
     */
    private final StateManager stateManager;

    /**
     * Costruttore dello stato del menu principale.
     *
     * @param stateManager Il gestore che coordina questo stato.
     */
    public MainMenuState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Fase di setup iniziale. Delegherà il caricamento della scena del menu
     * allo SceneLoader e la imposterà come attiva.
     */
    @Override
    public void onEnter() {
        System.out.println("[MainMenuState] Ingresso nel Menu Principale. Attesa caricamento UI...");
        // Implementazione futura:
        // Scene menuScene = this.stateManager.getSceneLoader().loadScene(new MainMenuSceneData());
        // this.stateManager.getEngine().setActiveScene(menuScene);
    }

    /**
     * Fase di smantellamento. Pulisce la memoria della scena del menu
     * rimuovendola dall'Engine.
     */
    @Override
    public void onExit() {
        System.out.println("[MainMenuState] Uscita dal Menu Principale.");
        // Implementazione futura:
        // this.stateManager.getEngine().setActiveScene(null);
    }
}