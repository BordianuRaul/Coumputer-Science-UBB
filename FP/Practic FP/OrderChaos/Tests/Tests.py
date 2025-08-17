import unittest

from Domain.Board import Board
from Domain.MoveValidator import MoveValidator
from Repository.BoardRepo import BoardRepo
from Service.BoardService import BoardService


class Tests(unittest.TestCase):

    def test_computer_move(self):

        empty_board = Board()
        board = Board()
        board_repo = BoardRepo()
        move_validator = MoveValidator()
        board_service = BoardService(board, board_repo, move_validator)

        self.assertTrue(str(empty_board) == board_service.str_board())

        board_service.computer_move()

        self.assertFalse(str(empty_board) == board_service.str_board())

    def test_1_move_win_row(self):

        test_board = [["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "],
                      ["X", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

        check_board = [["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "],
                      ["X", " ", " ", " ", " ", " "], ["0", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

        empty_board = Board()

        empty_board.set_board(check_board)

        board = Board()

        board.set_board(test_board)

        board_repo = BoardRepo()
        move_validator = MoveValidator()
        board_service = BoardService(board, board_repo, move_validator)

        board_service.computer_move()

        self.assertTrue(str(empty_board) == board_service.str_board())

    def test_1_move_win_column(self):

        test_board = [["X", "X", "X", "X", "X", " "], [" ", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "],
                      ["X", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

        check_board = [["X", "X", "X", "X", "X", "0"], [" ", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "],
                      ["X", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

        empty_board = Board()

        empty_board.set_board(check_board)

        board = Board()

        board.set_board(test_board)

        board_repo = BoardRepo()
        move_validator = MoveValidator()
        board_service = BoardService(board, board_repo, move_validator)

        board_service.computer_move()

        self.assertTrue(str(empty_board) == board_service.str_board())

    def test_check_win(self):
        test_board = [["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "],
                      ["X", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

        check_board = [["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "], ["X", " ", " ", " ", " ", " "],
                       ["X", " ", " ", " ", " ", " "], ["0", " ", " ", " ", " ", " "], [" ", " ", " ", " ", " ", " "]]

        empty_board = Board()

        empty_board.set_board(check_board)

        board = Board()

        board.set_board(test_board)

        board_repo = BoardRepo()
        move_validator = MoveValidator()
        board_service = BoardService(board, board_repo, move_validator)

        self.assertTrue(board_service.check_1_move_win())





