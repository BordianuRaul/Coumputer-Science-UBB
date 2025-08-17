#include <iostream>
#include "UI/UI.h"

int main() {

    Service service;

    service.add("Fiat", "Bravo", 2007, "Red");
    service.add("Ford", "Fiesta", 2007, "Blue");

    UI ui(service);

    ui.runConsole();
    return 0;
}
