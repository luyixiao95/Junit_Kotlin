import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class QuestionTests {
    val user = User(1, "Alice")

    @Nested
    inner class Constructor{
        @Test
        fun `Show throw an exception if the title is empty `() {
            Assertions.assertThrows(QuestionException::class.java)
            {
                Question(1, user, "", "question")
            }

        }

        @Test
        fun `Show throw an exception if the body is empty `() {
            Assertions.assertThrows(QuestionException::class.java)
            {
                Question(1, user, "dfadfa", "")
            }

        }

        @Test
        fun `Show not throw an exception if the question is valid `() {
            Assertions.assertDoesNotThrow { Question(1, user, "dfadfa", "fdfdfd") }

        }

        @ParameterizedTest
        @CsvSource("'',Hi", "title, ''")
        fun `Throw an exception if the question or title is none`(title: String, body: String) {
            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, title, body)
            }
        }
    }

    @Nested
    @KotlinParameterizedTests
    inner class `constructor should have the source` {
        @Suppress("unused")
        fun titlesAndQuestions() = listOf(
            Arguments.of("", "title"),
            Arguments.of(" ", "title"),
            Arguments.of("Author", ""),
            Arguments.of("Aughter", " ")
        )

        @ParameterizedTest
        @MethodSource("titlesAndQuestions")
        fun `Throw an exception if the question or title is invalid` (title: String, question: String ) {
            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, title, question)
            }        }
    }
}