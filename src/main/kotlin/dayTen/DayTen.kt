package dayTen

import generalUtils.FileHelper

class DayTen(inputName: String) {

    private val bracketOneOpen = '['
    private val bracketSecondOpen = '{'
    private val bracketThirdOpen = '<'
    private val bracketFourOpen = '('
    private val bracketOneClose = ']'
    private val bracketSecondClose = '}'
    private val bracketThirdClose = '>'
    private val bracketFourClose = ')'

    private val lstLinesOfInput = mutableListOf<String>()
    private val hashMapValuesIncomplete = HashMap<Char, Int>()
    private val hashMapValuesCorrupted = HashMap<Char, Int>()
    private val hashMapBracketPair = HashMap<Char, Char>()
    private var incompleteResult = mutableListOf<Long>()

    var corruptionResult = 0

    init {
        FileHelper().fetchLinesAsString(FileHelper().fetchBufferedStreamFromResource(inputName))
            .forEach {
                lstLinesOfInput.add(it)
            }
        initValueHashMap()
        initBracketPairs()
        initValueHashMapIncomplete()
    }

    fun findCorruptedAndIncompleteLines(): Int {

        lstLinesOfInput.forEach { line ->
            corruptionResult += iterateLine(line)
        }
        return corruptionResult
    }

    fun iterateLine(line: String): Int {
        val lstOfBrackets = mutableListOf<Char>()
        line.forEach { currChar ->
            if (currChar == bracketOneOpen || currChar == bracketSecondOpen || currChar == bracketThirdOpen || currChar == bracketFourOpen) {
                lstOfBrackets.add(currChar)
            } else {
                val brotherChar = hashMapBracketPair[currChar]
                if (brotherChar == lstOfBrackets[lstOfBrackets.lastIndex]) {
                    lstOfBrackets.removeAt(lstOfBrackets.lastIndex)
                } else {
                    hashMapValuesCorrupted.get(brotherChar)?.let {
                        return it
                    }
                }
            }
        }
        if(lstOfBrackets.isNotEmpty()){
            var score : Long = 0
            for (i in lstOfBrackets.lastIndex downTo  0){
                score *= 5
                hashMapValuesIncomplete[lstOfBrackets[i]]?.let{ value ->
                    score+=value
                }
            }
            incompleteResult.add(score)
        }
        return 0
    }

    fun fetchIncompleteResult() : Long{
        return incompleteResult.sorted()[((incompleteResult.size + 1) / 2)-1]
    }

    fun initValueHashMap() {
        hashMapValuesCorrupted.put(bracketSecondOpen, 1197)
        hashMapValuesCorrupted.put(bracketFourOpen, 3)
        hashMapValuesCorrupted.put(bracketThirdOpen, 25137)
        hashMapValuesCorrupted.put(bracketOneOpen, 57)
    }
    fun initValueHashMapIncomplete() {
        hashMapValuesIncomplete.put(bracketSecondOpen, 3)
        hashMapValuesIncomplete.put(bracketFourOpen, 1)
        hashMapValuesIncomplete.put(bracketThirdOpen, 4)
        hashMapValuesIncomplete.put(bracketOneOpen, 2)
    }

    fun initBracketPairs() {
        hashMapBracketPair.put(bracketSecondClose,bracketSecondOpen)
        hashMapBracketPair.put(bracketFourClose,bracketFourOpen)
        hashMapBracketPair.put(bracketThirdClose,bracketThirdOpen )
        hashMapBracketPair.put(bracketOneClose,bracketOneOpen)
    }
}