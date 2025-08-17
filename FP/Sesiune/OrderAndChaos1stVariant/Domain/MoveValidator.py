from Domain.Move import Move
from Domain.Table import Table


class MoveValidator:

    def validate(self, move: Move, table: Table):

        errors = ""

        if move.x < 0 or move.x > 5:
            errors += "X index out of range!\n"

        if move.y < 0 or move.y > 5:
            errors += "Y index out of range!\n"

        if move.char != "0" and move.char != "X":
            errors += "Invalid character, enter a 0 or an X.\n"

        if table.get_cell(move.x, move.y) != " ":
            errors += "Cell is already occupied\n"

        if errors:
            raise ValueError(errors)
