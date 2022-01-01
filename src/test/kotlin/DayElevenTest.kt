import dayEleven.DayEleven
import org.junit.Test
import kotlin.test.assertEquals

class DayElevenTest {

    @Test
    fun dayElevenTest(){

        val dayEleven = DayEleven("/input/day11t.txt")
        dayEleven.startSteps()
        assertEquals(1656, dayEleven.getFlashResult())
        assertEquals(195, dayEleven.getFirstSyncFlash())
    }

}