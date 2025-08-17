from Service.SentenceServcie import SentenceService


class UI:

    def __init__(self, sentence_service: SentenceService):

        self.sentence_service = sentence_service

    def console(self):

        print("1.Add sentece.")
        print("2.Start game.")
        print("0.Exit")

    def run_ui(self):


        while True:
            self.console()

            option = input("Select option: ")

            if option == "1":
                self.handle_add()

            elif option == "2":
                self.handle_start_game()

            elif option == "0":
                return
            else:
                print("Invalid option!")

    def handle_add(self):

        try:

            sentence = input("Enter sentence: ")
            self.sentence_service.add_sentence(sentence)

        except Exception as ex:
            print("Ooops: ", ex)

    def handle_start_game(self):

        hang = ""
        sentence = self.sentence_service.get_random_sentence()

        hangman_sentence = self.sentence_service.convert_sentence_hangman_style(sentence)

        while True:

            print(hangman_sentence)
            print("Hang: ", hang)

            letter = input("Enter letter: ")

            if self.sentence_service.check_letter(sentence, hangman_sentence, letter):
                hangman_sentence = self.sentence_service.reveal_letter(sentence, hangman_sentence, letter)

            else:
                hang = self.sentence_service.add_letter_in_hang(hang)

            if self.sentence_service.check_win(sentence, hangman_sentence):
                print(hangman_sentence)
                print("Congrats, you won!")
                return

            if self.sentence_service.check_lose(hang):

                print(hang)
                print("Game over!")
                return



