//
// Created by Raul on 6/27/2023.
//

#include "String.h"
#include <iostream>


String::String(std::string value) {

    this->data = new std::string(value);

}

String::~String()
{
    delete static_cast<std::string*>(this->data);
}

std::string String::toString() {

    return *static_cast<std::string*>(this->data);

}

void String::print() {

    std::cout << this->toString();

}

