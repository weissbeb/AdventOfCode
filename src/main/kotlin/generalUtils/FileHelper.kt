package generalUtils

import java.io.BufferedReader

class FileHelper {

    fun fetchLinesAsString(fileStream : BufferedReader) : MutableList<String>{
        val fileList = mutableListOf<String>()
        while (true) {
            val nxtLine = fileStream.readLine() ?: break
            fileList.add(nxtLine)
        }
        return fileList
    }


    fun fetchLinesAsInt(fileStream : BufferedReader) : MutableList<Int>{
        val fileList = mutableListOf<Int>()
        while (true) {
            val nxtLine = fileStream.readLine() ?: break
            fileList.add(Integer.parseInt(nxtLine))
        }
        return fileList
    }
}