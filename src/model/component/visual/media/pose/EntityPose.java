package model.component.visual.media.pose;

/**
 * Interfaccia marcatore (Marker Interface) che astrae il concetto di "Posa" o
 * stato visivo di un'entità. Consente a diverse entità di definire i propri
 * enumeratori specifici (es. RobotPose, SpinnerPose) mantenendo la compatibilità
 * con il sistema di notifica dell'Entity e dello SpriteAnimator.
 */
public interface EntityPose {
    // Interfaccia vuota utilizzata esclusivamente per garantire la tipizzazione forte
    // polimorfica degli stati visivi (pose) all'interno del motore.
}