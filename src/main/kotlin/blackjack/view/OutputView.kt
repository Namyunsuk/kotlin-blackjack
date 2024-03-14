package blackjack.view

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.participant.Role
import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.GameResultType
import blackjack.model.result.PlayersResult

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        val playersNameMessage = players.playerGroup.map { it.name }.joinToString(PLAYERS_NAME_SEPARATOR)
        lineBreak()
        println(DIVIDE_CARD_FINISH_MESSAGE.format(playersNameMessage))
        println("딜러: ${dealer.getDealerInitCardsMessage()}")

        players.playerGroup.forEach {
            println(it.getCardsMessage(it.name.toString()))
        }
        lineBreak()
    }

    fun printDealerAdditionalCardMessage() {
        lineBreak()
        println(DEALER_ADDITIONAL_CARD_MESSAGE)
    }

    fun printPlayerCardsMessage(player: Player) {
        println(player.getCardsMessage(player.name.toString()))
    }

    fun printPlayersCardResult(
        dealer: Dealer,
        players: Players,
    ) {
        lineBreak()
        println(dealer.getCardsMessage(DEALER_NAME_MESSAGE) + dealer.getPlayerCardResult())
        players.playerGroup.forEach {
            println(it.getCardsMessage(it.name.toString()) + it.getPlayerCardResult())
        }
        lineBreak()
    }

    fun printFinalGameResult(
        players: Players,
        gameResultStorage: GameResultStorage,
    ) {
        println(FINAL_GAME_RESULT_MESSAGE)
        print("딜러: ")
        printDealerFinalGameResult(gameResultStorage.dealerResult)
        printPlayersFinalGameResult(players, gameResultStorage.playersResult)
        lineBreak()
    }

    fun printFinalProfitResult(
        dealer: Dealer,
        players: Players,
    ) {
        println("## 최종 수익")
        println(dealer.getDealerProfitMessage())
        players.playerGroup.forEach {
            it.getPlayerProfitMessage(it.name.toString())
        }
    }

    private fun printDealerFinalGameResult(dealerResult: DealerResult) {
        GameResultType.entries.forEach { gameResult ->
            val count = dealerResult.results[gameResult] ?: return@forEach
            print("${count}${gameResult.message()} ")
        }
        lineBreak()
    }

    private fun printPlayersFinalGameResult(
        players: Players,
        playersResult: PlayersResult,
    ) {
        players.playerGroup.forEach {
            println("${it.name}: ${playersResult.results[it.name]?.message()}")
        }
    }

    private fun Role.getDealerInitCardsMessage(): String {
        return getCards()[0].toCardMessage()
    }

    private fun Role.getCardsMessage(name: String): String {
        return "${name}카드: ${getCards().joinToString(separator = CARDS_SEPARATOR, transform = { it.toCardMessage() })}"
    }

    private fun Card.toCardMessage() = "${denomination.value()}${suite.value()}"

    private fun Role.getPlayerCardResult() = " - 결과: ${getCardSum()}"

    private fun Role.getDealerProfitMessage() = "딜러: $profit"

    private fun Role.getPlayerProfitMessage(name: String) = "$name: $profit"

    private fun lineBreak() = println()

    private fun Suite.value(): String =
        when (this) {
            Suite.CLOVER -> "클로버"
            Suite.HEART -> "하트"
            Suite.SPADE -> "스페이드"
            Suite.DIAMOND -> "다이아몬드`"
        }

    private fun Denomination.value(): String =
        when (this) {
            Denomination.ACE -> "A"
            Denomination.TWO -> "2"
            Denomination.THREE -> "3"
            Denomination.FOUR -> "4"
            Denomination.FIVE -> "5"
            Denomination.SIX -> "6"
            Denomination.SEVEN -> "7"
            Denomination.EIGHT -> "8"
            Denomination.NINE -> "9"
            Denomination.TEN -> "10"
            Denomination.KING -> "K"
            Denomination.QUEEN -> "Q"
            Denomination.JACK -> "J"
        }

    private fun GameResultType.message(): String =
        when (this) {
            GameResultType.WIN -> "승"
            GameResultType.LOSE -> "패"
            GameResultType.DRAW -> "무"
        }

    companion object {
        private const val PLAYERS_NAME_SEPARATOR = ", "
        private const val CARDS_SEPARATOR = ", "
        private const val DIVIDE_CARD_FINISH_MESSAGE = "딜러와 %s에게 2장의 나누었습니다."
        private const val DEALER_ADDITIONAL_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val FINAL_GAME_RESULT_MESSAGE = "## 최종 승패"
        private const val DEALER_NAME_MESSAGE = "딜러 "
    }
}
