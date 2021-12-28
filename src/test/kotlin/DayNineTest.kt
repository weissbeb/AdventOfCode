import dayNine.DayNine
import org.junit.Test
import kotlin.test.assertEquals

class DayNineTest {

    @Test
    fun testDayNine(){
        val day9 = DayNine("/input/day9t.txt")

        assertEquals(15,day9.calcPartOneAndInit())
        assertEquals(1134, day9.calcPartTwo())
    }

}