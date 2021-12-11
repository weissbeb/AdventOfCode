package dayThree

import generalUtils.FileHelper
import java.io.File
import kotlin.math.pow

class DayThree(val file: File) {
    private var binaryCommon = ""
    private var binaryUncommon = ""
    private var commonBinaryCount = IntArray(0)
    private var lstBinary = mutableListOf<String>()

    fun init() {
        file.bufferedReader().use { stream ->
            lstBinary = FileHelper().fetchLinesAsString(stream)
        }
        dayThree()

    }

    private fun dayThree(){
        //Part 1
        commonBinaryCount = IntArray(lstBinary[0].length){0}
        fillCommonList(lstBinary)
        fetchBinariesFromCommonList()
        println("\n\n===DAY3===")
        println("Gamma rate is ${binaryToDecimal(binaryCommon)}, according to binary $binaryCommon")
        println("Epsilon rate is ${binaryToDecimal(binaryUncommon)}, according to binary $binaryUncommon")
        println("Thus we shall have the power consumption of ${binaryToDecimal(binaryUncommon) * binaryToDecimal(binaryCommon)}")

        //Part 2
        val o2 = checkBinaryRecursively(lstBinary,0,true)
        val co2 = checkBinaryRecursively(lstBinary,0,false)
        println("Oxygen generator rating is ${binaryToDecimal(o2)}, according to binary $o2")
        println("CO2 Scrubber rating is ${binaryToDecimal(co2)}, according to binary $co2")
        println("Thus we shall have the life support of ${binaryToDecimal(o2) * binaryToDecimal(co2)}")
    }

    private fun fillCommonList(lstBinary: MutableList<String>) {
        lstBinary.forEach { currentBinaryString ->
            var indx = 0
            currentBinaryString.forEach { currentBinaryChar ->
                if (currentBinaryChar == '0') {
                    commonBinaryCount[indx] -= 1
                } else {
                    commonBinaryCount[indx] += 1
                }
                indx += 1
            }
        }
    }

    private fun fetchBinariesFromCommonList() {
        commonBinaryCount.forEach { currentNum ->
            if (currentNum > 0) {
                binaryCommon += "1"
                binaryUncommon += "0"
            } else {
                binaryCommon += "0"
                binaryUncommon += "1"
            }
        }
    }

    private fun binaryToDecimal(binary : String) : Int{
        var decimalCommon = 0.0
        var cnt = 0
        val almightyAndHoly2 = 2.0
        binary.reversed().forEach { digit ->
            decimalCommon += digit.toString().toInt() * almightyAndHoly2.pow(cnt)
            cnt+=1
        }
        return decimalCommon.toInt()
    }

    private fun checkBinaryRecursively(filteredBinaryList: MutableList<String>, iteration : Int,  useCommon: Boolean) : String{
        if(filteredBinaryList.size == 1) return filteredBinaryList[0]

        var charCount = 0
        var binary = fetchNewBinary(filteredBinaryList, iteration)
        binary.forEach { currChar ->
            if(currChar == '1') charCount ++
            else charCount --
        }

        val newFilteredBinaryList = mutableListOf<String>()
        val lookUpDigit =
            if((charCount>=0 && useCommon) || (charCount<0 && !useCommon)) '1'
            else '0'

        filteredBinaryList.forEach { currBinary ->
            if(currBinary[iteration] == lookUpDigit)
                newFilteredBinaryList.add(currBinary)
        }
        return checkBinaryRecursively(newFilteredBinaryList, iteration + 1 , useCommon)
    }

    private fun fetchNewBinary (binaryList: MutableList<String>, position : Int) : String{
        var result = ""
        binaryList.forEach { currBinary ->
            result += currBinary[position]
        }
        return result
    }

}