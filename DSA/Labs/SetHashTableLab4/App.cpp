#include "Set.h"
#include "SetIterator.h"
#include "ExtendedTest.h"
#include "ShortTest.h"
#include <stack>
#include <iostream>

using namespace std;

bool condition(TElem element) {
    // Keep elements that are greater than 5
    return element > 5;
}

int main() {

	testAll();
	testAllExtended();

    Set set;
    set.add(2);
    set.add(4);
    set.add(6);
    set.add(8);
    set.add(10);

    std::cout << "Original set: ";
    SetIterator it = set.iterator();
    while (it.valid()) {
        std::cout << it.getCurrent() << " ";
        it.next();
    }
    std::cout << std::endl;

    set.filter(condition);

    std::cout << "Filtered set: ";
    it.first();
    while (it.valid()) {
        std::cout << it.getCurrent() << " ";
        it.next();
    }
    std::cout << std::endl;

    return 0;

}



