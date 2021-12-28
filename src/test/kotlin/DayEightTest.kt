import dayEight.DayEight
import org.junit.Test
import kotlin.test.assertEquals

class DayEightTest {

    @Test
    fun dayEightTest(){

        val dayEight = DayEight("/input/day8t.txt")
        assertEquals(26,dayEight.fetchUniqueNumbers())
        assertEquals(61229, dayEight.fetchPartTwoResult())
    }


}