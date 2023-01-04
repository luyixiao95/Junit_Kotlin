
class Answer(id: Int, author: User, answer: String) : QuestionOrAnswer(id, author, answer) {
    init {
        if (!isVaild()) {
            throw QuestionException("The answer should be an exception")
        }
    }

    private fun isVaild(): Boolean {
        return !(body.isNullOrBlank())
    }
    override fun vote(direction: VoteEnum) {
        when (direction) {
            VoteEnum.Up -> votes ++
            VoteEnum.Down -> votes --
        }
        author.questionOrAnswerVotedOn(direction)
    }

    var approved: Boolean  = false

}