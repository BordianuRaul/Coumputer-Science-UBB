//
// Created by Raul on 3/8/2023.
//

#ifndef SEM1_REPOSITORY_H
#define SEM1_REPOSITORY_H

#pragma once
#include "Planet.h"

typedef struct{

    Planet* planets;
    int size;

} Repository;

Repository* createRepo(int maxCapacity);

void destroyRepo(Repository*);

int getSize(Repository*);

void addPlanet(Repository*, Planet*);
#endif //SEM1_REPOSITORY_H
