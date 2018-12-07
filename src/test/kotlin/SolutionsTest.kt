import net.ilkinulas.aoc2018.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class SolutionsTest {
    @Test
    fun test_day1() {
        assertEquals(486, day1Part1())
        assertEquals(69285, day1Part2())
    }

    @Test
    fun test_day2() {
        assertEquals(4712, day2Part1())
        assertEquals("lufjygedpvfbhftxiwnaorzmq", day2Part2())
    }

    @Test
    fun test_day3() {
        assertEquals(110389, day3Part1())
        assertEquals(552, day3Part2())
    }

    @Test
    fun test_day4() {
        assertEquals(94040, day4Part1())
        assertEquals(39940, day4Part2())
    }

    @Test
    fun test_day5() {
        assertEquals(11546, day5Part1())
        assertEquals(5124, day5Part2())
    }

    @Test
    fun test_day6() {
        assertEquals(3293, day6Part1())
        assertEquals(45176, day6Part2())
    }


    @Test
    fun test_day7() {
        assertEquals("FHMEQGIRSXNWZBCLOTUADJPKVY", day7Part1())
        assertEquals(0, day7Part2())
    }
}