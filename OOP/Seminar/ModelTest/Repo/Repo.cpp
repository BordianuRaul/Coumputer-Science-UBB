//
// Created by Raul on 4/27/2023.
//

#include <algorithm>
#include "Repo.h"

void Repo::add(Car car) {


    this->cars.push_back(car);

}

Repo::Repo() {}

bool Repo::search(Car car) {

    for(auto & i : this->cars)
    {
        if(car == i)
            return true;
    }
    return false;

}

Repo& Repo::operator=(Repo rhs) {

    if(this != &rhs)
        this->cars = rhs.cars;
    else return *this;

}

std::vector<Car> Repo::sortedCars() {

    std::sort(this->cars.begin(), this->cars.end());
    return this->cars;
}

void Repo::deleteCar(Car car) {

    auto it = std::find(this->cars.begin(), this->cars.end(), car);

    if(it != this->cars.end())
    {
        this->cars.erase(it);
    }


}