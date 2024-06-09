package io.github.camposmdev.foursouls.core.util

import io.github.camposmdev.foursouls.core.card.attribute.CardType

class CardAssetNotFoundException : Exception {
    constructor(message: String) : super(message)
    constructor(cardType: CardType) : super("No card assets found for type $cardType")
}

