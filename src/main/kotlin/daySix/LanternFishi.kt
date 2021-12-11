package daySix

class LanternFishi (initalBirthtimer : Int) {
    val maxBirthDuration = 8
    val initalAge = 6

    var birthTimer = initalBirthtimer
    var hasReproduced = false

    fun reduceAgeAndTryToMakeBabies() : LanternFishi?{
        return if(birthTimer > 0) {
            birthTimer -= 1
            null
        }else{
            birthTimer = maxBirthDuration
            hasReproduced = true
            LanternFishi(initalAge)
        }
    }

    override fun toString(): String {
        return "$birthTimer - has made babies: $hasReproduced"
    }
}