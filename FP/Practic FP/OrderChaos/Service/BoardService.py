import random

from Domain.Board import Board
from Domain.Move import Move
from Domain.MoveValidator import MoveValidator
from Repository.BoardRepo import BoardRepo


class BoardService:

    def __init__(self, board: Board, board_repo: BoardRepo, move_validator: MoveValidator):

        self._board = board
        self._move_validator = move_validator
        self._board_repo = board_repo

    def str_board(self):

        return str(self._board)

    def player_move(self, row: int, column: int, sign: str):

        move = Move(row, column, sign)

        self._move_validator.validate(move, self._board)

        self._board.add_move(row, column, sign)

    def computer_move(self):
        """
        Generates a move by the computer, blocks the user from 1 mov wins if possible else makes a random move
        :return:
        """

        if self.check_1_move_win():
            return

        possible_moves = self.get_possible_moves()

        random_move = random.randint(0, len(possible_moves) - 1)

        row = possible_moves[random_move][0]
        column = possible_moves[random_move][1]
        sign = "0"

        self._board.add_move(row, column, sign)

    def get_possible_moves(self):
        """
        Gets all possible moves that can be made in the board
        :return: All possible moves in the board
        """

        possible_moves = []

        for i in range(6):
            for j in range(6):
                if self._board.get_cell(i, j) == " ":
                    possible_moves.append([i, j])

        return possible_moves

    def check_win_on_row(self):

        for i in range(6):
            for j in range(2):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i, j + 1)\
                    == self._board.get_cell(i, j + 2) == self._board.get_cell(i, j + 3)\
                        == self._board.get_cell(i, j+ 4):
                    return True
        return False

    def check_win_on_column(self):

        for j in range(6):
            for i in range(2):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i + 1, j)\
                    == self._board.get_cell(i + 2, j) == self._board.get_cell(i + 3, j)\
                        == self._board.get_cell(i + 4, j):
                    return True
        return False

    def check_win_on_diagonals(self):

        for i in range(5, 3, - 1):
            for j in range(0, 3):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i - 1, j + 1)\
                    == self._board.get_cell(i - 2, j + 2) == self._board.get_cell(i - 3, j + 3)\
                        == self._board.get_cell(i - 4, j + 4):
                    return True

        for i in range(5, 3, - 1):
            for j in range(5, 3, -1):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i - 1, j - 1)\
                    == self._board.get_cell(i - 2, j - 2) == self._board.get_cell(i - 3, j - 3)\
                        == self._board.get_cell(i - 4, j - 4):
                    return True

        return False

    def check_win(self):

        if self.check_win_on_column():
            return True
        if self.check_win_on_row():
            return True
        if self.check_win_on_diagonals():
            return True

        return False

    def check_win_chaos(self):

        for i in range(6):
            for j in range(6):
                if self._board.get_cell(i, j) == " ":
                    return False

        return True

    def save_game(self):

        self._board_repo.set_board(self._board)
        self._board_repo.save_file()

    def load_game(self):

        self._board = self._board_repo.get_board()

    def check_1_move_win(self):
        """
        Checks if there was an 1 move win posibillity from the player and the computer blocked it
        :return:
        """

        if self.check_1_move_win_row():
            return True

        if self.check_1_move_win_column():
            return True

        if self.check_win_on_diagonals():
            return True

        return False

    def check_1_move_win_row(self):

        """
        Stops the player for an 1 move win on row if it was possible
        :return:
        """

        for i in range(6):
            for j in range(2):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i, j + 1)\
                    == self._board.get_cell(i, j + 2) == self._board.get_cell(i, j + 3) \
                        and self._board.get_cell(i, j + 4) == " ":

                    self.counter_move(i, j + 4, self._board.get_cell(i, j + 3))

                    return True
        return False

    def check_1_move_win_column(self):
        """
        Stops the player for an 1 move win on column if it was possible
        :return:
        """

        for j in range(6):
            for i in range(2):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i + 1, j)\
                    == self._board.get_cell(i + 2, j) == self._board.get_cell(i + 3, j)\
                        and self._board.get_cell(i + 4, j) == " ":

                    self.counter_move(i + 4, j, self._board.get_cell(i + 3, j))

                    return True
        return False

    def check_1_move_win_diagonals(self):

        """
        Stops the player for an 1 move win on diagonals if it was possible
        :return:
        """

        for i in range(5, 3, - 1):
            for j in range(0, 3):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i - 1, j + 1)\
                    == self._board.get_cell(i - 2, j + 2) == self._board.get_cell(i - 3, j + 3)\
                        and self._board.get_cell(i - 4, j + 4) == " ":

                    self.counter_move(i - 4,  j + 4, self._board.get_cell(i - 3, j + 3))

                    return True

        for i in range(5, 3, - 1):
            for j in range(5, 3, -1):
                if self._board.get_cell(i, j) != " " and self._board.get_cell(i, j) == self._board.get_cell(i - 1, j - 1)\
                    == self._board.get_cell(i - 2, j - 2) == self._board.get_cell(i - 3, j - 3)\
                        and self._board.get_cell(i - 4, j - 4) == " ":

                    self.counter_move(i - 4, j - 4, self._board.get_cell(i - 3, j - 3))

                    return True

        return False

    def counter_move(self, row, column, sign):

        if sign == "0":
            self._board.add_move(row, column, "X")

        elif sign == "X":
            self._board.add_move(row, column, "0")



