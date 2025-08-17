#include "ExtendedTest.h"
#include "ShortTest.h"

#include "SortedMap.h"
#include "SMIterator.h"


#include <iostream>
using namespace std;

bool relation1(TKey cheie1, TKey cheie2) {
    if (cheie1 <= cheie2) {
        return true;
    }
    else {
        return false;
    }
}

int main() {
	testAll();
	testAllExtended();

    SortedMap map(relation1);

    map.add(1, 10);
    map.add(2, 20);
    map.add(3, 30);
    map.add(4, 40);
    map.add(6, 60);
    map.add(5, 50);
    map.add(7, 70);
    map.add(8, 80);
    map.add(9, 90);

    SMIterator it = map.iterator();

    cout << it.getCurrent().first << " " << it.getCurrent().second << "\n";

    it.jumpForward(4);

    cout << it.getCurrent().first << " " << it.getCurrent().second << "\n";

	cout << "That's all!" << endl;
	system("pause");
	return 0;
}


