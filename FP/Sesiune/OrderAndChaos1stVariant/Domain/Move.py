
class Move:

    def __init__(self, x: int, y:int, char: str):

        self._x = x
        self._y = y
        self._char = char

    @property
    def x(self):
        return self._x

    @property
    def y(self):
        return self._y

    @property
    def char(self):
        return self._char

