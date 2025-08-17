//
// Created by Raul on 6/27/2023.
//

#include "Integer.h"
#include <iostream>
Integer::Integer(int value) {

    this->data = new int(value);

}

Integer::~Integer(){

    delete static_cast<int*>(this->data);

}

std::string Integer::toString() {


    return std::to_string(*static_cast<int*>(this->data));

}

void Integer::print() {

    std::cout << this->toString();

}