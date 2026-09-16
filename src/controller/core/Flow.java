package controller.core;

import model.data.Config;
import model.event.EventManager;
import model.phase.PhaseManager;
import model.scene.Scene;
import model.service.TimeManager;

/**
 * Gestore del ciclo di vita principale dell'applicazione (Main Loop).
 * Implementa java.lang.Runnable per l'esecuzione asincrona su un Thread dedicato.
 * Calcola il Delta Time, aggiorna le metriche globali tramite TimeManager,
 * orchestra il motore logico (Engine), propaga gli eventi e aggiorna la grafica (Panel).
 */
public class Flow implements Runnable {

    /**
     * Il thread di sistema su cui viene eseguito il ciclo infinito dell'applicazione.
     */
    private Thread thread;

    /**
     * Il gestore globale delle macro-fasi, utilizzato per recuperare Engine e Panel.
     */
    private final PhaseManager phaseManager;

    /** Il gestore globale del tempo per la registrazione del Delta Time e degli FPS. */
    private final TimeManager timeManager;

    /** Flag booleano di controllo che determina lo stato di esecuzione del loop. */
    private boolean running;

    /**
     * Inizializza il gestore del flusso associandolo al PhaseManager.
     * * @param phaseManager Il direttore generale da cui prelevare i moduli logici e grafici.
     */
    public Flow(PhaseManager phaseManager) {
        this.phaseManager = phaseManager;
        this.timeManager = TimeManager.getInstance();
        this.running = false;
    }

    /** Innesca l'avvio del ciclo continuo di sistema. */
    public void startLoop() {
        if (this.thread == null) {
            this.running = true;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    /** Richiede l'interruzione sicura del ciclo di elaborazione. */
    public void stopLoop() {
        this.running = false;
    }

    /**
     * Logica esecutiva del thread contenente il loop principale.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long currentTime;
        long elapsedTime;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (this.running) {
            currentTime = System.nanoTime();
            elapsedTime = currentTime - lastTime;

            if (elapsedTime >= Config.TARGET_TIME_NS) {

                double deltaTime = elapsedTime / 1000000000.0;

                // 1. Aggiornamento dati temporali
                this.timeManager.updateTimeData(deltaTime, frames);

                // 2. Il Battito Logico: L'Engine aggiorna la logica interna della scena
                this.phaseManager.getEngine().update();

                // 3. Lo Smistamento Eventi: Consegna i messaggi (es. fine timer prologo)
                EventManager.getInstance().processPendingEvents();

                // =============================================================
                // IL PONTE: COLLEGA IL MODELLO ALLA VISTA
                // =============================================================
                /** La scena attualmente caricata nell'Engine. */
                Scene activeScene = this.phaseManager.getEngine().getActiveScene();

                if (activeScene != null) {
                    // Passa le entità caricate al pannello affinché il RenderSystem possa disegnarle
                    this.phaseManager.getPanel().setEntities(activeScene.getEntities());
                } else {
                    // Se non c'è scena, il pannello non deve disegnare nulla (null = nero)
                    this.phaseManager.getPanel().setEntities(null);
                }
                // =============================================================

                // 4. Il Battito Grafico: Richiama il repaint() di Swing
                this.phaseManager.getPanel().repaint();

                lastTime = currentTime;
                frames++;

            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
    }
}