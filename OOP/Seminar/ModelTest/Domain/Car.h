//
// Created by Raul on 4/27/2023.
//

#ifndef MODELTEST_CAR_H
#define MODELTEST_CAR_H

#include <string>

class Car {
private:
    std::string name;
    std::string model;
    int year;
    std::string color;
public:
    Car(const std::string &name, const std::string &model, int year, const std::string &color);

    const std::string &getName() const;

    const std::string &getModel() const;

    int getYear() const;

    const std::string &getColor() const;

    bool operator==(const Car &rhs) const;

    bool operator<(const Car &rhs) const;

    bool operator>(const Car &rhs) const;

    bool operator<=(const Car &rhs) const;

    bool operator>=(const Car &rhs) const;
};


#endif //MODELTEST_CAR_H
