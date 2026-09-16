package model.component.data;

import model.component.Component;

/**
 * Componente dati che memorizza il riferimento logico a una risorsa visiva (Texture o Sprite).
 * Viene letto dai sistemi di rendering (come lo StaticSpriteComponent) per sapere
 * quale immagine estrarre dal TextureManager in fase di disegno.
 */
public class ImageReferenceComponent implements Component {

    /**
     * L'identificativo chiave utilizzato per rintracciare l'immagine precaricata
     * nella memoria video (es. "PROLOGO_COVER").
     */
    private String imageId;

    /**
     * Costruttore del componente di riferimento texture.
     *
     * @param textureId La chiave testuale dell'immagine.
     */
    //ctrl alt shift 0 d, roba strana, sembra mostrare l'UML delle classi
    public ImageReferenceComponent(String textureId) {
        this.imageId = textureId;
    }

    /**
     * Restituisce l'identificativo dell'immagine associata.
     *
     * @return La stringa textureId.
     */
    public String getImageId() {
        return this.imageId;
    }

    /**
     * Sovrascrive il riferimento all'immagine, permettendo di cambiare la grafica
     * dell'entità a runtime senza dover distruggere il componente visivo.
     *
     * @param newImageId La nuova chiave testuale da assegnare.
     */
    public void setImageId(String newImageId) {
        this.imageId = newImageId;
    }
}