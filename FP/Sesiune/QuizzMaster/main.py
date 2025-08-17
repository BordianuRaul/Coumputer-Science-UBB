from Repository.QuestionRepo import QuestionRepo
from Service.QuestionService import QuestionService
from UserInterface.UI import UI


def main():

    question_repo = QuestionRepo()

    question_service = QuestionService(question_repo)

    question_service.add_question("1", "Are ana mere?", "da", "nu", "poate", "da", "easy")
    question_service.add_question("2", "Are ana mere?", "da", "nu", "poate", "da", "medium")
    question_service.add_question("3", "Are ana mere?", "da", "nu", "poate", "da", "hard")
    question_service.add_question("4", "Are ana mere?", "da", "nu", "poate", "poate", "easy")
    question_service.add_question("5", "Are ana mere?", "da", "nu", "poate", "nu", "hard")
    question_service.add_question("6", "Are ana mere?", "da", "nu", "poate", "da", "easy")
    question_service.add_question("7", "Are ana mere?", "da", "nu", "poate", "da", "medium")
    question_service.add_question("8", "Are ana mere?", "da", "nu", "poate", "da", "hard")
    question_service.add_question("9", "Are ana mere?", "da", "nu", "poate", "poate", "easy")
    question_service.add_question("10", "Are ana mere?", "da", "nu", "poate", "nu", "hard")


    ui = UI(question_service)

    ui.run_ui()

if __name__ == "__main__":

    main()
