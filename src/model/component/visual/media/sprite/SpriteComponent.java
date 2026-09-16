package model.component.visual.media.sprite;

import model.component.Component;
import model.component.data.ImageReferenceComponent;

import java.awt.*;

/**
 * Componente dati che definisce i metadati di rendering di uno sprite.
 * Incapsula il riferimento all'immagine e i modificatori visivi (visibilità,
 * opacità, tinta cromatica). Non contiene logica di disegno.
 */
public class SpriteComponent implements Component {

    /**
     * Il riferimento al componente dati che contiene l'ID dell'immagine base.
     */
    private final ImageReferenceComponent imageReference;

    /**
     * Flag che determina se l'entità deve essere processata dal RenderSystem.
     */
    private boolean visible;

    /**
     * Valore decimale da 0.0 (completamente trasparente) a 1.0 (completamente opaco).
     */
    private float opacity;

    /**
     * Il colore utilizzato per applicare un filtro o una tinta sopra l'immagine originale.
     * Se null, l'immagine viene renderizzata con i suoi colori originali.
     */
    private Color colorFilter;

    /**
     * Costruttore base dello SpriteComponent.
     * Imposta i valori di default: visibile, opacità al 100% e nessun filtro colore.
     *
     * @param imageReference Il componente contenente l'ID testuale dell'immagine.
     */
    public SpriteComponent(ImageReferenceComponent imageReference) {
        this.imageReference = imageReference;
        this.visible = true;
        this.opacity = 1.0f;
        this.colorFilter = null;
    }

    /**
     * Restituisce il componente di riferimento dell'immagine.
     *
     * @return L'istanza di ImageReferenceComponent associata.
     */
    public ImageReferenceComponent getImageReference() {
        return this.imageReference;
    }

    /**
     * Verifica se il componente è impostato per essere disegnato a schermo.
     *
     * @return true se visibile, false se nascosto.
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Modifica lo stato di visibilità dello sprite.
     *
     * @param visible true per mostrare lo sprite, false per nasconderlo.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Restituisce il livello di opacità corrente.
     *
     * @return Un valore float compreso tra 0.0f e 1.0f.
     */
    public float getOpacity() {
        return this.opacity;
    }

    /**
     * Imposta un nuovo livello di opacità.
     * I valori al di fuori del range consentito vengono ignorati per sicurezza.
     *
     * @param opacity Il nuovo valore float (0.0f = invisibile, 1.0f = opaco).
     */
    public void setOpacity(float opacity) {
        if (opacity >= 0.0f && opacity <= 1.0f) {
            this.opacity = opacity;
        }
    }

    /**
     * Restituisce l'attuale filtro di colore applicato allo sprite.
     *
     * @return L'oggetto Color di Java, o null se non è applicato alcun filtro.
     */
    public Color getColorFilter() {
        return this.colorFilter;
    }

    /**
     * Applica o rimuove un filtro colore dallo sprite.
     *
     * @param colorFilter L'istanza di java.awt.Color da sovrapporre, o null per rimuoverlo.
     */
    public void setColorFilter(Color colorFilter) {
        this.colorFilter = colorFilter;
    }
}