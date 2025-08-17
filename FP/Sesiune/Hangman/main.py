from Domain.SentenceValidator import SentenceValidator
from Repository.SentenceRepo import SentenceRepo
from Service.SentenceServcie import SentenceService
from UI.UI import UI


def main():

    sentence_repo = SentenceRepo("sentences.txt")
    sentence_validator = SentenceValidator()
    sentence_service = SentenceService(sentence_repo, sentence_validator)
    ui = UI(sentence_service)

    ui.run_ui()

if __name__ == "__main__":

    main()
