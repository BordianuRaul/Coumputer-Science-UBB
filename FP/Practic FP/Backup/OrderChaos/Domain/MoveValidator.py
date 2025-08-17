from Domain.Board import Board
from Domain.Move import Move


class MoveValidator:

    def validate(self, move: Move, board: Board):

        errors = ""

        if move.row < 0 or move.row > 5:
            errors += "Row index out of range!\n"

        if move.column < 0 or move.column > 5:
            errors += "Column index out of range!\n"

        if board.get_cell(move.row, move.column) == "0" or board.get_cell(move.row, move.column) == "X":
            errors += "A move have been already made at the coordinates given!\n"

        if move.sign != "0" and move.sign != "X":
            errors += "Invalid sign entered!\n"
        if errors:
            raise ValueError(errors)
