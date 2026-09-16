package model.component.data.render;

/**
 * Enumerazione che definisce l'ordine gerarchico di rendering (Z-Index).
 * Gestisce i livelli di profondità per determinare quali elementi grafici
 * vengono sovrapposti agli altri durante la fase di disegno sul GamePanel.
 */
public enum RenderLayer {

    /**
     * Livello 0: Sfondi lontani (es. cielo, montagne, background parallax).
     */
    BACKGROUND,

    /**
     * Livello 1: Elementi statici di base della stanza (es. muri, pavimenti, tappeti).
     */
    WORLD_BACK,

    /**
     * Livello 2: Attori principali e dinamici della scena (es. Giocatore, Robot, Ostacoli).
     */
    WORLD_ENTITY,

    /**
     * Livello 3: Oggetti del mondo che devono coprire gli attori (es. tetti, chiome degli alberi).
     */
    WORLD_FRONT,

    /**
     * Livello 4: Elementi di base dell'interfaccia utente (es. HUD, barre della vita).
     */
    UI_BASE,

    /**
     * Livello 5: Primo livello di sovrapposizione dell'interfaccia (es. menu di pausa, popup).
     */
    UI_OVERLAY0,

    /**
     * Livello 6: Livello di sovrapposizione assoluta (es. icona di caricamento, transizioni schermo).
     */
    UI_OVERLAY1
}