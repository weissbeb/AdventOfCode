package dayFour

import generalUtils.FileHelper
import java.io.BufferedReader
import java.io.File

class DayFour (val file: File) {
    private val bingoCards = mutableListOf<BingoCard>()
    private var bingoLines = mutableListOf<String>()
    private var playingNumbers = arrayOf<String>()

    fun init() {
        file.bufferedReader().use { stream ->
            bingoLines = FileHelper().fetchLinesAsString(stream)
        }

        prepareValues()
        val winnerCard = dayFourPartOne()

        println("\n\n===DAY4===")
        winnerCard?.let{
            println("The winner is... card ${winnerCard.uniqueId} with its result being...\n" +
                    "${winnerCard.calculateResult()} times ${winnerCard.winningNumber} being ${winnerCard.calculateResult() * winnerCard.winningNumber}" )
        }
        val loserCard = dayFourPartTwo()
        loserCard?.let{
            println("\nThe loser is... card ${loserCard.uniqueId} with its result being...\n" +
                    "${loserCard.calculateResult()} times ${loserCard.winningNumber} being ${loserCard.calculateResult() * loserCard.winningNumber}" )
        }
    }

    private fun dayFourPartOne() : BingoCard?{
        fetchBingoCards(bingoLines)
        var currentNumberInt : Int
        playingNumbers.forEach { currenNumber ->
            currentNumberInt = currenNumber.toInt()
            bingoCards.forEach { currentCard ->
                currentCard.replace(currentNumberInt)
                if(currentCard.isWinner()) {
                    currentCard.winningNumber = currentNumberInt
                    return currentCard
                }
            }
        }
        return null
    }

    private fun dayFourPartTwo() : BingoCard?{

        fetchBingoCards(bingoLines)
        var currentNumberInt : Int
        var bingoCardsCopy = bingoCards.toMutableList()
        playingNumbers.forEach { currenNumber ->
            currentNumberInt = currenNumber.toInt()
            bingoCards.forEach { currentCard ->
                currentCard.replace(currentNumberInt)
                if(currentCard.isWinner() && bingoCardsCopy.size>1) {
                    bingoCardsCopy.remove(currentCard)
                }else if(currentCard.isWinner() && bingoCardsCopy.size==1
                    && bingoCardsCopy.contains(currentCard)){
                    currentCard.winningNumber = currentNumberInt
                    return currentCard
                }
            }
        }
        return null
    }

    private fun prepareValues(){

        playingNumbers = bingoLines[0].split(",").toTypedArray()
        bingoLines.removeAt(0)
        bingoLines.removeAt(0)
    }

    private fun fetchBingoCards(
        bingoLines: MutableList<String>
    ) {
        bingoCards.clear()
        var intArray = mutableListOf<Int>()
        var cnt = 0;
        bingoLines.forEach { line ->
            if (line == "") {
                bingoCards.add(BingoCard(intArray,cnt))
                intArray = mutableListOf()
                cnt+=1
            }
            line.split(" ").forEach { splitValue ->
                splitValue.toIntOrNull()?.let {
                    intArray.add(splitValue.toInt())
                }
            }
        }
        bingoCards.add(BingoCard(intArray,cnt))
    }

}