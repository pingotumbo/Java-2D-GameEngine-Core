package src;

import controller.core.Flow;
import model.data.Config;
import model.phase.PhaseManager;
import model.phase.menu.MenuPhase;
import model.service.Engine;
import model.service.SceneLoader;
import view.component.Frame;
import view.component.Panel;

/**
 * Classe principale di avvio dell'applicazione (Entry Point).
 * Responsabile dell'assemblaggio architetturale tramite Dependency Injection manuale.
 * Configura il pattern Model-View-Controller istanziando i motori logici,
 * i servizi di caricamento e la finestra grafica prima di innescare il Loop Principale.
 */
public class App {

    /**
     * Punto di ingresso per la Java Virtual Machine (JVM).
     * Coordina l'accensione dei moduli, definisce la risoluzione nativa del gioco
     * richiamando la classe Config e stabilisce la macro-fase iniziale (MenuPhase).
     *
     * @param args Argomenti testuali passati da riga di comando all'avvio del programma (non utilizzati).
     */
    public static void main(String[] args) {

        System.out.println("[System] Inizializzazione del Motore ECS in corso...");

        // =====================================================================
        // 1. IL MODELLO (Logica e Dati)
        // =====================================================================

        /**
         * Il nucleo del motore logico.
         * Si occuperà di processare tutti i componenti logici delle entità attive.
         */
        Engine engine = new Engine();

        /**
         * Il servizio responsabile della costruzione delle scene.
         * Legge i dati statici e popola la Scene scaricando l'onere dai manager di stato.
         */
        SceneLoader sceneLoader = new SceneLoader();


        // =====================================================================
        // 2. LA VISTA (Grafica e Interfaccia)
        // =====================================================================

        /**
         * Il contenitore grafico principale.
         * Al suo interno risiederà il RenderSystem che interrogherà le entità del Model.
         * Utilizza le costanti globali per definire la risoluzione nativa (800x600).
         */
        Panel panel = new Panel(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        /**
         * La cornice della finestra del sistema operativo.
         * Ospita il Panel e lo rende visibile all'utente interagendo con le API native.
         */
        Frame frame = new Frame(panel);


        // =====================================================================
        // 3. IL CONTROLLER DI STATO (Macchina a Stati Gerarchica)
        // =====================================================================

        /**
         * Il gestore centrale delle macro-fasi di gioco.
         * Coordina i passaggi tra i grandi capitoli (es. Menu, Esplorazione) e funge
         * da Service Locator globale per fornire Engine e Panel alle classi sottostanti.
         */
        PhaseManager phaseManager = new PhaseManager(engine, panel, sceneLoader);

        /**
         * Definizione del punto di partenza logico dell'applicazione.
         * Istanza della macro-fase dei menu, che al suo interno avvierà automaticamente
         * il CinematicPrologueState tramite il proprio StateManager locale.
         */
        MenuPhase initialPhase = new MenuPhase(phaseManager);

        // Imposta ufficialmente i menu come prima macro-fase attiva del programma
        phaseManager.changePhase(initialPhase);


        // =====================================================================
        // 4. AVVIO DEL FLUSSO (Main Loop)
        // =====================================================================

        /**
         * Il motore del tempo (Thread principale asincrono).
         * Pilota direttamente l'Engine e il Panel garantendo una frequenza
         * di aggiornamento e rendering costante.
         */
        Flow flow = new Flow(phaseManager);

        System.out.println("[System] Setup completato. Avvio del Main Loop...");

        /** Innesca il loop infinito che manterrà in vita l'applicazione. */
        flow.startLoop();
    }
}