import daySeven.DaySeven
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class DaySeven {

    @Test
    fun testDaySeven() {
        val daySeven = DaySeven(File("""C:\Users\baumg\Desktop\Neuer Ordner\day7t.txt"""))
        assertEquals(37, daySeven.calculateCheapestPositionByMedian())
        assertEquals(168, daySeven.calculateCheapestPositionByMean())
    }

}