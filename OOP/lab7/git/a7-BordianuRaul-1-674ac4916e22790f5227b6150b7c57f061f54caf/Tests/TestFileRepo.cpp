//
// Created by Raul on 5/2/2023.
//

#include "TestFileRepo.h"
#include "FileRepo.h"
#include "HTMLRepo.h"
#include "CSVRepo.h"
#include "Service.h"

void testCreateRepo()
{
    FileRepo repo("test.txt");
}

void testAddFileRepo()
{
    FileRepo repo("test.txt");

    Tutorial tutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                      "https://www.youtube.com/watch?v=wA8LriKDBAI");

    repo.addElem(tutorial);

    assert(repo.getSize() == 1);

    repo.clearFile();
}


void testLoadFromFileRepo()
{
    FileRepo repo("testRead.txt");

    assert(repo.getSize() == 2);
}

void testDeleteFileRepo()
{
    FileRepo repo("test.txt");

    Tutorial tutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                      "https://www.youtube.com/watch?v=wA8LriKDBAI");

    repo.addElem(tutorial);

    assert(repo.getSize() == 1);

    repo.deleteRepo(tutorial);

    assert(repo.getSize() == 0);
}

void testHTMLRepo()
{
    HTMLRepo repo("test.txt");

    Tutorial tutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                      "https://www.youtube.com/watch?v=wA8LriKDBAI");
    repo.addElem(tutorial);

    repo.writeToFile();
}

void testCSVRepo()
{

    CSVRepo repo("test.txt");

    Tutorial tutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                      "https://www.youtube.com/watch?v=wA8LriKDBAI");
    repo.addElem(tutorial);

    repo.writeToFile();


}

void testRepoVariantHTML()
{

    FileRepo repo("test.txt");

    Service service(&repo);

    service.repoVariant(1);

}

void testRepoVariantCSV()
{
    FileRepo repo("test.txt");

    Service service(&repo);

    service.repoVariant(0);
}

void testAllFileRepo()
{
    testCreateRepo();
    testAddFileRepo();
    testLoadFromFileRepo();
    testDeleteFileRepo();
    testHTMLRepo();
    testCSVRepo();
    testRepoVariantHTML();
    testRepoVariantCSV();
}
