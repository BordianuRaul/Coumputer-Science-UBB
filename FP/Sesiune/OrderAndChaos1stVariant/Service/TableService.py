from Domain.Move import Move
from Domain.MoveValidator import MoveValidator
from Domain.Table import Table


class TableService:

    def __init__(self, table: Table, move_validator: MoveValidator):

        self._table = table
        self._move_validator = move_validator

    def player_move(self, x: int, y: int, char: str):

        move = Move(x, y, char)
        self._move_validator.validate(move, self._table)

        self._table.add_move(move)

    def computer_move(self):

        char = self.most_common_char()

        row, column = self.possible_move_indexes()

        computer_move = Move(row, column, char)

        self._table.add_move(computer_move)


    def most_common_char(self):

        nr_x = 0
        nr_0 = 0

        for i in range(6):
            for j in range(6):
                if self._table.get_cell(i,j) == "X":
                    nr_x += 1
                elif self._table.get_cell(i,j) == "0":
                    nr_0 += 1

        if nr_x > nr_0:
            return "X"
        else:
            return "0"

    def possible_move_indexes(self):

        for i in range(6):
            for j in range(6):
                if self._table.get_cell(i, j) == " ":
                    return i, j

    def get_table(self):

        return str(self._table)

    def check_win(self):

        if self.check_win_on_row():

            return True

        if self.check_win_on_column():

            return True

        if self.check_win_on_diagonals():

            return True

        return False

    def check_win_on_row(self):

        for i in range(6):
            for j in range(1):
                if self._table.get_cell(i, j) != " " and self._table.get_cell(i, j) == self._table.get_cell(i, j + 1) == self._table.get_cell(i, j + 2) ==\
                   self._table.get_cell(i, j + 3) == self._table.get_cell(i, j + 4):
                    return True

        return False

    def check_win_on_column(self):
        for j in range(6):
            for i in range(1):
                if self._table.get_cell(i, j) != " " and self._table.get_cell(i, j) == self._table.get_cell(i + 1, j)\
                   == self._table.get_cell(i + 2, j) ==\
                   self._table.get_cell(i + 3, j) == self._table.get_cell(i + 4, j):
                    return True

        return False

    def check_win_on_diagonals(self):

        for i in range(5, 3, - 1):
            for j in range(2):
                if self._table.get_cell(i, j) != " " and self._table.get_cell(i, j) ==\
                   self._table.get_cell(i - 1, j + 1) == self._table.get_cell(i - 2, j + 2) ==\
                   self._table.get_cell(i - 3, j + 3) == self._table.get_cell(i - 4, j + 4):
                    return True

        for i in range(5, 3, - 1):
            for j in range(5, 3, - 1):
                if self._table.get_cell(i, j) != " " and self._table.get_cell(i, j) ==\
                   self._table.get_cell(i - 1, j - 1) == self._table.get_cell(i - 2, j - 2) ==\
                   self._table.get_cell(i - 3, j - 3) == self._table.get_cell(i - 4, j - 4):
                    return True

        return False

    def check_draw(self):

        for i in range(6):
            for j in range(6):
                if self._table.get_cell(i, j) == " ":
                    return False

        return True