//
// Created by Raul on 3/22/2023.
//

#include "DynamicVector.h"

DynamicVector::DynamicVector(int cap) : capacity{cap}, size{0} {

    this->elems = new TElem[cap];

}

DynamicVector::DynamicVector(const DynamicVector& v)
{

    this->size = v.size;
    this->capacity = v.capacity;
    this->elems = new TElem[this->capacity];

    for(int i = 0; i < this->size; i++)
        this->elems[i] = v.elems[i];

}

DynamicVector& DynamicVector::operator=(const DynamicVector& v)
{
    if(this == &v)
        return *this;

    this->size = v.size;
    this->capacity = v.capacity;

    delete[] this->elems;

    this->elems = new TElem[this->capacity];

    for(int i = 0; i < this->size; i++)
        this->elems[i] = v.elems[i];

    return *this;
}

DynamicVector::~DynamicVector() {

    delete[] this->elems;

}