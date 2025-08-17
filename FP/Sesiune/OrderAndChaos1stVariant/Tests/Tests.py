import unittest

from Domain.Move import Move
from Domain.MoveValidator import MoveValidator
from Domain.Table import Table
from Service.TableService import TableService


class Tests(unittest.TestCase):

    def test_check_win_on_row(self):

        table = Table()
        move = Move(3, 0, "0")
        table.add_move(move)

        move = Move(3, 1, "0")
        table.add_move(move)

        move = Move(3, 2, "0")
        table.add_move(move)

        move = Move(3, 3, "0")
        table.add_move(move)

        move = Move(3, 4, "0")
        table.add_move(move)

        move_validator = MoveValidator()

        table_service = TableService(table, move_validator)

        self.assertEqual(table_service.check_win_on_row(), True)

        move = Move(3, 4, "X")
        table.add_move(move)

        self.assertEqual(table_service.check_win_on_row(), False)

    def test_check_win_on_column(self):

        table = Table()

        move = Move(0, 3, "0")
        table.add_move(move)

        move = Move(1, 3, "0")
        table.add_move(move)

        move = Move(2, 3, "0")
        table.add_move(move)

        move = Move(3, 3, "0")
        table.add_move(move)

        move = Move(4, 3, "0")
        table.add_move(move)

        move_validator = MoveValidator()

        table_service = TableService(table, move_validator)

        self.assertEqual(table_service.check_win_on_column(), True)

        move = Move(4, 3, "X")
        table.add_move(move)

        self.assertEqual(table_service.check_win_on_column(), False)

    def test_win_on_diagonal(self):

        table = Table()

        move = Move(4, 1, "0")
        table.add_move(move)

        move = Move(3, 2, "0")
        table.add_move(move)

        move = Move(2, 3, "0")
        table.add_move(move)

        move = Move(1, 4, "0")
        table.add_move(move)

        move = Move(0, 5, "0")
        table.add_move(move)

        move_validator = MoveValidator()

        table_service = TableService(table, move_validator)

        self.assertEqual(table_service.check_win_on_diagonals(), True)

        move = Move(0, 5, "X")
        table.add_move(move)

        self.assertEqual(table_service.check_win_on_diagonals(), False)
