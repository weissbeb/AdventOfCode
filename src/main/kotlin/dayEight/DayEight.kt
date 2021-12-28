package dayEight

import generalUtils.FileHelper

class DayEight(inputName: String) {

    private val lstLinesOfInput = mutableListOf<List<String>>()
    private var digitList = arrayOf("","","","","","","","","","")


    init{

        FileHelper().fetchLinesAsString(FileHelper().fetchBufferedStreamFromResource(inputName))
            .forEach {
                lstLinesOfInput.add(it.split(" "))
            }

    }

    fun fetchUniqueNumbers() : Int{
        var resultCounter = 0
        lstLinesOfInput.forEach { currLine ->
            for(i in 1..4){
                val currStringLength = currLine[currLine.size - i].length
                if( currStringLength == 2 || currStringLength == 3 || currStringLength == 4 || currStringLength == 7){
                    resultCounter += 1
                }
            }
        }
        return  resultCounter
    }


    fun fetchPartTwoResult() : Int{
        var result = 0
        lstLinesOfInput.forEach { inputLine ->
            initKnownDigits(inputLine)
            for(i in 0..9){
                val it = inputLine[i]
                if(it.length == 5){
                    if(compareInputs(digitList[1], it) == 2)  digitList[3] = it
                    else if(compareInputs(digitList[4], it) == 2)  digitList[2] = it
                    else digitList[5] = it
                }
                else if(it.length == 6){
                    if(compareInputs(digitList[7], it) != 3)  digitList[6] = it
                    else if(compareInputs(digitList[4], it) == 4)  digitList[9] = it
                    else digitList[0] = it
                }
            }

            result += fetchInterimResult(inputLine)
        }
        return result
    }

    private fun fetchInterimResult(inputLine: List<String>) : Int{
        var interimResult = fetchCorrectNumber(inputLine[inputLine.size - 1])
        interimResult += (fetchCorrectNumber(inputLine[inputLine.size - 2]) * 10)
        interimResult += (fetchCorrectNumber(inputLine[inputLine.size - 3]) * 100)
        interimResult += (fetchCorrectNumber(inputLine[inputLine.size - 4]) * 1000)
        return interimResult
    }

    private fun fetchCorrectNumber(input : String) : Int{
        digitList.forEach { currDigit ->
            if(numbersAreSame(currDigit, input)) return digitList.indexOf(currDigit)
        }
        return -1
    }

    private fun numbersAreSame(currDigit : String, lookupDigit : String) : Boolean{
        if(currDigit.length != lookupDigit.length) return false
        currDigit.forEach { currChar ->
            if(!lookupDigit.contains(currChar)) return false
        }
        return true
    }

    private fun initKnownDigits(knownDigit: List<String>){
        knownDigit.forEach {
            when(it.length){
                2 -> digitList[1] = it
                3 -> digitList[7] = it
                4 -> digitList[4] = it
                7 -> digitList[8] = it
            }
        }
    }

    private fun compareInputs(knownDigit : String, unknownDigit : String) : Int {
        var result = 0
        knownDigit.forEach { knownDigitChar ->
            if (unknownDigit.contains(knownDigitChar))
                result+=1
        }
        return result
    }

}