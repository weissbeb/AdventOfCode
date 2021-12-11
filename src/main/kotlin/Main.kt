import dayFive.DayFive
import dayFour.DayFour
import dayOne.DayOne
import dayOne.RollingMeasure
import daySix.DaySix
import dayThree.DayThree
import dayTwo.DayTwo
import java.io.BufferedReader
import java.io.File

fun main(args: Array<String>) {
    val dayOne = DayOne(File("""C:\Users\baumg\Desktop\Neuer Ordner\input.csv"""))
    dayOne.init()

    val dayTwo = DayTwo(File("""C:\Users\baumg\Desktop\Neuer Ordner\day2.csv"""))
    dayTwo.init()

    val dayThree = DayThree(File("""C:\Users\baumg\Desktop\Neuer Ordner\day3.csv"""))
    dayThree.init()

    val dayFour = DayFour(File("""C:\Users\baumg\Desktop\Neuer Ordner\day4.csv"""))
    dayFour.init()

    println("===Day5===")
    var dayFive = DayFive(File("""C:\Users\baumg\Desktop\Neuer Ordner\day5.txt"""), true)
    dayFive.init()
    dayFive = DayFive(File("""C:\Users\baumg\Desktop\Neuer Ordner\day5.txt"""), false)
    dayFive.init()

    println("===Day6===")
    var daySix = DaySix(File("""C:\Users\baumg\Desktop\Neuer Ordner\day6.txt"""))
    println("Oh no, there are ${daySix.forgetObjects(80)} fishies all around us...")
    daySix = DaySix(File("""C:\Users\baumg\Desktop\Neuer Ordner\day6.txt"""))
    println("Oh no, there are ${daySix.forgetObjects(256)} fishies all around us...")
}


