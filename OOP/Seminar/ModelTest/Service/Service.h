//
// Created by Raul on 4/27/2023.
//

#ifndef MODELTEST_SERVICE_H
#define MODELTEST_SERVICE_H


#include "../Repo/Repo.h"

class Service {
private:
    Repo repo;
public:
    Service();
    /**
     * Adds a car into the application
     * @return true if the car was added with succes, false if the exists allready in the application
     */
    bool add(std::string, std::string, int, std::string);

    Service& operator=(Service rhs);

    std::vector<Car> sortedCars();
};


#endif //MODELTEST_SERVICE_H
