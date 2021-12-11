package dayOne

class RollingMeasure (val1 : Int, val2: Int, val3 : Int) {

    private var values = mutableListOf(val1, val2, val3)

    fun put(int: Int) {
        values.add(int)
    }

    fun isFull(): Boolean {
        return values.size >= 3
    }

    fun sumOf(): Int {
        var result = 0;
        values.forEach {
            result += it
        }
        return result
    }

    fun compareTo(otherRMeasure: RollingMeasure?): Boolean {
        if (otherRMeasure == null) return false

        return this.sumOf() > otherRMeasure.sumOf()
    }

    override fun toString(): String {
        return String.format("Rolling Measure: %d, %d, %d - Sum: %d ",values.get(0),values.get(1),values.get(2),this.sumOf())
    }
}