
class Question:

    def __init__(self, id: str, text: str, choice_a: str, choice_b: str, choice_c: str, answer: str, difficulty: str):

        self._id = id
        self._text = text
        self._choice_a = choice_a
        self._choice_b = choice_b
        self._choice_c = choice_c
        self._answer = answer
        self._difficulty = difficulty

    @property
    def id(self):
        return self._id

    @property
    def text(self):
        return self._text

    @property
    def choice_a(self):
        return self._choice_a

    @property
    def choice_b(self):
        return self._choice_b

    @property
    def choice_c(self):
        return self._choice_c

    @property
    def answer(self):
        return self._answer

    @property
    def difficulty(self):
        return self._difficulty

    def __str__(self):

        question_str = self._text + "," + self._choice_a + "," + self._choice_b + "," + self._choice_c + "," \
                       + self._answer + "," + self._difficulty

        return question_str
