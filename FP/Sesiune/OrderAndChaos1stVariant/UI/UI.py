from Service.TableService import TableService

class UI:

    def __init__(self, table_service: TableService):

        self.table_service = table_service

    def run_game(self):

        while True:

            print(str(self.table_service.get_table()))

            self._handle_player_move()

            print(str(self.table_service.get_table()))

            if self.table_service.check_draw():
                print("Chaos wins!")
                return

            if self.table_service.check_win():
                print("Order wins!")
                return

            print("\nComputer makes a move:\n")

            self._handle_computer_move()

            if self.table_service.check_draw():
                print("Chaos wins!")
                return

            if self.table_service.check_win():
                print("Order wins!")
                return

    def _handle_player_move(self):

        while True:
            try:
                str_move = input("Enter move(row/column/0 or X): ")

                row = int(str_move[0]) - 1
                column = int(str_move[1]) - 1
                char = str_move[2]

                self.table_service.player_move(row, column, char)

                return

            except Exception as ex:
                print("Invalid move: ", ex)

    def _handle_computer_move(self):

        self.table_service.computer_move()
