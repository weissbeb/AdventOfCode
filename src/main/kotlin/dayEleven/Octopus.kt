package dayEleven

class Octopus(private val row : Int,private val column : Int,private var energy : Int) {

    private var flashedThisTurn = false

    fun increaseEnergy(){
        energy +=1
    }

    fun timeToFlash() : Boolean = (energy > 9)

    fun flashAndTriggerNeighbors(allOctopusses : List<List<Octopus>>){
        if(flashedThisTurn) return
        flashedThisTurn = true

        if(row>0){
            triggerNeighbor(allOctopusses[row-1][column], allOctopusses)
            if(column>0){
                triggerNeighbor(allOctopusses[row-1][column-1], allOctopusses)
            }
            if(column <= allOctopusses[0].size - 2){
                triggerNeighbor(allOctopusses[row-1][column+1], allOctopusses)
            }
        }
        if(row <= allOctopusses.size - 2){
            triggerNeighbor(allOctopusses[row+1][column], allOctopusses)
            if(column>0){
                triggerNeighbor(allOctopusses[row+1][column-1], allOctopusses)
            }
            if(column <= allOctopusses[0].size - 2){
                triggerNeighbor(allOctopusses[row+1][column+1], allOctopusses)
            }
        }
        if(column>0){
            triggerNeighbor(allOctopusses[row][column-1], allOctopusses)
        }
        if(column <= allOctopusses[0].size - 2){
            triggerNeighbor(allOctopusses[row][column+1], allOctopusses)
        }
    }

    fun cleanup() : Boolean{
        val result = flashedThisTurn
        flashedThisTurn = false
        if(energy>9) energy = 0
        return result
    }

    private fun triggerNeighbor(
        it: Octopus,
        allOctopusses: List<List<Octopus>>
    ) {
        it.increaseEnergy()
        if (it.timeToFlash()) it.flashAndTriggerNeighbors(allOctopusses)
    }

    override fun toString(): String {
        return "row: $row - col: $column - energy: $energy - hasflashed: $flashedThisTurn"
    }

}