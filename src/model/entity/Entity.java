package model.entity;

import model.component.Component;
import model.component.data.IdentityComponent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Contenitore universale puro per gli attori del gioco (Architettura ECS).
 * Gestisce un numero dinamico di componenti tramite una mappa tipizzata.
 * Completamente agnostico rispetto a logiche di gioco, grafica o stati.
 */
public class Entity {

    /**
     * Dizionario che mappa la classe di un componente alla sua istanza effettiva.
     * Garantisce l'unicità del tipo di componente per entità.
     */
    private final Map<Class<? extends Component>, Component> components;

    /**
     * Costruisce una nuova entità vuota, assegnandole l'identità immutabile
     * tramite l'inserimento forzato di un IdentityComponent.
     *
     * @param id L'identificativo testuale univoco (es. "PLAYER_1").
     */
    public Entity(String id) {
        this.components = new HashMap<>();
        this.addComponent(new IdentityComponent(id));
    }

    /**
     * Inserisce o sovrascrive un modulo all'interno del contenitore.
     *
     * @param component L'istanza concreta del componente da collegare.
     */
    public void addComponent(Component component) {
        this.components.put(component.getClass(), component);
    }

    /**
     * Estrae un componente specifico basandosi sulla sua classe.
     *
     * @param componentClass La classe del componente desiderato.
     * @param <T>            Il tipo generico del componente.
     * @return L'istanza del componente richiesto, oppure null se assente.
     */
    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(this.components.get(componentClass));
    }

    /**
     * Restituisce tutti i componenti attualmente associati.
     * Utilizzato dai System per iterare sui dati.
     *
     * @return Una collezione iterabile dei moduli istanziati.
     */
    public Collection<Component> getComponents() {
        return this.components.values();
    }

    /**
     * Metodo di utilità (scorciatoia) che restituisce l'identificatore testuale
     * univoco dell'entità, estraendolo direttamente dal suo IdentityComponent.
     *
     * @return La stringa identificativa assegnata al momento della creazione.
     */
    public String getId() {
        /** Estrae l'IdentityComponent dalla mappa e ne legge il valore interno. */
        return this.getComponent(IdentityComponent.class).getId();
    }
}