from Domain.Question import Question
from Repository.QuestionRepo import QuestionRepo


class QuestionService:

    def __init__(self, question_repo: QuestionRepo):

        self._question_repo = question_repo

    def add_question(self, id: str, text: str, choice_a: str, choice_b: str, choice_c: str, answer: str,
                     difficulty: str):

        question = Question(id, text, choice_a, choice_b, choice_c, answer, difficulty)

        self._question_repo.add_question(question)

    def create_quiz(self, difficulty: str, nr_questions: int, filename: str):

        if nr_questions // 2 > len(self._question_repo.get_questions_of_difficulty(difficulty)):

            raise ValueError("There are not enough questions of this difficulty to create the quizz")

        fout = open(filename, "wt")

        question_nr = 1

        questions = self._question_repo.get_questions_of_difficulty(difficulty)

        while nr_questions:

            for question in questions:

                to_add_question = str(question_nr) + str(question) + "\n"

                fout.write(to_add_question)

                question_nr += 1

                nr_questions -= 1

                if not nr_questions:
                    break

            break

        if difficulty != "easy":

            questions = self._question_repo.get_questions_of_difficulty("easy")

            while nr_questions:

                for question in questions:

                    to_add_question = str(question_nr) + str(question) + "\n"

                    fout.write(to_add_question)

                    question_nr += 1

                    nr_questions -= 1

                    if not nr_questions:
                        break

                break

        if difficulty != "medium":

            questions = self._question_repo.get_questions_of_difficulty("medium")

            while nr_questions:

                for question in questions:

                    to_add_question = str(question_nr) + str(question) + "\n"

                    fout.write(to_add_question)

                    question_nr += 1

                    nr_questions -= 1

                    if not nr_questions:
                        break

                break

        if difficulty != "hard":

            questions = self._question_repo.get_questions_of_difficulty("hard")

            while nr_questions:

                for question in questions:

                    to_add_question = str(question_nr) + str(question) + "\n"

                    fout.write(to_add_question)

                    question_nr += 1

                    nr_questions -= 1

                    if not nr_questions:
                        break

                break

        fout.close()
