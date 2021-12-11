import daySeven.DaySeven
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class DaySeven {

    @Test
    fun testDaySeven() {
        val daySeven = DaySeven("/input/day7.txt")
        assertEquals(37, daySeven.calculateCheapestPositionByMedian())
        assertEquals(168, daySeven.calculateCheapestPositionByMean())
    }

}