
from Iterator import BagIterator


class Bag:

    def __init__(self):

        self._elems = []

    def add(self, element):

        self._elems.append(element)

    def remove(self, element):

        for elem in self._elems:

            if elem == element:
                self._elems.remove(elem)
                return True

        return False

    def search(self, e):

        if e in self._elems:
            return True
        return False

    def size(self):

        return len(self._elems)

    def nrOccurrences(self, e):

        nrOccurrences = 0
        for current_elem in self._elems:
            if current_elem == e:
                nrOccurrences += 1

        return nrOccurrences

    def destroy(self):

        self._elems.clear()

    def iterator(self):

        iterator = BagIterator(self)
        return  iterator

