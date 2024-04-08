package org.camposmdev.model.card.attribute;

import com.almasb.fxgl.logging.Logger;

/**
 * When damage is dealt, value for effect to be applied
 * @param min Minimum value
 * @param max Maximum value
 */
public record DamageDealthRollForEffect(
        Byte min, Byte max
) {
    /* TODO - Implement Effect model */
    public void apply(byte roll) {
        /* check if condition is met */
        if (roll >= min && roll <= max) {
            /* apply effect */
            Logger.get(DamageDealthRollForEffect.class).fatal("NOT YET IMPLEMENTED");
        }
    }
}
