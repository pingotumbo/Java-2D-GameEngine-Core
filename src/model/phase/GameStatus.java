package model.phase;

/**
 * Enumerazione che definisce gli identificatori globali per tutti gli stati
 * di gioco (Macchina a Stati).
 * Permette di tracciare in quale fase specifica si trova l'applicazione.
 */
public enum GameStatus {
    /**
     * Fase di caricamento iniziale degli asset nella memoria RAM/VRAM.
     */
    LOADING,
    /**
     * Schermata di avvio cinematografica prima del menu principale.
     */
    CINEMATIC_PROLOGUE,
    /**
     * Menu principale del gioco (Nuova Partita, Opzioni, Esci).
     */
    MENU,
    /**
     * Stanza di intermezzo per il passaggio tra i livelli (Ascensore).
     */
    ELEVATOR_ROOM,
    /**
     * Fase di inserimento codice o puzzle.
     */
    CODE,
    /**
     * Fase dedicata alla creazione o modifica delle mappe.
     */
    EDITOR,
    /**
     * Fase di interazione con un terminale in-game.
     */
    TERMINAL,
    /**
     * Stanza standard del dungeon con esplorazione e combattimento.
     */
    DUNGEON_ROOM,
    /**
     * Stanza di monitoraggio per la gestione delle telecamere o mappa.
     */
    MONITOR_ROOM,
    /**
     * Animazione di transizione tra due aree distinte.
     */
    CINEMATIC_TRANSITION,
    /**
     * Schermata di Game Over in seguito alla sconfitta del giocatore.
     */
    DEATH,
    /**
     * Animazione di ingresso nel laboratorio.
     */
    CINEMATIC_ENTER_LAB,
    /**
     * Schermata dei riconoscimenti o dei punteggi massimi.
     */
    HALL_OF_FAME,
    /**
     * Gioco in pausa, loop fisico interrotto ma rendering attivo.
     */
    PAUSE
}