package model.factory;

import model.component.data.ImageReferenceComponent;
import model.component.data.render.RenderLayer;
import model.component.data.render.RenderLayerComponent;
import model.component.logic.behavior.ui.SpinnerBehavior;
import model.component.spatial.Transform;
import model.component.visual.media.sprite.SpriteComponent;
import model.entity.Entity;
import view.service.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Fabbrica specializzata nella creazione di entità destinate all'Interfaccia Utente (UI).
 * Assembla elementi puramente visivi (icone, immagini di sfondo) iniettando
 * i componenti spaziali, i livelli di rendering e le logiche specifiche per la UI.
 */
public class UIFactory {

    /**
     * Costruttore privato che impedisce l'istanziazione di questa classe di utilità.
     */
    private UIFactory() {
        // Nessuna inizializzazione permessa
    }

    /**
     * Fabbrica un'entità visiva statica scalata per coprire interamente un'area target.
     * Calcola la scala dinamicamente in base alle dimensioni dell'immagine in memoria.
     *
     * @param entityId     L'identificativo testuale univoco per questa specifica entità.
     * @param imagePath    Il percorso o indirizzo della texture precaricata da applicare (es. "res/image.png").
     * @param targetWidth  La larghezza desiderata dello schermo.
     * @param targetHeight L'altezza desiderata dello schermo.
     * @param layer        Il livello di profondità (Z-Index) specificato tramite l'enum.
     * @return L'entità immagine assemblata con i componenti dati necessari.
     */
    public static Entity createAdaptiveImage(String entityId, String imagePath, int targetWidth, int targetHeight, RenderLayer layer) {
        /** Immagine cruda estratta esclusivamente per leggerne altezza e larghezza. */
        BufferedImage image = ImageManager.getInstance().getImage(imagePath);

        /** Fattore di scala di base usato come fallback in caso di immagine mancante. */
        double finalScale = 1.0;

        if (image != null) {
            double scaleX = (double) targetWidth / image.getWidth();
            double scaleY = (double) targetHeight / image.getHeight();
            finalScale = Math.max(scaleX, scaleY);
        }

        /** Il blocco spaziale dell'entità, posizionato alle coordinate di origine. */
        Transform transform = new Transform(0, 0, 0, finalScale);

        /** L'entità ECS vuota inizializzata col proprio ID univoco. */
        Entity adaptiveEntity = new Entity(entityId);

        /** Il componente dati che incapsula l'indirizzo del file immagine. */
        ImageReferenceComponent imageRef = new ImageReferenceComponent(imagePath);

        adaptiveEntity.addComponent(transform);
        adaptiveEntity.addComponent(imageRef);
        adaptiveEntity.addComponent(new SpriteComponent(imageRef));
        adaptiveEntity.addComponent(new RenderLayerComponent(layer));

        return adaptiveEntity;
    }

    /**
     * Fabbrica un'icona di caricamento rotante tramite l'iniezione del behavior specifico.
     *
     * @param entityId  L'identificativo testuale univoco per questa specifica entità.
     * @param imagePath Il percorso o indirizzo della texture dello spinner (es. "res/spinner.png").
     * @param x         La coordinata orizzontale di posizionamento.
     * @param y         La coordinata verticale di posizionamento.
     * @param scale     Il moltiplicatore di grandezza visiva.
     * @param layer     Il livello di profondità (Z-Index) su cui renderizzare l'entità.
     * @return L'entità spinner completamente autonoma e funzionante.
     */
    public static Entity createLoadingSpinner(String entityId, String imagePath, double x, double y, double scale, RenderLayer layer) {
        /** Il blocco spaziale dell'entità posizionato in base ai parametri in ingresso. */
        Transform transform = new Transform(x, y, 0, scale);

        /** L'entità ECS base. */
        Entity spinner = new Entity(entityId);

        /** Il componente dati che incapsula l'indirizzo del file immagine. */
        ImageReferenceComponent imageRef = new ImageReferenceComponent(imagePath);

        spinner.addComponent(transform);
        spinner.addComponent(imageRef);
        spinner.addComponent(new SpriteComponent(imageRef));
        spinner.addComponent(new RenderLayerComponent(layer));

        /** Componente logico UI per la rotazione costante a 240 gradi al secondo. */
        spinner.addComponent(new SpinnerBehavior(transform, 240.0));

        return spinner;
    }
}