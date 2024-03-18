package org.camposmdev.model.card.attribute;

/**
 * When damage is dealt, roll for effect to be applied
 * @param min Minimum roll
 * @param max Maximum roll
 */
public record DamageDealthRollForEffect(
        Byte min, Byte max
) {
    /* TODO - Implement Effect model */
    public void apply(byte roll) {
        /* check if condition is met */
        if (roll >= min && roll <= max) {
            /* apply effect */
        }
    }
}
