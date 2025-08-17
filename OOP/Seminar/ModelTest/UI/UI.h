//
// Created by Raul on 4/27/2023.
//

#ifndef MODELTEST_UI_H
#define MODELTEST_UI_H

#include "../Service/Service.h"

class UI {
private:
    Service service;
public:
    UI(const Service &service);

    void printConsole();

    void runConsole();

    void handleAdd();

    void handlePrint();
};


#endif //MODELTEST_UI_H
