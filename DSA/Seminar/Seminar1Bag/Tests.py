import unittest

from Bag import Bag


class Tests(unittest.TestCase):

    def test_add_bag(self):

        bag = Bag()

        bag.add(20)

        self.assertEqual(bag.size(), 1)

    def test_search(self):
        bag = Bag()

        bag.add(20)

        self.assertTrue(bag.search(20))

        self.assertFalse(bag.search(8))

    def test_nrOccurences(self):
        bag = Bag()

        bag.add(20)

        self.assertEqual(bag.nrOccurrences(20), 1)

    def test_iterator_get_current(self):

        bag = Bag()

        bag.add(20)

        it = bag.iterator()

        self.assertEqual(it.getCurrent(), 20)