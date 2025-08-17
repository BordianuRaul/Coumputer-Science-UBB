//
// Created by Raul on 6/27/2023.
//

#include "MyObjectList.h"

MyObjectList& MyObjectList::add(Object *obj)
{

    this->objects.push_back(obj);
    return *this;
}

int MyObjectList::length() {

    return this->objects.size();

}