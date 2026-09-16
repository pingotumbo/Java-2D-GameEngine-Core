package model.service;

import model.component.logic.Updatable;
import model.scene.Scene;

import java.util.List;

/**
 * Motore logico e fisico dell'applicazione.
 * Mantiene il riferimento alla scena ECS attualmente attiva e si occupa
 * dell'elaborazione ciclica (tick) di tutti i componenti dotati di comportamento dinamico.
 * Costituisce il nucleo del calcolo computazionale, operando in totale autonomia
 * rispetto alla Macchina a Stati.
 */
public class Engine {

    /**
     * L'ambiente spaziale e logico correntemente in elaborazione.
     * Contiene l'aggregato di entità da aggiornare a ogni iterazione del motore.
     */
    private Scene activeScene;

    /**
     * Costruttore di default del motore logico.
     * Inizializza l'elaboratore in stato di riposo, in attesa che lo StateManager
     * vi inietti una scena valida.
     */
    public Engine() {
        this.activeScene = null;
    }

    /**
     * Imposta o sostituisce la scena attiva che il motore dovrà processare.
     * Invocato dagli Stati (es. CinematicPrologueState) nel momento del loro caricamento.
     *
     * @param activeScene L'istanza della nuova scena ECS da elaborare,
     *                    oppure null per sospendere completamente i calcoli.
     */
    public void setActiveScene(Scene activeScene) {
        this.activeScene = activeScene;
    }

    /**
     * Restituisce la scena correntemente agganciata al motore.
     * Utile per la comunicazione con il RenderSystem o per il debug.
     *
     * @return L'istanza attiva, oppure null se il motore è in attesa.
     */
    public Scene getActiveScene() {
        return this.activeScene;
    }

    /**
     * Esegue il ciclo di calcolo logico e fisico per il singolo fotogramma.
     * Invocato direttamente dal Controller di Flusso (Loop Principale) ad ogni tick.
     * Estrae automaticamente i componenti dalla scena e ne esegue l'aggiornamento.
     */
    public void update() {
        if (this.activeScene != null) {

            /** La collezione dei moduli comportamentali estratti dalla cache della scena. */
            List<Updatable> updatables = this.activeScene.getUpdatables();

            if (updatables != null) {
                // Itera su tutti i Behavior e le Primitive ed esegue i loro calcoli isolati
                for (Updatable component : updatables) {
                    component.update();
                }
            }

            // --- SEZIONE RISERVATA AI FUTURI SISTEMI FISICI ---
            // Dopo aver aggiornato la logica individuale, qui verranno chiamati i "System" globali:
            // 1. CollisionSystem (per rilevare intersezioni tra Transform e Hitbox)
            // 2. PhysicsSystem (per applicare gravità o forze inerziali unificate)
            // 3. GarbageCollectorSystem (per rimuovere dalla scena le entità marcate per l'eliminazione)
        }
    }
}