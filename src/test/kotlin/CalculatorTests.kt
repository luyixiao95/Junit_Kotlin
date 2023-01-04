import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorTests {

    @Test
    fun `1 + 1 == 2`() {
        val calculator = Calculator()
        assertEquals(2, calculator.add(1, 1))
    }
}