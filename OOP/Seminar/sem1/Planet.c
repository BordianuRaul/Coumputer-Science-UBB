//
// Created by Raul on 3/8/2023.
//


#include "Planet.h"

Planet createPlanet(char* name, char* type, double distToEarth){

    Planet p;

    p.name = malloc(sizeof(char)* (strlen(name) + 1));

    if(p.name == NULL)
        return p;

    strcpy(p.name, name);

    p.type = malloc(sizeof(char)* (strlen(type) + 1));

    if(p.type == NULL)
        return p;

    strcpy(p.type, type);

    p.distToEarth = distToEarth;

    return p;

}

void destroyPlanet(Planet* planet){

    free(planet->name);
    free(planet->type);

}

char* getName(Planet* p){

    if(p == NULL){
        return NULL;
    }

    return p->name;

}
