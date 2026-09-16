package model.component.visual.media.pose;

import model.component.Component;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Componente dati che funge da macchina a stati in sola lettura per le animazioni.
 * Associa staticamente gli stati logici (pose) agli identificativi delle sequenze.
 * Implementa l'Observer pattern per intercettare i cambiamenti di stato dell'entità
 * e aggiornare dinamicamente il puntatore alla sequenza attiva.
 */
@SuppressWarnings("deprecation")
public class PoseComponent implements Component, Observer {

    /**
     * Mappa associativa immutabile che collega ogni stato logico (EntityPose) all'ID testuale
     * della rispettiva sequenza animata. Inizializzata solo alla creazione del componente.
     */
    private final Map<EntityPose, String> poseToSequenceMap;

    /**
     * Lo stato visivo in cui si trova attualmente l'entità.
     */
    private EntityPose currentPose;

    /**
     * L'ID della sequenza animata corrispondente alla posa corrente.
     * Mantenuto in cache per garantire un accesso immediato a ogni frame.
     */
    private String activeSequenceId;

    /**
     * Costruttore base del componente a pose.
     * Congela la mappa delle animazioni e imposta lo stato di partenza.
     *
     * @param poseToSequenceMap La mappa precompilata con tutte le associazioni posa-sequenza.
     * @param initialPose       La posa in cui si trova l'entità al momento della creazione.
     */
    public PoseComponent(Map<EntityPose, String> poseToSequenceMap, EntityPose initialPose) {
        this.poseToSequenceMap = poseToSequenceMap;
        this.currentPose = initialPose;
        this.activeSequenceId = this.poseToSequenceMap.get(initialPose);
    }

    /**
     * Restituisce la mappa completa e immutabile delle associazioni.
     *
     * @return La struttura dati che collega le pose alle stringhe ID.
     */
    public Map<EntityPose, String> getPoseToSequenceMap() {
        return this.poseToSequenceMap;
    }

    /**
     * Restituisce la posa attualmente registrata nel componente.
     *
     * @return L'istanza di EntityPose corrente.
     */
    public EntityPose getCurrentPose() {
        return this.currentPose;
    }

    /**
     * Restituisce l'ID della sequenza che dovrebbe essere riprodotta per la posa attuale.
     * Questo è il dato che il sistema di animazione leggerà a ogni tick.
     *
     * @return La stringa identificativa della sequenza, o null se la posa non è mappata.
     */
    public String getActiveSequenceId() {
        return this.activeSequenceId;
    }

    /**
     * Metodo di callback invocato automaticamente dall'Observable (es. l'Entity)
     * quando si verifica un cambio di stato formale.
     * Aggiorna la posa corrente e l'ID della sequenza attiva se il nuovo stato è mappato.
     *
     * @param o   L'oggetto Observable che ha lanciato la notifica.
     * @param arg L'argomento passato con la notifica (deve essere un'istanza di EntityPose).
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof EntityPose) {
            EntityPose newPose = (EntityPose) arg;
            this.currentPose = newPose;
            this.activeSequenceId = this.poseToSequenceMap.get(newPose);
        }
    }
}