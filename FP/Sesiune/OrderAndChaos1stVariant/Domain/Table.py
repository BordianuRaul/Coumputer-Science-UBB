
import texttable

from Domain.Move import Move


class Table:

    def __init__(self):

        self._data = [[" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "],
                      [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

    @property
    def data(self):
        return self._data

    def __str__(self):

        str_table = texttable.Texttable()

        for i in range(6):
            str_table.add_row(self._data[i])

        return str_table.draw()

    def add_move(self, move: Move):

        self._data[move.x][move.y] = move.char

    def get_cell(self, x, y):

        return self._data[x][y]
