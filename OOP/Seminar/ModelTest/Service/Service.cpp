//
// Created by Raul on 4/27/2023.
//

#include "Service.h"

bool Service::add(std::string name, std::string model, int year, std::string color) {

    Car car(name, model, year, color);

    if(this->repo.search(car))
        return false;

    this->repo.add(car);
    return true;

}

Service::Service() {}

Service& Service::operator=(Service rhs) {

    if(this != &rhs)
        this->repo = rhs.repo;
    else return *this;

}

std::vector<Car> Service::sortedCars() {

    return this->repo.sortedCars();

}