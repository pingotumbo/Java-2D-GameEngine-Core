package model.component.data;

import model.component.Component;

/**
 * Componente dati che fornisce un'identità univoca o un'etichetta semantica a un'entità.
 * Isola il concetto di "nome" dall'entità stessa, permettendo al motore di cercare
 * e filtrare gli attori nella scena basandosi su questo identificatore.
 */
public class IdentityComponent implements Component {

    /**
     * La stringa testuale che identifica l'entità (es. "SCENE_TIMER", "PLAYER_1").
     */
    private final String id;

    /**
     * Costruttore del componente di identità.
     *
     * @param id Il valore testuale da assegnare come identificatore.
     */
    public IdentityComponent(String id) {
        this.id = id;
    }

    /**
     * Restituisce l'identificatore memorizzato in questo componente.
     *
     * @return Il valore della stringa id.
     */
    public String getId() {
        return this.id;
    }
}