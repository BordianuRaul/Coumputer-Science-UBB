import unittest

from Domain.Board import Board
from Domain.MoveValidator import MoveValidator
from Service.BoardService import BoardService


class Tests(unittest.TestCase):

    def test_computer_move(self):

        empty_board = Board()
        board = Board()
        move_validator = MoveValidator()
        board_service = BoardService(board, move_validator)

        self.assertTrue(str(empty_board) == board_service.str_board())

        board_service.computer_move()

        self.assertFalse(str(empty_board) == board_service.str_board())

