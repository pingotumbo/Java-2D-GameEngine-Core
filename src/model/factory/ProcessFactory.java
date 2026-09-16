package model.factory;

import model.component.logic.behavior.process.TimerEventBehavior;
import model.entity.Entity;
import model.event.EventType;

/**
 * Fabbrica specializzata nella creazione di entità logiche e di processo.
 * Queste entità sono solitamente invisibili (prive di componenti di rendering)
 * e servono a gestire temporizzazioni, trigger di eventi o regole di scena.
 */
public class ProcessFactory {

    /**
     * Costruttore privato che impedisce l'istanziazione di questa classe di utilità.
     */
    private ProcessFactory() {
        // Nessuna inizializzazione permessa
    }

    /**
     * Fabbrica un "Regista di Scena" invisibile delegato alla temporizzazione.
     * Utilizza un componente logico per spedire un messaggio di sistema a tempo debito.
     *
     * @param entityId   L'identificativo testuale univoco dell'entità logica.
     * @param durationMs L'attesa in millisecondi prima che l'evento scatti.
     * @param eventType  La tipologia di evento da innescare allo scadere del tempo.
     * @return Un'entità priva di sprite, operante esclusivamente a livello logico.
     */
        /** L'entità ECS base, identificata in modo univoco. */
        Entity director = new Entity(entityId);

        /** Iniezione del comportamento di temporizzazione dal pacchetto process. */
        director.addComponent(new TimerEventBehavior(director, durationMs, eventType));

        return director;
    }
}