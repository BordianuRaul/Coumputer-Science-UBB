//
// Created by Raul on 3/29/2023.
//

#include "../Headers/UserInterface.h"
#include "TutorialValidator.h"
#include "RepositoryException.h"
#include <iostream>
#include <limits>


using namespace std;

UserInterface::UserInterface(const Service& service)
{
    this->service = service;
}

void UserInterface::printAdministratorMenu()
{
    std::cout << "1.Add tutorial\n";
    std::cout << "2.Delete tutorial\n";
    std::cout << "3.Update tutorial\n";
    std::cout << "4.Print all tutorials\n";
    std::cout << "0.Exit\n";
}

void UserInterface::printMenu()
{
    std::cout << "1.Administrator menu.\n";
    std::cout << "2.User menu.\n";
    std::cout << "0.Exit.\n";
}

void UserInterface::printUserMenu()
{
    std::cout << "1.See the tutorials in the database having a given presenter.\n";
    std::cout << "2.See the watch list.\n";
    std::cout << "3.Delete tutorial from the watch list.\n";
    std::cout << "4.Open watchlist.\n";
    std::cout << "0.Exit.\n";
}

void UserInterface::printSlideShowMenu()
{
    std::cout << "1.Next\n";
    std::cout << "2.Add to watch list.\n";
    std::cout << "0.Exit.\n";
}

void UserInterface::handleAdd()
{

    std::string id;
    std::string title;
    std::string presenter;
    std::string duration;
    int nrLikes;
    std::string link;

    try
    {
        std::cout << "ID: ";
        std::cin >> id;
        std::cout << "\n";
        std::cin.ignore(256, '\n');

        std::cout << "Title: ";
        std::getline(std::cin, title);
        std::cout << "\n";

        std::cout << "Presenter: ";
        std::getline(std::cin, presenter);
        std::cout << "\n";

        std::cout << "Duration: ";
        std::getline(std::cin, duration);
        std::cout << "\n";

        std::cout << "Number of likes: ";
        std::cin >> nrLikes;
        std::cout << "\n";

        std::cin.ignore(256, '\n');

        std::cout << "Link: ";
        std::getline(std::cin, link);
        std::cout << "\n";

        if (std::cin.fail()) {
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            throw std::runtime_error("Invalid option.Number of likes must be an integer.\n");
        }
    } catch (std::runtime_error& e) {
        std::cout << e.what() << std::endl;
    }

    try
    {

        this->service.addTutorial(id, title, presenter, duration, nrLikes, link);
        std::cout << "Tutorial was added with success.\n";

    }
    catch(TutorialException& e){

        for(auto & error: e.getErrors())
        {
            cout << error << endl;
        }

    }
    catch(RepositoryException& e){
        cout << e.what() << endl;
    }
    catch(FileException& e){
        cout << e.what() << endl;
    }

}

void UserInterface::handleDelete()
{
    std::string title;

    try
    {
        std::cout << "Title of the tutorial to be deleted: ";
        std::cin >> title;
        std::cout << "\n";
        std::cin.ignore(256, '\n');

        if (std::cin.fail()) {
            throw std::runtime_error("Invalid option.\n");
        }
    } catch (std::runtime_error& e) {
        std::cout << e.what() << std::endl;
    }

    try
    {

        this->service.deleteTutorial(title);
        std::cout << "Tutorial was deleted with success.\n";

    }
    catch(RepositoryException& e){
        std::cout << e.what() << std::endl;
    }
}

void UserInterface::handleUpdate()
{
    std::string id;
    std::string title;
    std::string presenter;
    std::string duration;
    int nrLikes;
    std::string link;

    try
    {
        std::cout << "ID: ";
        std::cin >> id;
        std::cout << "\n";
        std::cin.ignore(256, '\n');

        std::cout << "Title: ";
        std::getline(std::cin, title);
        std::cout << "\n";


        if(!this->service.searchTutorial(id, title))
        {
            std::cout << "Invalid data, there is no existing tutorial with the id and title you mentioned.\n";
            return;
        }

        std::cout << " New title: ";
        std::getline(std::cin, title);
        std::cout << "\n";

        std::cout << "New presenter: ";
        std::getline(std::cin, presenter);
        std::cout << "\n";

        std::cout << "New duration: ";
        std::getline(std::cin, duration);
        std::cout << "\n";

        std::cout << "New number of likes: ";
        std::cin >> nrLikes;
        std::cout << "\n";

        std::cin.ignore(256, '\n');

        std::cout << "New link: ";
        std::getline(std::cin, link);
        std::cout << "\n";

        if (std::cin.fail()) {
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            throw std::runtime_error("Invalid option.Number of likes must be an integer.\n");
        }
    } catch (std::runtime_error& e) {
        std::cout << "Invalid data, a tutorial with this data does not exists.\n" << std::endl;
    }

    try
    {

        this->service.updateTutorial(id, title, presenter, duration, nrLikes, link);
        std::cout << "Tutorial was updated with success.\n";

    }
    catch(std::exception& e){
        std::cout << "Could not do the updated!" << std::endl;
    }
}

void UserInterface::printTutorial(const Tutorial& tutorial)
{
    std::cout << "ID: " << tutorial.getId() << "\n";
    std::cout << "Title: " << tutorial.getTitle() << "\n";
    std::cout << "Presenter: " << tutorial.getPresenter() << "\n";
    std::cout << "Duration: " << tutorial.getDuration() << "\n";
    std::cout << "Number of likes: " << tutorial.getNrLikes() << "\n";
    std::cout << "Link: " << tutorial.getLink() << "\n\n";
}

void UserInterface::handlePrintAll()
{

    int size = this->service.getSize();

    for(int i = 0; i < size; i++)
    {
        this->printTutorial(this->service.getTutorial(i));
    }

}

void UserInterface::handlePrintSlideShowTutorial(const Tutorial& tutorial)
{

    this->service.accessLink(tutorial.getLink());
    std::cout << "Title: " << tutorial.getTitle() << "\n";
    std::cout << "Presenter: " << tutorial.getPresenter() << "\n";
    std::cout << "Duration: " << tutorial.getDuration() << "\n";
    std::cout << "Number of likes: " << tutorial.getNrLikes() << "\n";
}

void UserInterface::increaseLikes(const Tutorial &tutorial) {

    this->service.increaseLikes(tutorial);
}

void UserInterface::deleteWatchList(const Tutorial &tutorial) {

    try {
        this->service.deleteWatchList(tutorial);
    }catch(RepositoryException& e)
    {
        cout << e.what() << endl;
    }
}

void UserInterface::handleDeleteWatchList()
{

    if(this->service.getWatchList().empty())
    {
        std::cout << "Watch list is empty.\n";
        return;
    }

    while(true) {


        int option;
        std::string title;

        try {

            std::cin.ignore(256, '\n');
            std::cout << "Title: ";
            std::getline(std::cin, title);
            std::cout << "\n";

            Tutorial tutorial = this->service.searchWatchList(title);

            std::cout << "Like the tutorial?\n";
            std::cout << "1.Yes.\n";
            std::cout << "2.No.\n";

            std::cout << "Select your option :";

            std::cin >> option;
            std::cout << std::endl;


            if (std::cin.fail()) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                throw std::runtime_error("");
            }

            if (option == 1) {
                this->increaseLikes(tutorial);
            }

            this->deleteWatchList(tutorial);

            return;


        }
        catch (std::runtime_error &e) {
            std::cout << "Invalid option\n" << std::endl;
            this->handleDeleteWatchList();
        }
        catch (RepositoryException &e){

            std::cout << e.what() << endl;

        }
    }
}

void UserInterface::handlePrintSlideShow(const std::vector<Tutorial>& tutorials,int index)
{

    while(true)
    {

        int option;

        try
        {

            this->handlePrintSlideShowTutorial(tutorials.at(index));
            if(index == tutorials.size() - 1)
                index = 0;
            else index++;

            this->printSlideShowMenu();

            std::cout << "Select your option :";
            std::cin >> option;
            std::cout << std::endl;

            if (std::cin.fail()) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                throw std::runtime_error("");
            }
        } catch (std::runtime_error& e) {
            std::cout << "Invalid option\n" << std::endl;
            this->handlePrintSlideShow(tutorials, index - 1);


        }


        switch(option)
        {
            case 1: break;

            case 2:{

                try {


                    this->service.addToWatchList(tutorials.at(index - 1));
                }
                catch(std::exception &e)
                {
                    std::cout << "This tutorial already exists in the watch list.\n" << std::endl;
                    this->handlePrintSlideShow(tutorials, index - 1);
                }
                break;
            }

            case 0: return;

            default:{
                std::cout << "Invalid option!\n";
                index --;
                break;
            }
        }
    }

}

void UserInterface::handleSlideShow()
{
    std::string presenter;

    std::cout << "Presenter: ";
    std::cin.ignore(256, '\n');
    std::getline(std::cin, presenter);
    std::cout << "\n";

    std::vector<Tutorial> tutorials = this->service.filterByPresenter(presenter);

    if(tutorials.empty())
    {
        std::cout << "There are no tutorials for the presenter: " << presenter << "\n";
        return;
    }

    this->handlePrintSlideShow(tutorials, 0);

}

void UserInterface::handleSeeWatchList()
{
    std::vector<Tutorial> watchList = this->service.getWatchList();

    for(auto & index : watchList)
        this->printTutorial(index);
}

void UserInterface::handleAdministratorMenu()
{
    while(true)
    {
        this->printAdministratorMenu();

        int option;

        try
        {
            std::cout << "Select your option :";
            std::cin >> option;
            std::cout << std::endl;

            if (std::cin.fail()) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                throw std::runtime_error("");
            }
        } catch (std::runtime_error& e) {
            std::cout << "Invalid option\n" << std::endl;
            this->handleAdministratorMenu();
        }

        switch(option)
        {
            case 1:
            {
                handleAdd();
                break;
            }

            case 2:
            {
                handleDelete();
                break;
            }
            case 3:
            {
                handleUpdate();
                break;

            }
            case 4:
            {
                handlePrintAll();
                break;
            }
            case 0:
                return;

            default:
                std::cout << "Invalid option.\n";

        }
    }
}



void UserInterface::handleOpenFile()
{
    this->service.openFile();
}

void UserInterface::handleUserMenu()
{

    this->printCSVorHTML();
    int variant;

    while (true) {

        std::cout << "Select your option :";
        std::cin >> variant;
        std::cout << std::endl;

        if (variant == 1) {
            this->service.repoVariant(1);
            break;
        } else if (variant == 2) {
            this->service.repoVariant(2);
            break;
        } else {
            cout << "Invalid option!" << endl;
        }

    }

    while(true)
    {
        this->printUserMenu();

        int option;

        try
        {

            std::cout << "Select your option :";
            std::cin >> option;
            std::cout << std::endl;

            if (std::cin.fail()) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                throw std::runtime_error("");
            }
        } catch (std::runtime_error& e) {
            std::cout << "Invalid option\n" << std::endl;
            this->handleUserMenu();
        }

        switch(option)
        {
            case 1:
            {
                this->handleSlideShow();
                break;
            }

            case 2:
            {
                this->handleSeeWatchList();
                break;
            }

            case 3:
            {
                this->handleDeleteWatchList();
                break;
            }

            case 4:
            {
                this->handleOpenFile();
                break;
            }

            case 0:
                return;

            default:
                std::cout << "Invalid option.\n";

        }
    }
}

void UserInterface::printCSVorHTML()
{
    cout << "1.Html\n";
    cout << "2.CSV\n";
}

void UserInterface::runUI()
{

    while(true)
    {
        this->printMenu();

        int option;

        try
        {
            std::cout << "Select your option :";
            std::cin >> option;
            std::cout << std::endl;

            if (std::cin.fail()) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                throw std::runtime_error("");
            }
        } catch (std::runtime_error& e) {
            std::cout << "Invalid option\n" << std::endl;
            this->runUI();
        }

        switch(option)
        {
            case 1:
            {
                handleAdministratorMenu();
                break;
            }

            case 2:
            {
                handleUserMenu();
                break;
            }

            case 0:
                return;

            default:
                std::cout << "Invalid option.\n";

        }
    }
}