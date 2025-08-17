
class Move:

    def __init__(self, row: int, column: int, sign: str):

        self._row = row
        self._column = column
        self._sign = sign

    @property
    def row(self):
        return self._row

    @property
    def column(self):
        return self._column

    @property
    def sign(self):
        return self._sign