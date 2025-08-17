//
// Created by Raul on 4/27/2023.
//

#include "Car.h"

Car::Car(const std::string &name, const std::string &model, int year, const std::string &color) : name(name),
                                                                                                  model(model),
                                                                                                  year(year),
                                                                                                  color(color) {}

const std::string &Car::getName() const {
    return name;
}

const std::string &Car::getModel() const {
    return model;
}

int Car::getYear() const {
    return year;
}

const std::string &Car::getColor() const {
    return color;
}

bool Car::operator==(const Car &rhs) const {
    return model == rhs.model &&
           year == rhs.year;
}

bool Car::operator<(const Car &rhs) const {
    if (name < rhs.name)
        return true;
    if (rhs.name < name)
        return false;
    return model < rhs.model;
}

bool Car::operator>(const Car &rhs) const {
    return rhs < *this;
}

bool Car::operator<=(const Car &rhs) const {
    return !(rhs < *this);
}

bool Car::operator>=(const Car &rhs) const {
    return !(*this < rhs);
}
