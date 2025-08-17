#include <iostream>

#include "ShortTest.h"
#include "ExtendedTest.h"
#include "SortedIteratedList.h"
#include "ListIterator.h"

bool relation10(TComp e1, TComp e2) {
    if (e1 <= e2) {
        return true;
    }
    else {
        return false;
    }
}

int main(){
    testAll();
    testAllExtended();
    std::cout<<"Finished IL Tests!"<<std::endl;

    SortedIteratedList list(relation10);

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(2);
    list.add(5);
    list.add(4);
    list.add(6);

    ListIterator start = list.first();
    ListIterator end = list.search(4);

    ListIterator printIt = list.first();

    while(printIt.valid())
    {
        std::cout << printIt.getCurrent() << " ";
        printIt.next();
    }

    std::cout << "\n";

    list.removeBetween(start, end);

    printIt.first();

    while(printIt.valid())
    {
        std::cout << printIt.getCurrent() << " ";
        printIt.next();
    }

    std::cout << "\n";




	system("pause");
}