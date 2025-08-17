import Bag


class BagIterator:

    def __init__(self, bag: Bag):

        self._bag = bag
        self._current = 0

    def valid(self):

        if self._current < len(self._bag._elems):

            return True

        return False

    def first(self):

        self._current = 0

    def next(self):

        if self.valid():
            self._current += 1
        else:
            raise ValueError("Iterator on invalid position")

    def getCurrent(self):

        return self._bag._elems[self._current]
