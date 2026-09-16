package view.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servizio globale (Singleton) responsabile dell'accesso al file system,
 * del caricamento delle risorse grafiche e della loro archiviazione in memoria RAM.
 * Evita il caricamento multiplo della stessa immagine sul disco e fornisce
 * strumenti avanzati per il ritaglio degli Sprite Sheet.
 */
public class ImageManager {

    /**
     * L'unica istanza globale allocata in memoria dell'ImageManager.
     * Implementa il pattern architetturale Singleton per un accesso centralizzato.
     */
    private static ImageManager instance;

    /**
     * Mappa associativa (Cache) che archivia le immagini caricate.
     * La chiave (String) è l'indirizzo/percorso dell'immagine.
     * Il valore (BufferedImage) è l'effettiva matrice di pixel pronta per il rendering.
     */
    private final Map<String, BufferedImage> images;

    /**
     * Costruttore privato della classe.
     * Impedisce la creazione di istanze multiple tramite la parola chiave 'new'.
     * Inizializza la mappa associativa vuota in attesa che il motore carichi la scena.
     */
    private ImageManager() {
        this.images = new HashMap<>();
    }

    /**
     * Fornisce il punto di accesso globale all'istanza dell'ImageManager.
     * Se l'istanza non esiste, la alloca in memoria (Lazy Initialization).
     *
     * @return L'unica istanza attiva del gestore delle immagini.
     */
    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    /**
     * Carica un'immagine dal disco e la archivia nella cache interna.
     * Se il percorso specificato è già presente nella mappa, la funzione si interrompe
     * prevenendo letture fisiche inutili del file system.
     *
     * @param path Il percorso assoluto o relativo del file immagine sul disco.
     */
    public void load(String path) {
        // Verifica se la risorsa è già stata caricata usando il percorso come chiave
        if (this.images.containsKey(path)) {
            return;
        }

        try {
            /** Oggetto immagine temporaneo allocato tramite la libreria ImageIO. */
            BufferedImage img = ImageIO.read(new File(path));

            // Archiviazione nella cache interna.
            this.images.put(path, img);

        } catch (IOException e) {
            System.err.println("[ImageManager] Errore critico nel caricamento del file: " + path);
            e.printStackTrace();
        }
    }

    /**
     * Recupera un'immagine completa dalla memoria cache tramite il suo percorso.
     *
     * @param path Il percorso testuale dell'immagine richiesta.
     * @return L'istanza di BufferedImage se presente, oppure null se non trovata.
     */
    public BufferedImage getImage(String path) {
        return this.images.get(path);
    }

    /**
     * Estrae un ritaglio specifico (sub-image) da un'immagine sorgente (Sprite Sheet).
     * Ottimo per ottenere i singoli frame di un'animazione da una singola grande immagine.
     *
     * @param path   Il percorso dell'immagine sorgente archiviata in cache.
     * @param x      La coordinata orizzontale di partenza per il ritaglio.
     * @param y      La coordinata verticale di partenza per il ritaglio.
     * @param width  La larghezza in pixel dell'area da ritagliare.
     * @param height L'altezza in pixel dell'area da ritagliare.
     * @return Una nuova BufferedImage contenente solo la porzione ritagliata, o null in caso di errore.
     */
    public BufferedImage getSubImage(String path, int x, int y, int width, int height) {
        /** Puntatore all'immagine originale (Sprite Sheet) recuperata dalla cache. */
        BufferedImage sheet = this.images.get(path);

        if (sheet != null) {
            // Genera e restituisce il ritaglio basato sulle coordinate fornite.
            return sheet.getSubimage(x, y, width, height);
        }
        return null;
    }

    /**
     * Libera completamente la memoria RAM svuotando la cache delle immagini.
     * Deve essere invocato dallo SceneLoader prima di caricare
     * una nuova stanza per prevenire crash dovuti alla memoria esaurita.
     */
    public void clear() {
        this.images.clear();
    }
}