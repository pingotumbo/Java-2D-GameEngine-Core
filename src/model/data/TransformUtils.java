package model.data;

/**
 * Classe di utilità per il calcolo delle trasformazioni spaziali.
 * Fornisce metodi statici puri e riutilizzabili per il ridimensionamento
 * e il posizionamento delle entità all'interno del mondo di gioco.
 */
public class TransformUtils {

    /**
     * Costruttore privato per impedire l'istanziazione.
     * Questa è una libreria di funzioni matematiche statiche.
     */
    private TransformUtils() {
        // Impedisce la creazione di oggetti TransformUtils
    }

    /**
     * Calcola il moltiplicatore di scala necessario affinché un'immagine copra
     * interamente un'area target (effetto "Cover"), mantenendo le sue proporzioni originali.
     * L'immagine riempirà completamente lo spazio, venendo eventualmente ritagliata (clipping)
     * se il suo rapporto d'aspetto differisce da quello dell'area di destinazione.
     *
     * @param sourceWidth  La larghezza nativa in pixel dell'immagine sorgente.
     * @param sourceHeight L'altezza nativa in pixel dell'immagine sorgente.
     * @param targetWidth  La larghezza in pixel dell'area da riempire (es. 800).
     * @param targetHeight L'altezza in pixel dell'area da riempire (es. 600).
     * @return Il fattore di scala matematico (es. 1.5, 0.8) da applicare al Transform.
     */
    public static double calculateCoverScale(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
        /** Moltiplicatore per coprire orizzontalmente. */
        double scaleX = (double) targetWidth / sourceWidth;
        /** Moltiplicatore per coprire verticalmente. */
        double scaleY = (double) targetHeight / sourceHeight;

        // Sceglie la scala maggiore per non lasciare bordi neri
        return Math.max(scaleX, scaleY);
    }
}