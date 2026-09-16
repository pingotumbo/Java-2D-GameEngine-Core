package model.scene;

import model.component.Component;
import model.component.logic.Updatable;
import model.entity.Entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contenitore dinamico del motore ECS per la scena attiva.
 * Archivia le Entity e smista automaticamente i moduli logici (Updatable)
 * in una cache ad alte prestazioni per il Game Loop.
 */
public class Scene {

    /**
     * Tabella associativa delle entità attive. Mantiene l'ordine di inserimento.
     */
    private final Map<String, Entity> entityMap;

    /**
     * Cache ad accesso rapido per tutti i componenti che necessitano di aggiornamento.
     */
    private final List<Updatable> cachedUpdatables;

    /**
     * Inizializza la memoria della stanza di gioco.
     */
    public Scene() {
        this.entityMap = new LinkedHashMap<>();
        this.cachedUpdatables = new ArrayList<>();
    }

    /**
     * Inserisce un'entità e scansiona i suoi componenti per popolare la cache logica.
     *
     * @param entity L'entità ECS da aggiungere al mondo.
     */
    public void addEntity(Entity entity) {
        this.entityMap.put(entity.getId(), entity);

        // Estrae dinamicamente tutti i componenti dell'entità
        for (Component component : entity.getComponents()) {
            if (component instanceof Updatable) {
                this.cachedUpdatables.add((Updatable) component);
            }
        }
    }

    /**
     * Rimuove un'entità e ripulisce le cache logiche dai suoi componenti.
     *
     * @param id L'identificatore univoco dell'entità da distruggere.
     */
    public void removeEntity(String id) {
        Entity entityToRemove = this.entityMap.remove(id);

        if (entityToRemove != null) {
            for (Component component : entityToRemove.getComponents()) {
                if (component instanceof Updatable) {
                    this.cachedUpdatables.remove((Updatable) component);
                }
            }
        }
    }

    /**
     * Distrugge tutte le entità correnti e svuota la memoria.
     */
    public void clearScene() {
        this.entityMap.clear();
        this.cachedUpdatables.clear();
    }

    /**
     * @return La collezione in sola lettura di tutte le entità presenti.
     */
    public Iterable<Entity> getEntities() {
        return this.entityMap.values();
    }

    /**
     * @return La lista dei componenti logici da processare a ogni tick.
     */
    public List<Updatable> getUpdatables() {
        return this.cachedUpdatables;
    }
}