//
// Created by Raul on 5/1/2023.
//

#include "FileRepo.h"
#include "RepositoryException.h"

#include <utility>
#include <fstream>
#include <iostream>
#include <exception>
#include <sstream>

using namespace std;

FileRepo::FileRepo() {

    this->filename = "basic.txt";

}

FileRepo::FileRepo(std::string filename)
{
    this->filename = filename;
    this->loadFromFile();
}

void FileRepo::addElem(Tutorial tutorial) {

    if(this->search(tutorial))
        throw DuplicateTutorialException();

    Repository<Tutorial>::addElem(tutorial);

    this->writeToFile();
}

void FileRepo::writeToFile() {

    std::string path = "../" + this->filename;
    std::ofstream fileOut(path);

    if (!fileOut.is_open())
        throw FileException("File could nout be opened!\n");

    for(const auto& tutorial : this->elements) {

        if (fileOut.is_open()) {
            fileOut << tutorial;

        }
    }

    fileOut.close();

}

void FileRepo::loadFromFile()
{
    std::string path = "../" + this->filename;
    if(!path.empty()) {
        std::ifstream file(path);

        if(!file.good())
        {
            std::ofstream file(path);
            file.close();

            // reopen the file for input
            file.open(path);
        }

        if (file.peek() == std::ifstream::traits_type::eof())
            return;

        if(!file.is_open())
            throw FileException("Failed to open file.");

        Tutorial tutorial;
        while (file >> tutorial) {
            this->elements.push_back(tutorial);
        }

        file.close();
    }


}

void FileRepo::clearFile() {
    std::string path = "../" + this->filename;
    std::ofstream fileOut(path, std::ios::trunc);  // truncating file deletes its content

    if (!fileOut.is_open())
        throw FileException("Failed to open file.");

    fileOut.close();
}

void FileRepo::deleteRepo(Tutorial tutorial) {

    Repository<Tutorial>::deleteRepo(tutorial);

    this->writeToFile();

}

FileRepo& FileRepo::operator=(const FileRepo &rhs) {

    if(this != &rhs){
        this->elements =rhs.elements;
        this->filename = rhs.filename;
    }
    return *this;
}

const string &FileRepo::getFilename() const {
    return filename;
}

void FileRepo::openFile()
{

}

FileRepo::~FileRepo() {

    this->elements.clear();
    this->filename.clear();
}

void FileRepo::writeToFileToOpen(std::vector<Tutorial> watchList) {

}