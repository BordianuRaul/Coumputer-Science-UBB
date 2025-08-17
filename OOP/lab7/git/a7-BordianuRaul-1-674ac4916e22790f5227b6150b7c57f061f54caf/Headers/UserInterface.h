//
// Created by Raul on 3/29/2023.
//

#ifndef A45_BORDIANURAUL_USERINTERFACE_H
#define A45_BORDIANURAUL_USERINTERFACE_H

#include "Service.h"

class UserInterface {
private:
    Service service;

public:

    UserInterface(const Service&);

    static void printMenu();

    static void printAdministratorMenu();

    static void printUserMenu();

    static void printSlideShowMenu();

    void handlePrintSlideShowTutorial(const Tutorial&);

    void handlePrintSlideShow(const std::vector<Tutorial>& tutorials, int);

    void runUI();

    void handleAdd();

    void handleDelete();

    void handleUpdate();

    void handlePrintAll();

    void handleAdministratorMenu();

    void handleSlideShow();

    void handleUserMenu();

    void handleSeeWatchList();

    void printTutorial(const Tutorial&);

    void deleteWatchList(const Tutorial&);

    void handleDeleteWatchList();

    void increaseLikes(const Tutorial&);

    void printCSVorHTML();

    void handleOpenFile();

};


#endif //A45_BORDIANURAUL_USERINTERFACE_H
