package model.service;

import model.component.data.ImageReferenceComponent;
import model.entity.Entity;
import model.scene.Scene;
import model.scene.SceneData;
import view.service.ImageManager;

import java.util.List;

/**
 * Servizio operativo responsabile della traduzione dei dati statici (SceneData)
 * in un ambiente di gioco dinamico ed eseguibile (Scene).
 * Analizza le entità appena create, delega il caricamento automatico delle risorse visive
 * all'ImageManager e popola il contenitore ECS pronto per l'elaborazione del Game Loop.
 */
public class SceneLoader {

    /**
     * Riferimento al manager Singleton che gestisce l'accesso al file system
     * e la memorizzazione in cache delle risorse grafiche in RAM.
     */
    private final ImageManager imageManager;

    /**
     * Costruttore di default.
     * Inizializza il servizio agganciandosi all'istanza globale dell'ImageManager.
     */
    public SceneLoader() {
        this.imageManager = ImageManager.getInstance();
    }

    /**
     * Esegue l'intero processo di allocazione e popolamento di un nuovo livello.
     * Riceve la configurazione (SceneData), richiede la fabbricazione delle entità,
     * ne ispeziona i componenti per l'auto-caricamento della grafica e genera la Scene.
     *
     * @param data L'oggetto che estende SceneData, contenente le direttive del livello.
     * @return Un'istanza completamente inizializzata e popolata della classe Scene.
     */
    public Scene loadScene(SceneData data) {
        System.out.println("[SceneLoader] Inizio caricamento scena: " + data.getSceneId());

        /** L'ambiente di memoria vuoto che farà da contenitore per il livello attivo. */
        Scene activeScene = new Scene();

        /** La lista degli attori base appena fabbricati invocando le rispettive Factory. */
        List<Entity> sceneEntities = data.buildEntities();

        if (sceneEntities != null) {
            /** Ciclo iterativo per analizzare e smistare ogni entità generata. */
            for (Entity entity : sceneEntities) {

                /** Ricerca dinamica del componente che dichiara la necessità di un file grafico su disco. */
                ImageReferenceComponent imageRef = entity.getComponent(ImageReferenceComponent.class);

                // Se l'entità possiede una grafica, innesca il caricamento automatico in memoria video
                if (imageRef != null) {
                    this.imageManager.load(imageRef.getImageId());
                }

                // Inserisce l'entità nel mondo. La Scene smisterà automaticamente i componenti logici in cache.
                activeScene.addEntity(entity);
            }
        }

        System.out.println("[SceneLoader] Scena " + data.getSceneId() + " caricata con successo.");

        return activeScene;
    }
}