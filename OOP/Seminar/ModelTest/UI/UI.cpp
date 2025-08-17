//
// Created by Raul on 4/27/2023.
//

#include "UI.h"

#include <iostream>

UI::UI(const Service &newService)
{
    this->service = newService;
}

void UI::printConsole() {

    std::cout << "1.Add.\n";
    std::cout << "2.Print.\n";
    std::cout << "0.Exit.\n";

}

void UI::handleAdd() {


    std::string name;
    std::string model;
    std::string color;
    int year;

    std::cout << "Name: ";
    std::cin >> name;
    std::cout <<"\n";
    std::cin.ignore(256, '\n');

    std::cout << "Model: ";
    std::cin >> model;
    std::cout <<"\n";
    std::cin.ignore(256, '\n');

    std::cout << "Year=: ";
    std::cin >> year;
    std::cout << "\n";

    std::cout << "Color: ";
    std::cin >> color;
    std::cout <<"\n";
    std::cin.ignore(256, '\n');

    if(this->service.add(name, model, year, color))
        std::cout << "The car was added with success.\n";
    else std::cout << "The car allready exists, it was not added again.\n";

}

void UI::handlePrint() {


    std::vector<Car> cars = this->service.sortedCars();

    for(auto & i: cars)
    {
        std::cout << i.getName() << "\n";
        std::cout << i.getModel() << "\n";
        std::cout << i.getYear() << "\n";
        std::cout << i.getColor() << "\n";
        std::cout << "\n";
    }

}

void UI::runConsole(){


    while(true) {


        int option;

        this->printConsole();

        std::cout << "Option = ";
        std::cin >> option;

        switch (option) {

            case 1: {
                this->handleAdd();
                break;
            }

            case 2: {
                this->handlePrint();
                break;
            }

            case 0:
                return;

            default: {
                std::cout << "Invalid option!\n";
                break;
            }

        }
    }

}