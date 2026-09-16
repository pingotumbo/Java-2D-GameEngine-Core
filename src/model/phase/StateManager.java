package model.phase;

import model.service.Engine;
import model.service.SceneLoader;

/**
 * Gestore dedicato esclusivamente ai micro-stati (State) all'interno di una singola Fase.
 * Fornisce agli Stati gli strumenti necessari (SceneLoader) per assemblare i livelli
 * e il riferimento all'Engine per impostare la scena attiva.
 */
public class StateManager {

    /**
     * Il micro-stato attualmente in esecuzione.
     */
    private State currentState;

    /**
     * Il servizio delegato alla costruzione delle scene partendo dai dati statici.
     */
    private final SceneLoader sceneLoader;

    /**
     * Il motore centrale, necessario agli Stati per iniettare la nuova scena attiva.
     */
    private final Engine engine;

    /**
     * Costruisce il gestore degli stati locali.
     *
     * @param sceneLoader Servizio per la lettura e fabbricazione delle scene.
     * @param engine      Il motore ECS che elaborerà la scena.
     */
    public StateManager(SceneLoader sceneLoader, Engine engine) {
        this.sceneLoader = sceneLoader;
        this.engine = engine;
        this.currentState = null;
    }

    /**
     * Sostituisce lo stato locale corrente con uno nuovo, gestendone il ciclo di vita.
     *
     * @param newState L'istanza del nuovo Stato da avviare.
     */
    public void changeState(State newState) {
        if (this.currentState != null) {
            this.currentState.onExit();
        }

        this.currentState = newState;

        if (this.currentState != null) {
            this.currentState.onEnter();
        }
    }

    /**
     * Restituisce il servizio di caricamento scene.
     *
     * @return Il caricatore delle scene a disposizione degli Stati.
     */
    public SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }

    /**
     * Restituisce il motore logico e fisico.
     *
     * @return Il motore logico a cui passare la scena caricata.
     */
    public Engine getEngine() {
        return this.engine;
    }
}