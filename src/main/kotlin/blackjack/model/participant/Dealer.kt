package blackjack.model.participant

import blackjack.model.result.GameResultType

class Dealer : Role() {
    override fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    fun decideGameResultType(player: Player): GameResultType {
        return when {
            player.isBurst() -> GameResultType.WIN
            isBurst() -> GameResultType.LOSE
            getCardSum() > player.getCardSum() -> GameResultType.WIN
            getCardSum() == player.getCardSum() -> GameResultType.DRAW
            else -> GameResultType.LOSE
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
