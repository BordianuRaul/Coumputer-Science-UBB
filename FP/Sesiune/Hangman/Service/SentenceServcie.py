import random

from Domain.SentenceValidator import SentenceValidator
from Repository.SentenceRepo import SentenceRepo


class SentenceService:

    def __init__(self, sentence_repo: SentenceRepo, sentence_validator: SentenceValidator):

        self.sentence_repo = sentence_repo
        self.sentence_validator = sentence_validator

    def add_sentence(self, sentence: str):

        self.sentence_validator.validate(sentence)

        self.sentence_repo.add(sentence)

    def get_random_sentence(self):

        random_number = random.randint(0, len(self.sentence_repo.data) - 1)

        return self.sentence_repo.data[random_number]

    def convert_sentence_hangman_style(self, sentence: str):

        hangman_style = ""
        for i in range(len(sentence)):

            if sentence[i] == sentence[0]:
                hangman_style += sentence[0]

            elif sentence[i] == sentence[len(sentence) - 1]:
                hangman_style += sentence[len(sentence) - 1]

            elif sentence[i] == " ":
                hangman_style += " "

            else:
                hangman_style += "_"

        return hangman_style

    def check_letter(self, sentence: str, hang_man: str, letter: str):

        if letter in hang_man:
            return False

        if letter not in sentence:
            return False

        return True

    def reveal_letter(self, sentence: str, hang_man: str, letter: str):

        new_hangman = ""
        for i in range(len(sentence)):
            if sentence[i] == letter:
                new_hangman += letter
            else:
                new_hangman += hang_man[i]

        return new_hangman

    def add_letter_in_hang(self, hang: str):

        hangman = "hangman"

        hang += hangman[len(hang)]

        return hang

    def check_win(self, sentence, hangman_sentence):

        if sentence == hangman_sentence:
            return True
        return False

    def check_lose(self, hang):

        if hang == "hangman":
            return True
        return False
