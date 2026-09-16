package model.component.visual.media.pose.type;

import model.component.Component;
import model.component.visual.media.pose.EntityPose;

/**
 * Enumeratore specifico che definisce le pose esclusive per l'entità di tipo Robot.
 * Implementa EntityPose per poter essere assegnato al contenitore base Entity.
 */
public enum RobotPose implements EntityPose, Component {
    /**
     * Posa statica del robot quando non riceve input.
     */
    IDLE,
    /**
     * Posa di deambulazione durante il movimento orizzontale.
     */
    WALKING,
    /**
     * Posa di attacco o interazione.
     */
    ATTACKING
}