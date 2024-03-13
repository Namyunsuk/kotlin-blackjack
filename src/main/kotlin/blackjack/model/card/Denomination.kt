package blackjack.model.card

enum class Denomination(val score: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10), ;

    fun isAce() = this == ACE

    companion object {
        const val ADDITIONAL_ACE_SCORE = 10
    }
}
