//
// Created by Raul on 6/27/2023.
//

#ifndef ROW1_STRING_H
#define ROW1_STRING_H
#include "Object.h"

class String : public Object{
public:
    String(std::string value);

    ~String();

    std::string toString();

    bool operator==(const char* other) const {
        return *static_cast<std::string*>(data) == other;
    }

    void print();
};


#endif //ROW1_STRING_H
