//
// Created by Raul on 6/27/2023.
//

#ifndef ROW1_MYOBJECTLIST_H
#define ROW1_MYOBJECTLIST_H

#include "Object.h"
#include <vector>
using namespace std;

class MyObjectList {
private:

    vector<Object*> objects;

public:

    MyObjectList& add(Object*);

    int length();

    std::vector<Object*>::iterator begin() {
        return objects.begin();
    }

    std::vector<Object*>::iterator end() {
        return objects.end();
    }


};


#endif //ROW1_MYOBJECTLIST_H
