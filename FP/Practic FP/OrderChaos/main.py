from Domain.Board import Board
from Domain.MoveValidator import MoveValidator
from Repository.BoardRepo import BoardRepo
from Service.BoardService import BoardService
from UI.UI import UI


def main():

    board = Board()
    board_repo = BoardRepo()
    move_validator = MoveValidator()
    board_service = BoardService(board, board_repo, move_validator)
    ui = UI(board_service)

    ui.run_ui()


if __name__ == '__main__':
    main()
