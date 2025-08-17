from Domain.Board import Board
from Domain.MoveValidator import MoveValidator
from Service.BoardService import BoardService
from UI.UI import UI


def main():

    board = Board()
    move_validator = MoveValidator()
    board_service = BoardService(board, move_validator)
    ui = UI(board_service)

    ui.run_ui()



if __name__ == '__main__':
    main()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
