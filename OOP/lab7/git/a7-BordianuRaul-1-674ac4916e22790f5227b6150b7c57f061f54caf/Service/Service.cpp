//
// Created by Raul on 3/28/2023.
//

#include "../Headers/Service.h"
#include "../Headers/RepositoryException.h"
#include "HTMLRepo.h"
#include "CSVRepo.h"
#include "TutorialValidator.h"
#include <cstdlib>
#include <algorithm>


Service::~Service()
{
    delete this->repo;
}

Service::Service()
{
    auto dummyRepo = new FileRepo("dummy.txt");

    this->repo = dummyRepo;

}

Service::Service(FileRepo* repo) {

    this->repo = repo;

}

Service& Service::operator=(const Service& other) {
    if (this == &other) {
        return *this;
    }

    delete this->repo;
    this->repo = new FileRepo(*other.repo);

    this->watchList = other.watchList;

    return *this;
}

void Service::addTutorial(const std::string& id, const std::string& title,const std::string& presenter,
                          const std::string& duration, int likes,const std::string& link)
{

    Tutorial tutorial(id, title, presenter, duration, likes, link);

    TutorialValidator validator;

    validator.validate(tutorial);

    this->repo->addElem(tutorial);

}

void Service::deleteTutorial(const std::string& title)
{
    std::vector<Tutorial> tutorials = this->repo->read();

    auto tutorialToDelete = std::find_if(tutorials.begin(), tutorials.end(), [&](const auto& tutorial) {
        return tutorial.getTitle() == title;
    });

    if (tutorialToDelete != tutorials.end()) {
        this->repo->deleteRepo(*tutorialToDelete);
        return;
    }

    throw RepositoryException("Invalid data, a tutorial with this data does not exists.\n");

}

void Service::updateTutorial(const std::string& id, const std::string& title,
                             const std::string& presenter,const std::string& duration, int nrLikes,
                             const std::string& link)
{

    Tutorial updatedTutorial(id, title, presenter, duration, nrLikes, link);

    std::vector<Tutorial> tutorials = this->repo->read();

    auto tutorialToUpdate = std::find_if(tutorials.begin(), tutorials.end(), [&](const auto& tutorial) {
        return tutorial.getId() == id;
    });

    if (tutorialToUpdate != tutorials.end()) {
        this->repo->deleteRepo(*tutorialToUpdate);
        this->repo->addElem(updatedTutorial);
        return;
    }

    throw RepositoryException("Invalid data, a tutorial with this data does not exists.\n");


}

int Service::getSize()
{
    return this->repo->getSize();
}

Tutorial Service::getTutorial(int index)
{

    if(index >= this->getSize())
        throw std::exception();

    return this->repo->read().at(index);
}

void Service::accessLink(const std::string& link)
{
    std::string url = "xdg-open "+  link;
    system(url.c_str());
}

std::vector<Tutorial> Service::filterByPresenter(const std::string& presenter) {

    std::vector<Tutorial> tutorials = this->repo->read();
    std::vector<Tutorial> filteredTutorials;

    if(presenter.empty())
        return tutorials;

    std::copy_if(tutorials.begin(), tutorials.end(), std::back_inserter(filteredTutorials), [&](const auto& tutorial) {
        return tutorial.getPresenter() == presenter;
    });

    return filteredTutorials;

}

void Service::addToWatchList(const Tutorial & tutorial) {

    auto it = std::find(this->watchList.begin(), this->watchList.end(), tutorial);

    if(it != this->watchList.end())
        throw RepositoryException("Invalid data, a tutorial with this data already exists.\n");

    this->watchList.push_back(tutorial);
}

std::vector<Tutorial> Service:: getWatchList()
{
    return this->watchList;
}

Tutorial Service::searchWatchList(const std::string& title)
{
    auto it = std::find_if(this->watchList.begin(), this->watchList.end(), [&](const auto& i) {
        return i.getTitle() == title;
    });

    if (it != this->watchList.end()) {
        return *it;
    }

    throw std::exception();
}

void Service::deleteWatchList(const Tutorial & tutorial) {



    this->watchList.erase(std::remove_if(this->watchList.begin(), this->watchList.end(),
                                        [&](const Tutorial& elem){ return elem == tutorial; }),
                         this->watchList.end());
}

void Service::increaseLikes(const Tutorial& tutorial)
{
    Tutorial updatedTutorial = tutorial;

    updatedTutorial.increaseLikes();

    this->repo->deleteRepo(tutorial);
    this->repo->addElem(updatedTutorial);
}

bool Service::searchTutorial(const std::string &id, const std::string &title) {

    std::vector<Tutorial> tutorials = this->repo->read();

    bool exists = std::any_of(tutorials.begin(), tutorials.end(),
                              [&](const auto& tutorial) {
                                  return tutorial.getId() == id && tutorial.getTitle() == title;
                              });
    return exists;

}

void Service::repoVariant(int variant) {

    vector<Tutorial> copyWatchList = this->watchList;
    vector<Tutorial> copyData = this->repo->read();

    if(variant == 1)
    {
        auto newRepo = new HTMLRepo {this->repo->getFilename()};

        this->repo = newRepo;

        for(auto &i: copyData)
        {
            this->repo->addElem(i);
        }
        this->watchList = copyWatchList;

        this->repo->writeToFileToOpen(this->watchList);
    }
    else
    {
        auto newRepo = new CSVRepo {this->repo->getFilename()};
        this->repo = newRepo;

        for(auto &i: copyData)
        {
            this->repo->addElem(i);
        }
        this->watchList = copyWatchList;

        this->repo->writeToFileToOpen(this->watchList);

    }

}

void Service::openFile() {

    this->repo->writeToFileToOpen(this->watchList);
    this->repo->openFile();

}
