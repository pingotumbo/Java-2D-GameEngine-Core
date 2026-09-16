package model.scene;

import model.entity.Entity;

import java.util.List;

/**
 * Classe base astratta che definisce i dati strutturali per avviare una scena di gioco.
 * Fornisce la gestione dell'ID di scena e dichiara il contratto obbligatorio
 * per la costruzione delle entità.
 */
public abstract class SceneData {

    /**
     * L'identificatore testuale univoco della scena.
     */
    private final String sceneId;

    /**
     * Inizializza la configurazione di base della scena.
     *
     * @param sceneId L'identificativo univoco (es. "PROLOGO").
     */
    public SceneData(String sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * Restituisce l'identificatore testuale univoco della scena.
     *
     * @return La stringa identificativa fornita alla costruzione.
     */
    public String getSceneId() {
        return this.sceneId;
    }

    /**
     * Costruisce e restituisce l'elenco delle entità che compongono la scena.
     * I percorsi delle immagini verranno estratti automaticamente dal motore
     * leggendo gli ImageReferenceComponent delle entità generate.
     *
     * @return La lista delle entità ECS pronte all'uso.
     */
    public abstract List<Entity> buildEntities();
}