from Service.QuestionService import QuestionService


class UI:
    def __init__(self, question_service: QuestionService):

        self._question_service = question_service

    def print_menu(self):

        print("Add question to master question (add id;text;choice_a;choice_b;choice_c;answer;difficulty)")
        print("Create quiz(create difficulty number_of_quesitons file)")
        print("Exit the program(exit)")

    def run_ui(self):

        while True:

            self.print_menu()

            command_input = input("Select option: ")

            command = command_input.split(" ")

            if command[0] == "add":

                attributes = command[1]
                self.handle_add_question(attributes)

            elif command[0] == "create":

                self.handle_create_quiz(command)

            elif command[0] == "exit":
                return

            else:
                print("Invalid format for the command(write the command name and then use a space).")

    def handle_add_question(self, attributes):

        try:

            attributes = attributes.split(";")

            self._question_service.add_question(attributes[0], attributes[1], attributes[2], attributes[3],
                                                attributes[4], attributes[5], attributes[6])

        except Exception as ex:
            print("Invalid format for the command!")

    def handle_create_quiz(self, command):

        try:

            self._question_service.create_quiz(command[1], int(command[2]), command[3])

        except Exception as ex:
            print("Ooops: ", ex)