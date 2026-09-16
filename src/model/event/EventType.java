package model.event;

/**
 * Enumerazione che definisce le etichette (le "causali") dei messaggi di evento.
 * Serve a far capire immediatamente a chi riceve la busta di che tipo di evento
 * si tratta, garantendo la sicurezza dei tipi (Type Safety) ed evitando l'uso di stringhe.
 */
public enum EventType {

    /**
     * Evento scatenato quando il giocatore raggiunge l'uscita della stanza.
     */
    ROOM_EXIT_REACHED,

    /**
     * Evento innescato all'attivazione di un terminale logico nel mondo di gioco.
     */
    TERMINAL_ACTIVATED,

    /**
     * Evento fatale che indica l'azzeramento della salute del giocatore.
     */
    PLAYER_DEATH,

    /**
     * Evento di sistema per richiedere l'interruzione temporanea del flusso fisico.
     */
    PAUSE_REQUESTED,

    /**
     * Evento che conferma la chiusura della schermata di sconfitta da parte dell'utente.
     */
    GAME_OVER_ACKNOWLEDGED,

    /**
     * Evento lanciato allo scadere di un timer generico in una sequenza.
     */
    STATE_SEQUENCE_COMPLETED,

    /**
     * Evento specifico che segnala la fine dell'animazione del prologo cinematografico.
     */
    PROLOGUE_END
}