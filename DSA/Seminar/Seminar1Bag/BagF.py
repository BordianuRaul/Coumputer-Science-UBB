

class BagF:

    def __init__(self):

        self._elems = []
        self._freq = []

    def add(self, elem):

        if elem not in self._elems:
            self._elems.append(elem)
            self._freq.append(1)

        else:
            for i in range(len(self._elems)):
                if self._elems[i] == elem:
                    self._freq[i] += 1

    def size(self):

        nr = 0

        for freq in self._freq:

            nr += freq

