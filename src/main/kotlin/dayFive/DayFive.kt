package dayFive

import generalUtils.FileHelper
import java.io.File

class DayFive(val file: File, val easyMode: Boolean)  {

    val matrix =  mutableListOf<MutableList<Coordinate>>()
    var lstLinesStr = mutableListOf<String>()
    var lstLines= mutableListOf<Line>()
    var biggestX = 0
    var biggestY = 0

    fun init() {
        file.bufferedReader().use { stream ->
            lstLinesStr = FileHelper().fetchLinesAsString(stream)
        }

        lstLinesStr.forEach {
            interpretLine(it)
        }

        for(i in 0 .. biggestX){
            val innerChild = mutableListOf<Coordinate>()
            for(ii in 0 .. biggestY){
                innerChild.add(Coordinate(i,ii))
            }
            matrix.add(innerChild)
        }

        fetchCorruption()
    }

    fun interpretLine(line : String){
        val lstNums = mutableListOf<Int>()
        line.split("->").forEach{ halfString ->
            halfString.split(",").forEach{ singleNum ->
                lstNums.add(Integer.parseInt(singleNum.replace(" ","")))
            }
        }
        lstLines.add(Line(lstNums[0],lstNums[1],lstNums[2],lstNums[3]))

        biggestX =
            if (lstNums[0] > biggestX) lstNums[0]
            else if (lstNums[2] > biggestX) lstNums[2]
            else biggestX
        biggestY =
            if (lstNums[1] > biggestY) lstNums[1]
            else if (lstNums[3] > biggestY) lstNums[3]
            else biggestY
    }

    fun fetchCorruption(){
        lstLines.forEach { currLine ->
            if(!easyMode || (currLine.direction == currLine.SOUTH || currLine.direction == currLine.EAST))
                currLine.coordinatesICorrupt.forEach { currentCorruption ->
                    matrix.get(currentCorruption.x).get(currentCorruption.y).corruption += 1
                }
        }

        var corrupted = 0
        matrix.forEach { innerChild ->
            innerChild.forEach { coordinate ->
                if (coordinate.corruption > 1)corrupted += 1
            }
        }
        println("Corrupted fields... easymode activated: $easyMode - Corrption: $corrupted")
    }
}