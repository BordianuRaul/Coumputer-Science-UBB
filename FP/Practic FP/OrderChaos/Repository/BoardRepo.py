from Domain.Board import Board


class BoardRepo:

    def __init__(self):

        self._data = Board()
        self._filename = "game.txt"

        self._load()

    def _load(self):

        try:
            fin = open("game.txt", "rt")

            lines = fin.readlines()

            for line in lines:

                elements = line.split(",")

                for i in range(6):
                    self._data.add_move(int(elements[i][0]), int(elements[i][1]), elements[i][2])

            fin.close()

        except IOError as io:
            print("IOError: ", io)

    def save_file(self):

        fout = open("game.txt", "wt")

        for i in range(0, 6):
            for j in range(0, 6):
                str_move = str(i) + str(j) + self._data.get_cell(i, j) + ","
                fout.write(str_move)

            fout.write("\n")

        fout.close()

    def set_board(self, board: Board):

        self._data = board

    def get_board(self):
        return self._data
