

class SentenceRepo:

    def __init__(self, file_name: str):

        self.data = []
        self.filename = file_name
        self.load()

    def load(self):

        lines = []

        try:

            fin = open(self.filename, "rt")

            lines = fin.readlines()

            fin.close()

        except IOError as er:
            print("IOError: ", er)

        for line in lines:
            self.add(line)

    def save(self):

        fout = open(self.filename, "wt")

        for sentence in self.data:
            str_sentence = sentence
            fout.write(str_sentence)

        fout.close()


    def add(self, sentence: str):

        if sentence in self.data:
            raise ValueError("Sentence already in repo!")

        self.data.append(sentence)

        self.save()

