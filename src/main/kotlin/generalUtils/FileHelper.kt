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

    fun fetchLinesAsIntList(fileStream: BufferedReader) : MutableList<MutableList<Int>>{
        val fileList = mutableListOf<MutableList<Int>>()
        while(true){
            val nxtLine = fileStream.readLine() ?: break
            val childFileList = mutableListOf<Int>()
            nxtLine.forEach { currChar ->
                childFileList.add(currChar.digitToInt())
            }
            fileList.add(childFileList)
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

    fun fetchBufferedStreamFromResource(relativePosition : String) : BufferedReader{
        return FileHelper::class.java.getResourceAsStream(relativePosition).bufferedReader()
    }
}