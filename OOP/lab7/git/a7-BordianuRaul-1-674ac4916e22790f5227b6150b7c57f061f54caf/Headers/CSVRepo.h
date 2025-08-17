//
// Created by Raul on 5/3/2023.
//

#ifndef A7_BORDIANURAUL_1_CSVREPO_H
#define A7_BORDIANURAUL_1_CSVREPO_H


#include "FileRepo.h"

using namespace std;

class CSVRepo : public FileRepo {

private:

    string csvFile;
public:

    CSVRepo(std::string);

    virtual void openFile();

    void writeToFileToOpen(std::vector<Tutorial>);

    ~CSVRepo();

};


#endif //A7_BORDIANURAUL_1_CSVREPO_H
