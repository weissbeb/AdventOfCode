package daySix

import generalUtils.FileHelper

class DaySix(reader: String) {

    var lstLanternFishi: MutableList<LanternFishi>

    init {
        val lstFishiStr: String =
            FileHelper().fetchLinesAsString(FileHelper().fetchBufferedStreamFromResource(reader))[0]

        lstLanternFishi = mutableListOf()
        lstFishiStr.split(",").forEach { fishiAge ->
            lstLanternFishi.add(LanternFishi(fishiAge.toInt()))
        }
    }

    fun runAndGo(days: Int): Int {
        for (i in 0 until days) {
            val lstLanternFishiTemp = mutableListOf<LanternFishi>()
            lstLanternFishi.forEach { fishi ->
                val fishi = fishi.reduceAgeAndTryToMakeBabies()
                if (fishi != null)
                    lstLanternFishiTemp.add(fishi)
            }
            lstLanternFishiTemp.forEach { lstLanternFishi.add(it) }
        }
        return lstLanternFishi.size
    }


    fun getEmptyArray(): HashMap<Int, Long> {
        return hashMapOf(
            0 to 0,
            1 to 0,
            2 to 0,
            3 to 0,
            4 to 0,
            5 to 0,
            6 to 0,
            7 to 0,
            8 to 0,
        )
    }

    fun forgetObjects(days: Int): Long {
        var mapFishi = getEmptyArray()
        lstLanternFishi.forEach { fishi ->
            mapFishi.set(fishi.birthTimer, mapFishi.getValue(fishi.birthTimer) + 1)
        }

        for (day in 0 until days) {
            val mapTemp = getEmptyArray()
            for (age in 8 downTo 0) {
                if (age == 0) {
                    mapTemp[8] = mapFishi.getValue(0)
                    mapTemp[6] = mapFishi.getValue(0) + mapFishi.getValue(7)
                } else {
                    mapTemp[age - 1] = mapFishi.getValue(age)
                }
            }
            mapFishi = mapTemp
        }

        var result: Long = 0
        for (i in 0..8) {
            mapFishi[i]?.let {
                result = result.plus(it)
            }
        }
        return result
    }


}