//
// Created by Raul on 5/3/2023.
//

#ifndef A7_BORDIANURAUL_1_HTMLREPO_H
#define A7_BORDIANURAUL_1_HTMLREPO_H


#include "FileRepo.h"

class HTMLRepo: public FileRepo{
private:

    std::string htmlFile;

public:
    HTMLRepo(std::string);

    void writeToFileToOpen(std::vector<Tutorial>);

    virtual void openFile();

    ~HTMLRepo();

};


#endif //A7_BORDIANURAUL_1_HTMLREPO_H
