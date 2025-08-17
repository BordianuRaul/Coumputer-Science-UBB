import texttable


class Board:

    def __init__(self):

        self.board = [[" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "],
                      [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

    def __str__(self):

        str_board = texttable.Texttable()

        for row in self.board:
            str_board.add_row(row)

        return str_board.draw()

    def get_cell(self, row: int, column: int):

        return self.board[row][column]

    def add_move(self, row: int, column: int, sign: str):

        self.board[row][column] = sign

    def set_board(self, board):

        self.board = board