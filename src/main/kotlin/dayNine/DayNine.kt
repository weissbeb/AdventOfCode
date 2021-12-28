package dayNine

import generalUtils.FileHelper

class DayNine(inputName: String) {

    private var numMatrix = FileHelper().fetchLinesAsIntList(FileHelper().fetchBufferedStreamFromResource(inputName))
    private var lowestPoinVals = mutableListOf<Int>()
    private val lowestPoints = mutableListOf<Point>()
    private var intRowNum = numMatrix.size
    private var intColNum = numMatrix[0].size
    private var pointMatrix = Array(intRowNum){Array<Point?>(intColNum){null} }

    fun calcPartOneAndInit() : Int{
        var intCol: Int
        var intRow = 0
        numMatrix.forEach { childList ->
            intCol = 0
            childList.forEach {
                if(currValIsLowestLevel(intRow, intCol, it)) {
                    lowestPoinVals.add(it)
                    lowestPoints.add(Point(intRow,intCol, it))
                }
                pointMatrix[intRow][intCol] = Point(intRow,intCol, it)
                intCol += 1
            }
            intRow += 1
        }

        var result = 0
        lowestPoinVals.forEach {
            result += it + 1
        }
        return result
    }

    private fun currValIsLowestLevel(intRow : Int, intCol : Int, currVal : Int) : Boolean{
        if(intCol <= (intColNum-2) && (numMatrix[intRow][intCol+1] <= currVal)) return false
        if(intRow <= (intRowNum-2) && (numMatrix[intRow+1][intCol] <= currVal)) return false
        if(intCol != 0 && (numMatrix[intRow][intCol-1] <= currVal)) return false
        if(intRow != 0 && (numMatrix[intRow-1][intCol] <= currVal)) return false
        return true
    }


    private var currDepthNum : Int = 0
    private var resultLst = mutableListOf<Int>()

    fun calcPartTwo() : Int {
        cleanUpMatrix()
        lowestPoints.forEach { depthPoint ->
            currDepthNum = 0
            fetchNeighbors(depthPoint)
            resultLst.add(currDepthNum)
        }
        val sortedResults = resultLst.sorted().reversed()
        return sortedResults[0] * sortedResults[1] * sortedResults[2]
    }

    fun fetchNeighbors(currPoint : Point){
        currDepthNum += 1
        pointMatrix[currPoint.row][currPoint.col] = null

        if(currPoint.row<=intRowNum-2)
            pointMatrix[currPoint.row + 1 ][currPoint.col]?.let{
                fetchNeighbors(it)
            }
        if(currPoint.row != 0)
            pointMatrix[currPoint.row - 1 ][currPoint.col]?.let{
                fetchNeighbors(it)
            }
        if(currPoint.col<=intColNum-2)
        pointMatrix[currPoint.row][currPoint.col + 1]?.let{
            fetchNeighbors(it)
        }
        if(currPoint.col != 0)
            pointMatrix[currPoint.row][currPoint.col - 1]?.let{
                fetchNeighbors(it)
            }
    }

    private fun cleanUpMatrix(){
        pointMatrix.forEach { childLst ->
            var intCol = 0
            childLst.forEach {
                if(it!= null && it.isMaxHeight) childLst[intCol]=null
                intCol +=1
            }
        }
    }


}


class Point(val row: Int, val col: Int, val value : Int){
    val isMaxHeight : Boolean = value==9
    override fun toString(): String {
        return "row: $row - col: $col - val: $value"
    }
}