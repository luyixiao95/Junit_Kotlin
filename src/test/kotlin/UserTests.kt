import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class  UserTests {
    val user = User(1, "Alice")

    @Test
    fun `should Be Able To Increase Reputation`(){
        user.changeReputation(10)
        Assertions.assertEquals(10, user.reputation)
    }

    @Test
    fun `should Be Able To Decrease Reputation`(){
        user.changeReputation(10)
        user.changeReputation(-5)

        Assertions.assertEquals(5, user.reputation)
    }
}