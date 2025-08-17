from Domain.Question import Question


class QuestionValidator:

    def validate(self, question: Question):

        errors = ""

        if question.text[-1] != "?":
            errors += "Invalid format for question text\n"

