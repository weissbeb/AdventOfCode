package dayEleven

import generalUtils.FileHelper

class DayEleven(inputName: String)  {

    private var allOctopusses = mutableListOf<MutableList<Octopus>>()
    private var flashCounter = 0
    private var flashResult = 0
    private var firstSyncFlashStep = 0

    init {
        var rowCounter = 0
        FileHelper().fetchLinesAsString(FileHelper().fetchBufferedStreamFromResource(inputName))
            .forEach { row ->
                var colCounter = 0
                val octoRow = mutableListOf<Octopus>()
                row.forEach { char ->
                    octoRow.add(Octopus(rowCounter , colCounter, char.digitToInt()))
                    colCounter+=1
                }
                allOctopusses.add(octoRow)
                rowCounter += 1
            }
    }

    fun startSteps(){
        for(i in 1..1000){
            val oldFlashCount = flashCounter
            allOctopusses.forEach { octoRow ->
                octoRow.forEach { octo ->
                    octo.increaseEnergy()
                }
            }
            allOctopusses.forEach { octoRow ->
                octoRow.forEach { octo ->
                    if(octo.timeToFlash())
                        octo.flashAndTriggerNeighbors(allOctopusses)
                }
            }
            allOctopusses.forEach { octoRow ->
                octoRow.forEach { octo ->
                    if(octo.cleanup())flashCounter+=1
                }
            }
            val flashesThisStep = flashCounter - oldFlashCount
            if(firstSyncFlashStep == 0 && (flashesThisStep == (allOctopusses.size * allOctopusses[0].size))){
                firstSyncFlashStep = i
                return
            }
            if(i==100) flashResult = flashCounter
        }
    }

    fun getFlashResult() : Int = flashResult
    fun getFirstSyncFlash() : Int = firstSyncFlashStep


}