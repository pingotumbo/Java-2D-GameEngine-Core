package model.component.logic.behavior.process;

import model.component.Component;
import model.component.logic.Updatable;
import model.entity.Entity;
import model.event.EventManager;
import model.event.EventMessage;
import model.event.EventType;
import model.service.TimeManager;

/**
 * Comportamento logico (Behavior) che agisce come un conto alla rovescia invisibile.
 * Legge il Delta Time dal motore per accumulare il tempo trascorso.
 * Una volta raggiunta la soglia richiesta, genera e spedisce un evento di sistema
 * sulla coda dell'EventManager, disattivandosi automaticamente dopo l'invio.
 */
public class TimerEventBehavior implements Component, Updatable {

    /**
     * Il riferimento all'entità padrona di questo componente.
     * Funge da mittente (source) all'interno del messaggio di evento.
     */
    private final Entity sender;

    /**
     * La durata dell'attesa richiesta, convertita in frazioni di secondo
     * per essere compatibile con l'aritmetica basata sul Delta Time.
     */
    private final double targetTimeInSeconds;

    /**
     * L'etichetta semantica dell'evento da spedire allo scadere del timer.
     */
    private final EventType eventType;

    /**
     * Il tempo attualmente accumulato (in secondi) dall'inizio dell'esecuzione.
     */
    private double accumulatedTime;

    /**
     * Flag di stato che determina se il comportamento deve continuare a contare.
     * Viene impostato a false dopo lo scatto del timer per risparmiare cicli di calcolo.
     */
    private boolean active;

    /**
     * Costruisce il comportamento del timer impostandone il mittente e la durata.
     *
     * @param sender     L'entità "regista" che spedirà il messaggio.
     * @param durationMs I millisecondi di attesa desiderati prima dell'innesco.
     * @param eventType  La tipologia di evento da comunicare.
     */
    public TimerEventBehavior(Entity sender, long durationMs, EventType eventType) {
        this.sender = sender;
        this.targetTimeInSeconds = durationMs / 1000.0;
        this.eventType = eventType;
        this.accumulatedTime = 0.0;
        this.active = true;
    }

    /**
     * Aggiorna l'orologio interno invocato dall'Engine a ogni frame.
     * Somma il Delta Time e, quando il tempo raggiunge la soglia,
     * spedisce il messaggio e si disattiva permanentemente.
     */
    @Override
    public void update() {
        if (this.active) {

            /** La frazione di secondo trascorsa dall'ultimo frame, recuperata dal Singleton temporale. */
            double dt = TimeManager.getInstance().getDeltaTime();

            this.accumulatedTime += dt;

            // Controllo della soglia di scatto
            if (this.accumulatedTime >= this.targetTimeInSeconds) {

                // Spegne il behavior per prevenire l'invio multiplo e risparmiare calcoli
                this.active = false;

                System.out.println("[TimerEventBehavior] Tempo scaduto! Spedisco: " + this.eventType);

                /** La "busta" contenente l'etichetta dell'evento e l'entità mittente. */
                EventMessage message = new EventMessage(this.eventType, this.sender);

                // Consegna il messaggio all'EventManager per il broadcast globale
                EventManager.getInstance().enqueue(message);
            }
        }
    }
}