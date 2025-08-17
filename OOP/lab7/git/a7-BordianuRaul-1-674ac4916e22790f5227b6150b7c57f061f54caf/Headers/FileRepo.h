//
// Created by Raul on 5/1/2023.
//

#ifndef A7_BORDIANURAUL_1_FILEREPO_H
#define A7_BORDIANURAUL_1_FILEREPO_H


#include "Repository.h"
#include "Tutorial.h"

class FileRepo : public Repository<Tutorial> {
protected:

    std::string filename;

public:

    FileRepo();

    FileRepo(std::string);

    virtual ~FileRepo();

    virtual void addElem(Tutorial);

    std::string createStringTutorial(const Tutorial&);

    virtual void writeToFile();

    void loadFromFile();

    void clearFile();

    virtual void deleteRepo(Tutorial);

    FileRepo& operator=(const FileRepo&);

    const std::string &getFilename() const;

    virtual void openFile();

    virtual void writeToFileToOpen(std::vector<Tutorial>);



};


#endif //A7_BORDIANURAUL_1_FILEREPO_H
