//
// Created by Raul on 3/8/2023.
//

#include "Repository.h"

Repository* createRepo(int maxCapacity){

    Repository* repo;

    repo = malloc(sizeof(Repository));

    repo->size - 0;
    repo->planets = malloc(sizeof(Planet) * maxCapacity);

    return repo;

}

void destroyRepo(Repository* repo){

    if(repo != NULL) {

        for(int i = 0; i < getSize(repo); i++)
            destroyPlanet(&(repo->planets[i]));

        free(repo);
    }
}

int getSize(Repository* repo){

    return repo->size;
}

void addPlanet(Repository* repo, Planet* planet){

    repo->planets[repo->size++] = *planet;

}