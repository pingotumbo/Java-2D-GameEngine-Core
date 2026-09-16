package model.component.visual;

import model.component.data.render.RenderLayer;

import java.awt.*;

/**
 * Interfaccia funzionale estesa che definisce il contratto per i componenti visivi.
 * Combina l'approccio di rendering attivo (metodo draw) con i metadati necessari
 * per l'ordinamento gerarchico (RenderLayer) e la gestione della telecamera (UI flag).
 */
public interface Drawable {

    /**
     * Esegue le istruzioni di disegno sul buffer grafico fornito.
     *
     * @param g2d L'oggetto Graphics2D fornito dal GamePanel.
     */
    void draw(Graphics2D g2d);

    /**
     * Restituisce il livello di profondità a cui questo elemento deve essere disegnato.
     *
     * @return Il valore enumerativo RenderLayer associato a questo componente.
     */
    RenderLayer getLayer();

    /**
     * Determina se il componente fa parte dell'interfaccia utente fissa o del mondo.
     *
     * @return true se è un elemento della UI fisso a schermo, false se è nel mondo.
     */
    boolean isUIElement();
}