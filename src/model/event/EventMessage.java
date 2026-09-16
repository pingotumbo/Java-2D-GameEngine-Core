package model.event;

/**
 * Rappresenta la "busta da lettera" (Payload) che viaggia attraverso l'Event Bus.
 * Incapsula l'etichetta dell'evento (il "Cosa") e un riferimento al mittente (il "Chi"),
 * permettendo a chi ascolta di avere il contesto completo dell'azione.
 */
public class EventMessage {

    /**
     * Il tipo di evento generato (l'etichetta semantica).
     * Definisce la natura del messaggio inviato.
     */
    private final EventType type;

    /**
     * Il riferimento al mittente che ha innescato l'evento.
     * Dichiarato come Object per permettere a chiunque (Entità, Motore, Interfaccia)
     * di generare eventi in modo puramente generico e disaccoppiato.
     */
    private final Object source;

    /**
     * Costruttore base del messaggio di evento.
     *
     * @param type   L'enumerativo che definisce la natura dell'evento.
     * @param source L'oggetto mittente (es. un'Entity o un Behavior) che ha creato questo messaggio.
     */
    public EventMessage(EventType type, Object source) {
        this.type = type;
        this.source = source;
    }

    /**
     * Recupera la tipologia dell'evento contenuto nel messaggio.
     *
     * @return L'enumerativo EventType associato.
     */
    public EventType getType() {
        return this.type;
    }

    /**
     * Recupera l'identità del mittente dell'evento.
     *
     * @return L'istanza dell'oggetto originario che ha generato il segnale.
     */
    public Object getSource() {
        return this.source;
    }
}