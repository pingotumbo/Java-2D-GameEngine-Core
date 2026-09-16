package view.component;

import model.component.spatial.Transform;
import model.component.visual.media.sprite.SpriteComponent;
import model.entity.Entity;
import view.service.ImageManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Utilità visiva (View) delegata al rendering di una singola entità ECS.
 * È una classe "stateless" (senza stato interno): non memorizza frame né osserva eventi.
 * Si limita a interrogare i componenti dati dell'Entity a ogni ciclo di disegno
 * per proiettarne l'immagine su schermo applicando le corrette trasformazioni spaziali.
 */
public class SpriteRenderer {

    /**
     * Legge i dati spaziali e visivi dall'entità e disegna l'immagine corrispondente sul buffer.
     * Applica dinamicamente rotazione, scala e livello di opacità.
     *
     * @param entity L'entità ECS da disegnare.
     * @param g2d    Il contesto grafico di Java2D fornito dal motore di rendering.
     */
    public static void render(Entity entity, Graphics2D g2d) {
        /** Il componente che contiene le informazioni sull'immagine e la sua visibilità. */
        SpriteComponent spriteData = entity.getComponent(SpriteComponent.class);

        /** Il componente che descrive la posizione, la rotazione e la scala nel mondo. */
        Transform transform = entity.getComponent(Transform.class);

        // Se l'entità non ha i componenti necessari o è impostata come invisibile, interrompe il rendering
        if (spriteData == null || transform == null || !spriteData.isVisible()) {
            return;
        }

        /** L'identificatore del percorso dell'immagine estratto dal componente. */
        String imagePath = spriteData.getImageReference().getImageId();

        /** I pixel dell'immagine prelevati direttamente dal manager della memoria video. */
        BufferedImage image = ImageManager.getInstance().getImage(imagePath);

        if (image != null) {
            /** Lo stato originale delle trasformazioni del pennello, salvato per poterlo ripristinare. */
            AffineTransform oldTransform = g2d.getTransform();

            /** Lo stato originale della composizione dei colori (es. l'opacità globale), salvato per il ripristino. */
            Composite oldComposite = g2d.getComposite();

            /** La coordinata orizzontale assoluta in cui disegnare l'oggetto. */
            int x = (int) transform.getX();

            /** La coordinata verticale assoluta in cui disegnare l'oggetto. */
            int y = (int) transform.getY();

            /** L'angolo di rotazione attuale espresso in gradi. */
            double rotation = transform.getRotation();

            /** Il fattore di moltiplicazione dimensionale. */
            double scale = transform.getScale();

            /** Il livello di trasparenza attuale estratto dallo SpriteComponent. */
            float opacity = spriteData.getOpacity();

            // 1. GESTIONE OPACITÀ
            if (opacity < 1.0f) {
                /** La regola di fusione Alpha di Java2D che imposta la trasparenza. */
                AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
                g2d.setComposite(alpha);
            }

            // 2. CALCOLO DIMENSIONI SCALATE
            /** La larghezza finale dell'immagine dopo l'applicazione della scala. */
            int drawWidth = (int) (image.getWidth() * scale);

            /** L'altezza finale dell'immagine dopo l'applicazione della scala. */
            int drawHeight = (int) (image.getHeight() * scale);

            // 3. GESTIONE ROTAZIONE E DISEGNO
            if (rotation != 0.0) {
                // Imposta il perno di rotazione esattamente al centro dell'immagine scalata
                g2d.rotate(Math.toRadians(rotation), x + (drawWidth / 2.0), y + (drawHeight / 2.0));
            }

            // Disegna l'immagine applicando le coordinate e le dimensioni calcolate
            g2d.drawImage(image, x, y, drawWidth, drawHeight, null);

            // 4. RIPRISTINO DEL CONTESTO GRAFICO
            // Riporta il pennello di Java2D allo stato originale per non influenzare le altre entità
            g2d.setTransform(oldTransform);
            g2d.setComposite(oldComposite);
        }
    }
}