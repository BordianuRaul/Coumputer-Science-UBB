//
// Created by Raul on 6/27/2023.
//

#ifndef ROW1_INTEGER_H
#define ROW1_INTEGER_H
#include "Object.h"

class Integer : public Object {
public:
    Integer(int);

    ~Integer();

    std::string toString();

    void print();
};


#endif //ROW1_INTEGER_H
