import net.ilkinulas.aoc2018.day1Part1
import net.ilkinulas.aoc2018.day1Part2
import net.ilkinulas.aoc2018.day2Part1
import net.ilkinulas.aoc2018.day2Part2
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
}