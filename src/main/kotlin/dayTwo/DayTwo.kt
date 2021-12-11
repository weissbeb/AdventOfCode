package dayTwo

import generalUtils.FileHelper
import java.io.BufferedReader
import java.io.File

class DayTwo(val file : File) {

    fun init(){
        file.bufferedReader().use { stream ->
            dayTwo(stream, false)
        }
        file.bufferedReader().use { stream ->
            dayTwo(stream, true)
        }
    }

    private fun dayTwo(fileStream : BufferedReader, useAim : Boolean){
        val lstCommands = FileHelper().fetchLinesAsString(fileStream)
        val position = Position(useAim)
        lstCommands.forEach { command ->
            val digit = command.filter { it.isDigit() }.toInt()
            if(command.contains("forward")){
                position.changeHorizontal(digit)
            }else if(command.contains("down")){
                position.changeDepth(digit)
            }else if(command.contains("up")){
                position.changeDepth(digit * -1)
            }
        }
        println("Day 2 - Using aim $useAim: ${position.result()}")
    }
}