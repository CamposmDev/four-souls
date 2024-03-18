package org.camposmdev.model.card.attribute;

/**
 * When a player takes damage, their given a list of options
 * on what they are given. Options can be the following but are not
 * limited to: n money, m attack buff, x loot;
 */
public record DamageTakenOptions(Byte money, Byte attackMod, Byte loot) {

}
