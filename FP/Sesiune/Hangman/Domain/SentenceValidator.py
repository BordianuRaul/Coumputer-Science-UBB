

class SentenceValidator:

    def validate(self, sentence):

        errors = ""

        if len(sentence) < 3:
            errors += "Sentence must contain at least 3 letters.\n"

        if errors:
            raise ValueError(errors)
