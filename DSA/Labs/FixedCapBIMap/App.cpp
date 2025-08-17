#include "ExtendedTest.h"
#include "ShortTest.h"

#include "FixedCapBiMap.h"
#include "FixedCapBiMapIterator.h"


#include <iostream>
using namespace std;


int main() {
	//testAll();
	//testAllExtended();

    FixedCapBiMap map(10);

    map.add(1, 10);
    map.add(1, 20);
    map.add(3,300);

    FixedCapBiMapIterator iter = map.iterator();

    while(iter.valid())
    {
        cout << iter.getCurrent().first << " " << iter.getCurrent().second << endl;
        iter.next();
    }
    iter.first();
    iter.updateCurrent(999);

    cout << "After update...\n";

    while(iter.valid())
    {
        cout << iter.getCurrent().first << " " << iter.getCurrent().second << endl;
        iter.next();
    }

	cout << "That's all!" << endl;
	system("pause");
	return 0;
}


