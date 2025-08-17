from Domain.Question import Question


class QuestionRepo:

    def __init__(self):

        self._data = []

    def add_question(self, question: Question):

        if question.id in self._data:
            raise ValueError("Question with same id already in repo!")

        self._data.append(question)

    def get_questions_of_difficulty(self, difficulty: str):

        questions = []

        for question in self._data:
            if question.difficulty == difficulty:
                questions.append(question)

        return questions

