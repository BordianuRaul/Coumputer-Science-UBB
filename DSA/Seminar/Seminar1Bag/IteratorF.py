from BagF import BagF


class BagFIterator:

    def __init__(self, bag: BagF):

        self._bag = bag
        self._current_index = 0
        self._current_freq = 0

    def next(self):

        if self.valid():

            if self._current_freq == self._bag._freq[self._current_index]:

                self._current_index += 1
                self._current_freq = 1
            else:
                self._current_freq += 1

        else:
            raise ValueError()

    def valid(self):

        if self._current_index < len(self._bag._elems):
            return True
        return False
