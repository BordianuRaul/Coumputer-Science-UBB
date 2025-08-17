#include <iostream>

#include "MyObjectList.h"
#include "Integer.h"
#include "String.h"
#include "Object.h"
#include <cassert>
using namespace std;

int main() {

    MyObjectList list{};

    list.add(new Integer{2}).add(new String { "Hi"});

    String* s = new String{ "Bye"};

    assert(*s == "Bye");

    list.add(s).add(new Integer {5 });

    assert(list.length() == 4);

    for(Object* o : list)
    {
        o->print();
    }

    cout << "HEHEHHEHEHE\n";
    return 0;
}
