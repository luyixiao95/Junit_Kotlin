class Question(id: Int, user: User, var title: String, question: String): QuestionOrAnswer(id, user, question) {

    init {
        if (!isValid()) {
            throw QuestionException("Question has no body or title")
        }
    }
    private fun isValid(): Boolean {
        if (title.isNullOrBlank() || body.isNullOrBlank()) {
            return false
        }
        return true
    }

    var answered = false
        private set

    var closed = false
        private set

    var closedReason = ""
        private set

    private val _answers = mutableListOf<Answer>()

    val answers: List<Answer>
        get() {
            return _answers.toList()
    }

    private val _tags = mutableListOf<Tag>()

    val tags: List<Tag>
        get() {return _tags.toList()}


    override fun vote(direction: VoteEnum) {
        if (closed) {
            throw QuestionException("HIIIIII")
        }
        when(direction) {
            VoteEnum.Up -> votes++
            VoteEnum.Down -> votes --
        }

        author.questionOrAnswerVotedOn(direction)
    }

    fun close(reason: String) {
        if (closed) {
            throw QuestionException("It is already closed")
        }
        closed = true
        closedReason = reason
    }

    fun addAnswer(answer: Answer) {
        val answerFromCollection = answers.firstOrNull {it.id == answer.id}
        if (answerFromCollection != null) {
            throw QuestionException("This answer has already exists")
        }
        _answers.add(answer)
        answered = true
    }

    fun approveAnswer(answerId: Int) {
        if(answers.any{it.approved}) {
            throw QuestionException("Already have an approved answer")
        }
        val answer = answers.firstOrNull {it.id == answerId} ?: throw QuestionException("Unable to find this answer")
        answer.approved = true
    }

}
