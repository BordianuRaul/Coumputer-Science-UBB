//
// Created by Raul on 5/3/2023.
//

#include <fstream>
#include "CSVRepo.h"

CSVRepo::CSVRepo(std::string filename) {

    this->filename = filename;
    this->csvFile = "WatchList.csv";

}

void CSVRepo::writeToFileToOpen(std::vector<Tutorial> watchList) {

    std::string path = "../" + this->csvFile;

    std::ofstream fileOut(path);
    if (this->getSize() != 0)
    {
        for (const auto& tutorial: watchList)
        {
            fileOut << tutorial;
        }
    }
    fileOut.close();

}

void CSVRepo::openFile()
{
    string path = "/mnt/c/Users/Raul/Desktop/OOP/Lab7/a7-BordianuRaul-1/WatchList.csv";
    string command = "gnome-terminal -- nano " + path;
    system(command.c_str());
}

CSVRepo::~CSVRepo() noexcept {}