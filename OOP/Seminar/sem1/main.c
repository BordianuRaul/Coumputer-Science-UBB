//
// Created by Raul on 3/8/2023.
//

#include "Planet.h"
#include <stdio.h>
#include <crtdbg.h>

int main(){

    Planet p = createPlanet("HD 133328", "a blue-irish planet", 10);
    printf("%s", getName(&p));
    destroyPlanet(&p);


    _CrtDumpMemoryLeaks();

    return 0;
}
