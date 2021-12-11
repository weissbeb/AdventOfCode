package dayOne

import generalUtils.FileHelper
import java.io.BufferedReader
import java.io.File

class DayOne(val reader: String) {
    val rollingMeasures = mutableListOf<RollingMeasure>()

    fun init(){
        dayOnePartOne(FileHelper().fetchBufferedStreamFromResource(reader))
        dayOnePartTwo(FileHelper().fetchBufferedStreamFromResource(reader))
    }

    private fun dayOnePartOne(fileStream : BufferedReader){
        var counter = 0
        var previousLine = Integer.parseInt(fileStream.readLine().toString())
        val fileList = FileHelper().fetchLinesAsInt(fileStream)
        var cnt = 0
        while(cnt < fileList.size){
            if (fileList[cnt] > previousLine) {
                counter += 1
            }
            previousLine = fileList[cnt]
            cnt+=1
        }
        println("Day 1 Part I: $counter")
    }


    private fun dayOnePartTwo(fileStream: BufferedReader){
        val fileList = FileHelper().fetchLinesAsInt(fileStream)

        var cnt = 0
        while(cnt < fileList.size - 2){
            rollingMeasures.add(RollingMeasure(fileList[cnt], fileList[cnt+1], fileList[cnt+2]))
            cnt+=1
        }

        var resultCnt = 0
        var prevMeasure : RollingMeasure? = null
        rollingMeasures.forEach{ currMeasure ->
            if(prevMeasure != null && currMeasure.compareTo(prevMeasure)) {
                resultCnt += 1
            }
            prevMeasure = currMeasure
        }
        println("Day 1 Part II: $resultCnt")
    }

}