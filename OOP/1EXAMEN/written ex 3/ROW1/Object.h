//
// Created by Raul on 6/27/2023.
//

#ifndef ROW1_OBJECT_H
#define ROW1_OBJECT_H

#include <string>

class Object {
protected:
    void* data;
public:

    Object();

    virtual ~Object();

    virtual std::string toString() = 0;

    virtual void print() = 0;
};

#endif //ROW1_OBJECT_H
