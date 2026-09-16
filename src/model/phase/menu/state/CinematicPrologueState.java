package model.phase.menu.state;

import model.phase.State;
import model.phase.StateManager;
import model.scene.Scene;
import model.scene.data.PrologueSceneData;

/**
 * Micro-stato esecutivo per la scena introduttiva.
 * Agisce da interruttore: carica la SceneData, costruisce la Scene
 * e la imposta come attiva nell'Engine.
 */
public class CinematicPrologueState implements State {

    /**
     * Il manager locale che fornisce i servizi di caricamento e iniezione nel motore.
     */
    private final StateManager stateManager;

    /**
     * L'ambiente ECS in memoria generato durante l'onEnter.
     */
    private Scene currentScene;

    /**
     * Costruttore dello stato del prologo.
     *
     * @param stateManager Il gestore che coordina questo stato.
     */
    public CinematicPrologueState(StateManager stateManager) {
        this.stateManager = stateManager;
        this.currentScene = null;
    }

    /**
     * Delega la costruzione della scena allo SceneLoader e la inietta nell'Engine.
     */
    @Override
    public void onEnter() {
        System.out.println("[State] Caricamento Cinematic Prologue...");

        /** I dati statici configurati per il prologo (Copertina, Spinner, Timer). */
        PrologueSceneData prologueData = new PrologueSceneData();

        // 1. Carica le entità in memoria e delega la grafica al loader
        this.currentScene = this.stateManager.getSceneLoader().loadScene(prologueData);

        // 2. Passa la scena direttamente all'Engine.
        // Da questo momento in poi, sarà l'Engine ad aggiornare lo Spinner e il Timer in autonomia.
        this.stateManager.getEngine().setActiveScene(this.currentScene);
    }

    /**
     * Rimuove la scena dal motore e svuota la memoria.
     */
    @Override
    public void onExit() {
        System.out.println("[State] Uscita da Cinematic Prologue.");

        // Rimuove la scena dal motore per fermare definitivamente i calcoli
        this.stateManager.getEngine().setActiveScene(null);

        // Dealloca le entità invocando la pulizia interna della scena
        if (this.currentScene != null) {
            this.currentScene.clearScene();
        }
    }
}