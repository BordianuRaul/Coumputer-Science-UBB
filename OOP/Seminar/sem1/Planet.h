//
// Created by Raul on 3/8/2023.
//

#ifndef SEM1_PLANET_H
#define SEM1_PLANET_H
#include <stdlib.h>
#include <string.h>

#pragma once


typedef struct{
    char* name;
    char* type;

    double distToEarth;
} Planet;

Planet createPlanet(char*, char*, double);

void destroyPlanet(Planet*);

char* getName(Planet* p);

#endif //SEM1_PLANET_H
