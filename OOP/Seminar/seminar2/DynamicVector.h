//
// Created by Raul on 3/22/2023.
//

#ifndef SEMINAR2_DYNAMICVECTOR_H
#define SEMINAR2_DYNAMICVECTOR_H

#include "Song.h"

typedef Song TElem;

class DynamicVector {
private:
    TElem* elems;
    int size;
    int capacity;
public:

    DynamicVector(int cap);
    DynamicVector(const DynamicVector&);
    DynamicVector& operator=(const DynamicVector&);
    ~DynamicVector();
};


#endif //SEMINAR2_DYNAMICVECTOR_H
