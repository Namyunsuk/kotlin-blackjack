package blackjack.model.participant

import blackjack.model.card.Card

class HandCards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getAceCount(): Int {
        return _cards.count { it.denomination.isAce() }
    }
}