package dayFour

class BingoCard (val lstOfNumbers : MutableList<Int>, val uniqueId : Int) {

    val replaceNum : Int? = null
    var winningNumber : Int = 0

    val matrix = arrayOf(
        arrayOf<Int?>(lstOfNumbers[0],lstOfNumbers[1],lstOfNumbers[2],lstOfNumbers[3],lstOfNumbers[4]),
        arrayOf<Int?>(lstOfNumbers[5],lstOfNumbers[6],lstOfNumbers[7],lstOfNumbers[8],lstOfNumbers[9]),
        arrayOf<Int?>(lstOfNumbers[10],lstOfNumbers[11],lstOfNumbers[12],lstOfNumbers[13],lstOfNumbers[14]),
        arrayOf<Int?>(lstOfNumbers[15],lstOfNumbers[16],lstOfNumbers[17],lstOfNumbers[18],lstOfNumbers[19]),
        arrayOf<Int?>(lstOfNumbers[20],lstOfNumbers[21],lstOfNumbers[22],lstOfNumbers[23],lstOfNumbers[24])
    )

    fun replace(intToReplace : Int){
        var innerPos = 0
        matrix.forEach { innerMatrix ->
            var valuePos = 0
            innerMatrix.forEach { value ->
                if (value==intToReplace) matrix[innerPos][valuePos] = replaceNum
                valuePos+=1
            }
            innerPos += 1
        }
    }

    fun isWinner() : Boolean{
        matrix.forEach { innerMatrix ->
            if(sumUpRow(innerMatrix) == 0) return true
        }
        var cnt = 0;
        while(cnt<5){
            if(sumUpRow(arrayOf(matrix[0][0+cnt],matrix[1][0+cnt],matrix[2][0+cnt],matrix[3][0+cnt],matrix[4][0+cnt])) == 0)
                return true
            cnt+=1
        }
        return false
    }

    fun calculateResult() : Int{
        var result = 0
        matrix.forEach { innerMatrix ->
            innerMatrix.forEach { value ->
                value?.let{
                    result += it
                }
            }
        }
        return result
    }

    fun sumUpRow(array: Array<Int?>) : Int {
        var result = 0
        array.forEach { num ->
            num?.let {
                result += it
            }
        }
        return result
    }
}