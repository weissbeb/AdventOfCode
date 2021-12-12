import dayTen.DayTen
import org.junit.Test
import kotlin.test.assertEquals

class DayTenTest {

    @Test
    fun testDayTen() {
        val dayTen = DayTen("/input/day10t.txt")
        dayTen.findCorruptedAndIncompleteLines()
        assertEquals(26397,dayTen.corruptionResult)
        assertEquals(288957,dayTen.fetchIncompleteResult())

    }
}