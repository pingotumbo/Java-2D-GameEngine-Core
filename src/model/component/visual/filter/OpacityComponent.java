package model.component.visual.filter;

import model.component.Component;

/**
 * Componente dati modificatore che gestisce il livello di trasparenza (canale Alpha)
 * di un elemento visivo durante la fase di rendering.
 */
public class OpacityComponent implements Component {

    /**
     * Il valore di trasparenza, compreso strettamente tra 0.0f (invisibile) e 1.0f (opaco).
     */
    private float alpha;

    /**
     * Costruisce il componente con un valore di opacità iniziale.
     *
     * @param alpha Il valore float da 0.0f a 1.0f.
     */
    public OpacityComponent(float alpha) {
        this.setAlpha(alpha);
    }

    /**
     * Restituisce l'opacità attuale.
     *
     * @return Il valore float del canale alpha.
     */
    public float getAlpha() {
        return this.alpha;
    }

    /**
     * Modifica l'opacità garantendo che il valore rimanga entro i limiti consentiti.
     *
     * @param alpha Il nuovo valore float (0.0f - 1.0f).
     */
    public void setAlpha(float alpha) {
        if (alpha < 0.0f) {
            this.alpha = 0.0f;
        } else if (alpha > 1.0f) {
            this.alpha = 1.0f;
        } else {
            this.alpha = alpha;
        }
    }
}