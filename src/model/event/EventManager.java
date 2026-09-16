package model.event;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

/**
 * Gestore globale degli eventi di sistema (Event Bus).
 * Implementa il pattern Singleton ed estende java.util.Observable.
 * Accoda i messaggi (EventMessage) generati durante il ciclo di esecuzione per processarli
 * in modo sincrono e sicuro all'inizio del fotogramma successivo,
 * prevenendo anomalie di concorrenza e alterazioni impreviste della memoria.
 */
@SuppressWarnings("deprecation")
public class EventManager extends Observable {

    /**
     * L'unica istanza statica e univoca allocata in memoria per questa classe (Singleton).
     */
    private static EventManager instance;

    /**
     * Struttura dati FIFO (First-In-First-Out) per la memorizzazione temporanea delle buste logiche.
     */
    private final Queue<EventMessage> eventQueue;

    /**
     * Costruttore privato. Impedisce l'istanziazione dall'esterno e inizializza
     * la coda dei messaggi in attesa.
     */
    private EventManager() {
        this.eventQueue = new LinkedList<>();
    }

    /**
     * Fornisce il punto di accesso globale all'istanza univoca del gestore eventi.
     *
     * @return L'istanza statica globale dell'EventManager.
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Inserisce un nuovo messaggio all'interno della coda di elaborazione.
     * Viene chiamato dai componenti ECS (es. TimerEventBehavior) quando si verifica un trigger.
     *
     * @param message Il pacchetto contenente la tipologia (EventType) e il mittente dell'evento.
     */
    public void enqueue(EventMessage message) {
        this.eventQueue.offer(message);
    }

    /**
     * Svuota la coda dei messaggi ed esegue la notifica di broadcast a tutti gli iscritti.
     * In un'architettura ECS ordinata, questo metodo dovrebbe essere chiamato dal Flow
     * (Main Loop) all'inizio o alla fine di ogni singolo tick.
     */
    public void processPendingEvents() {
        while (!this.eventQueue.isEmpty()) {
            /** Il pacchetto dati attualmente in fase di spedizione, estratto dalla testa della coda. */
            EventMessage currentMessage = this.eventQueue.poll();

            // Marca lo stato interno come alterato (passaggio obbligato per java.util.Observable)
            super.setChanged();

            // Notifica tutti gli Observer (es. MenuPhase) passando la busta intera come argomento
            super.notifyObservers(currentMessage);
        }
    }
}