import dayFive.DayFive
import dayFour.DayFour
import dayOne.DayOne
import daySeven.DaySeven
import daySix.DaySix
import dayTen.DayTen
import dayThree.DayThree
import dayTwo.DayTwo

class DayExecutor {
    init {

        val dayOne = DayOne("/input/input.csv")
        dayOne.init()

        val dayTwo = DayTwo("/input/day2.csv")
        dayTwo.init()

        val dayThree = DayThree("/input/day3.csv")
        dayThree.init()

        val dayFour = DayFour("/input/day4.csv")
        dayFour.init()

        println("===Day5===")
        var dayFive = DayFive("/input/day5.txt", true)
        dayFive.init()
        dayFive = DayFive("/input/day5.txt", false)
        dayFive.init()

        println("===Day6===")
        var daySix = DaySix("/input/day6.txt")
        println("Oh no, there are ${daySix.forgetObjects(80)} fishies all around us...")
        daySix = DaySix("/input/day6.txt")
        println("Oh no, there are ${daySix.forgetObjects(256)} fishies all around us...")

        println("===Day7===")
        val daySeven = DaySeven("/input/day7.txt")
        println("The ideal fuel consumption is ${daySeven.calculateCheapestPositionByMedian()} ... ")
        println("... or rather ${daySeven.calculateCheapestPositionByMean()} what do I know ... ")

        println("===Day8===")


        println("===Day10===")
        val dayTen = DayTen("/input/day10.txt")
        dayTen.findCorruptedAndIncompleteLines()
        println("That was easy to develop and I did not overthink at all at first - solution part I ist ${dayTen.corruptionResult}")
        println("Part II says ${dayTen.fetchIncompleteResult()}")
    }
}