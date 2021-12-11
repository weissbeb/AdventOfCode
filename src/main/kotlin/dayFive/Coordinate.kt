package dayFive

class Coordinate (x : Int, y : Int) {
    val x : Int = x
    val y : Int = y
    var corruption = 0

    override fun toString(): String {
        return "$x - $y - Corruption: $corruption"
    }
}