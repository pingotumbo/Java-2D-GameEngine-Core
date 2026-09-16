package model.component.visual.filter;

import model.component.Component;

import java.awt.*;

/**
 * Componente dati modificatore che applica una tinta cromatica a un elemento visivo.
 * Può essere associato a sprite, forme geometriche o testi.
 */
public class ColorTintComponent implements Component {

    /**
     * L'oggetto colore nativo di Java utilizzato come filtro di sovrapposizione.
     */
    private Color tint;

    /**
     * Costruisce il componente con un colore specifico.
     *
     * @param tint Il colore da applicare.
     */
    public ColorTintComponent(Color tint) {
        this.tint = tint;
    }

    /**
     * Restituisce la tinta corrente.
     *
     * @return L'istanza di Color memorizzata.
     */
    public Color getTint() {
        return this.tint;
    }

    /**
     * Modifica la tinta cromatica.
     *
     * @param tint Il nuovo colore da sovrapporre.
     */
    public void setTint(Color tint) {
        this.tint = tint;
    }
}