package model.component.logic.behavior;

import model.component.Component;
import model.component.logic.Updatable;

/**
 * Classe base astratta per tutti i comportamenti complessi (Behavior).
 * Fornisce una struttura comune, come la possibilità di abilitare
 * o disabilitare l'esecuzione della logica a runtime.
 */
public abstract class AbstractBehavior implements Component, Updatable {

    /**
     * Flag che determina se il comportamento deve essere eseguito o ignorato.
     */
    protected boolean active;

    /**
     * Costruttore di base. Di default, ogni comportamento nasce attivo.
     */
    public AbstractBehavior() {
        this.active = true;
    }

    /**
     * Verifica lo stato di attività del behavior.
     *
     * @return true se attivo, false se in pausa.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Modifica lo stato di attività del comportamento.
     *
     * @param active true per accenderlo, false per spegnerlo.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}