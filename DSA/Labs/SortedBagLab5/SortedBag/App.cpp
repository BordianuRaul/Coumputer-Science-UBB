#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <iostream>
#include "ShortTest.h"
#include "ExtendedTest.h"

using namespace std;
bool relation22(TComp r1, TComp r2) {
    return r1 <= r2;
}
int main() {

	testAll();
	testAllExtended();

    SortedBag bag(relation22);

    bag.add(1);
    bag.add(2);
    bag.add(3);
    bag.add(4);
    bag.add(5);

    auto it = bag.iterator();
    while(it.valid())
    {
        cout << it.getCurrent() << " ";
        it.next();
    }
    cout << endl;
    cout << "Bag size: " << bag.size() << endl;

    it.first();

    while(it.valid())
    {
        cout << it.removeCurrent() << " ";
    }

    cout << endl;
    cout << "Bag size: " << bag.size() << endl;

    try{
        it.removeCurrent();
    }
    catch (exception&) {
        cout << "Exception catched!" << endl;
    }

	cout << "Test over.\n" << endl;
	system("pause");
}
