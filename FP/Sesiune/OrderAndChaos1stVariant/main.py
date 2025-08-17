from Domain.MoveValidator import MoveValidator
from Domain.Table import Table
from Service.TableService import TableService
from UI.UI import UI


def main():

    table = Table()
    move_validator = MoveValidator()
    table_service = TableService(table, move_validator)
    ui = UI(table_service)

    ui.run_game()


if __name__ == "__main__":
    main()
