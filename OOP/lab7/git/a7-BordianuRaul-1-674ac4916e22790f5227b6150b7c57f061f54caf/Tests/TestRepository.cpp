//
// Created by Raul on 3/26/2023.
//

#include "../Headers/TestRepository.h"



void testConstructorRepo(){

    Repository<Tutorial> repo;

}

void testAddRepo(){

    Repository<Tutorial> repo;

    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    repo.addElem(testTutorial);

    assert(repo.getSize() == 1);
}

void testDeleteRepo(){

    Repository<Tutorial> repo;

    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    Tutorial testTutorial1("2DEF", "Welcome to Python tutorial!", "Mosh", "60:05", 267000,
                           "https://www.youtube.com/watch?v=kqtD5dpn9C8");

    repo.addElem(testTutorial);
    repo.addElem(testTutorial1);

    assert(repo.getSize() == 2);

    repo.deleteRepo(testTutorial1);

    assert(repo.getSize() == 1);


}

void testReadRepo(){

    Repository<Tutorial> repo;

    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    Tutorial testTutorial1("2DEF", "Welcome to Python tutorial!", "Mosh", "60:05", 267000,
                           "https://www.youtube.com/watch?v=kqtD5dpn9C8");

    repo.addElem(testTutorial);
    repo.addElem(testTutorial1);

    assert(repo.read().at(0) == testTutorial);

}

void testSearchElement(){

    Repository<Tutorial> repo;

    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    Tutorial testTutorial1("2DEF", "Welcome to Python tutorial!", "Mosh", "60:05", 267000,
                           "https://www.youtube.com/watch?v=kqtD5dpn9C8");

    repo.addElem(testTutorial);

    assert(repo.search(testTutorial) == true);
    assert(repo.search(testTutorial1) == false);
}

void testAssigmentOperatorRepo()
{
    Repository<Tutorial> repo;

    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    Tutorial testTutorial1("2DEF", "Welcome to Python tutorial!", "Mosh", "60:05", 267000,
                           "https://www.youtube.com/watch?v=kqtD5dpn9C8");

    repo.addElem(testTutorial);

    Repository<Tutorial> copyRepo;

    copyRepo = repo;

    assert(repo.search(testTutorial) == copyRepo.search(testTutorial));
    assert(repo.search(testTutorial1) == copyRepo.search(testTutorial1));
}
void testAllRepository()
{
    testConstructorRepo();
    testAddRepo();
    testDeleteRepo();
    testReadRepo();
    testSearchElement();
    testAssigmentOperatorRepo();
}