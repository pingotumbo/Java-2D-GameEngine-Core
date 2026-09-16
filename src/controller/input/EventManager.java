package controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 * Servizio globale responsabile dell'intercettazione degli eventi di input hardware.
 * Ascolta gli eventi della tastiera generati dal pannello Swing e mantiene una mappa
 * aggiornata dello stato logico di ogni singolo tasto.
 * Estende java.util.Observable per permettere ad altri moduli (es. UI o PlayerController)
 * di registrarsi come Observer e reagire istantaneamente ai cambiamenti di stato.
 */
@SuppressWarnings("deprecation")
public class EventManager extends Observable implements KeyListener {

    /**
     * L'unica istanza globale allocata in memoria del gestore degli eventi.
     * Implementa il pattern Singleton per garantire che tutti i moduli del gioco
     * interroghino lo stesso identico stato della tastiera.
     */
    private static EventManager instance;

    /**
     * Array di valori booleani che mappa lo stato (premuto/rilasciato) di ogni tasto.
     * L'indice dell'array corrisponde al KeyCode nativo di Java (es. KeyEvent.VK_SPACE).
     * La dimensione 256 copre i codici standard della tastiera.
     */
    private final boolean[] keys;

    /**
     * Costruttore privato del gestore degli eventi.
     * Inizializza l'array degli stati dei tasti.
     */
    private EventManager() {
        this.keys = new boolean[256];
    }

    /**
     * Restituisce il punto di accesso globale all'istanza del gestore.
     * Applica l'allocazione ritardata (Lazy Initialization).
     *
     * @return L'istanza Singleton di EventManager.
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Verifica lo stato in tempo reale di un tasto specifico.
     * Utilizzato dai moduli logici (GameEngine) per il polling continuo degli input.
     *
     * @param keyCode Il codice intero del tasto da verificare.
     * @return true se il tasto risulta fisicamente premuto, false altrimenti.
     */
    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= this.keys.length) {
            return false;
        }
        return this.keys[keyCode];
    }

    /**
     * Callback di sistema invocata quando un tasto viene abbassato.
     * Aggiorna la mappa di stato interna e notifica gli Observer registrati.
     *
     * @param e L'oggetto evento contenente i metadati dell'input.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code >= 0 && code < this.keys.length) {
            this.keys[code] = true;

            setChanged();
            notifyObservers(e);
        }
    }

    /**
     * Callback di sistema invocata quando un tasto viene rilasciato.
     * Ripristina lo stato del tasto e notifica gli Observer registrati.
     *
     * @param e L'oggetto evento contenente i metadati dell'input.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code >= 0 && code < this.keys.length) {
            this.keys[code] = false;

            setChanged();
            notifyObservers(e);
        }
    }

    /**
     * Callback di sistema per la digitazione di caratteri.
     * Attualmente non implementata in quanto il motore si basa sulla pressione fisica
     * dei tasti (keyPressed/keyReleased) per la logica di gioco.
     *
     * @param e L'oggetto evento fornito dal sistema.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Nessuna implementazione richiesta
    }

    /**
     * Svuota completamente l'array degli stati della tastiera.
     * Previene il blocco logico dei tasti durante i cambi di stato (es. da menu a livello).
     */
    public void resetKeys() {
        for (int i = 0; i < this.keys.length; i++) {
            this.keys[i] = false;
        }
    }
}