from Service.BoardService import BoardService


class UI:

    def __init__(self, board_service: BoardService):

        self._board_service = board_service

    def menu(self):

        print("1.Play Game.")
        print("2.Load Game.")
        print("0.Exit.")

    def run_ui(self):

        while True:

            self.menu()

            option = input("Option: ")
            if option == "0":
                return

            elif option == "1":
                if self._handle_play_game():
                    return

            elif option == "2":
                self._load_game()

            else:
                print("Invalid option!")

    def _handle_player_move(self):

        while True:

            try:

                move = input("Move(row/column/sign): ")

                self._board_service.player_move(int(move[0]) - 1, int(move[1]) - 1, move[2])
                return
            except Exception as ex:
                print("Ooops: ", ex)

    def _handle_play_game(self):

        while True:
            print(self._board_service.str_board())

            self._handle_save_game()

            self._handle_player_move()

            print(self._board_service.str_board())

            if self._board_service.check_win():
                print("\nOrders Wins!\n")
                return True

            print("\n Computer makes his move!\n")

            self._board_service.computer_move()

            if self._board_service.check_win():
                print("\nOrders Wins!\n")
                return True

            if self._board_service.check_win_chaos():
                print("\nChaos WIns\n")
                return True

            print("\n Your turn!\n")

    def _handle_save_game(self):

        while True:

            print("1.Save game.")
            print("2.Continue play.")

            option = input()

            if option == "1":

                self._board_service.save_game()
                return

            elif option == "2":
                return

            else:
                print("Invalid option!")

    def _load_game(self):

        self._board_service.load_game()
        self._handle_play_game()
