//
// Created by Raul on 4/27/2023.
//

#ifndef MODELTEST_REPO_H
#define MODELTEST_REPO_H

#include <vector>
#include "../Domain/Car.h"

class Repo {
private:
std::vector<Car> cars;

public:
    Repo();

    void add(Car);

    bool search(Car);

    Repo& operator=(Repo rhs);

    std::vector<Car> sortedCars();

    void deleteCar(Car);
};


#endif //MODELTEST_REPO_H
