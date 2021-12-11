package daySeven

import generalUtils.FileHelper
import kotlin.math.roundToInt

class DaySeven(reader: String) {

    private val lstHorizontalPositions: MutableList<Int> = mutableListOf()

    init {
        FileHelper().fetchLinesAsString(FileHelper().fetchBufferedStreamFromResource(reader))[0].split(",")
            .forEach {
                lstHorizontalPositions.add(it.toInt())
            }

    }

    fun calculateCheapestPositionByMedian(): Int {
        var median: Int
        lstHorizontalPositions.sorted().let { sortedList ->
            median = if (sortedList.size % 2 == 0)
                (sortedList[sortedList.size / 2] + sortedList[(sortedList.size + 1) / 2]) / 2
            else sortedList[sortedList.size / 2]
        }
        return fetchAllCrabFuelCost(median, false)
    }

    fun calculateCheapestPositionByMean(): Int {
        val mean: Int = (lstHorizontalPositions.sum().toFloat() / lstHorizontalPositions.size.toFloat()).roundToInt()

        val lstLookAround = mutableListOf<Int>()
        for (i in mean - 8..mean + 8) {
            lstLookAround.add(fetchAllCrabFuelCost(i, true))
        }
        return lstLookAround.sorted()[0]
    }


    private fun fetchAllCrabFuelCost(targetedPosition: Int, useCrabEngineering: Boolean): Int {
        var result = 0
        lstHorizontalPositions.forEach { initialCrabPosition ->
            (initialCrabPosition - targetedPosition).let { diff ->
                val diffPos = if (diff < 0) diff * -1 else diff
                result += fetchSingleCrabFuelCost(diffPos, useCrabEngineering)
            }
        }
        return result
    }

    private fun fetchSingleCrabFuelCost(spacesToMove: Int, useCrabEngineering: Boolean): Int {
        var result = 0
        if (!useCrabEngineering) {
            result = if (spacesToMove < 0) spacesToMove * -1
            else spacesToMove
        } else {
            for (i in 0..spacesToMove) {
                result += i
            }
        }
        return result
    }
}