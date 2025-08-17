//
// Created by Raul on 3/25/2023.
//

#include "../Headers/Tutorial.h"

#include <vector>
#include <iostream>
#include <sstream>


using namespace std;

Tutorial::Tutorial() {

    this->id = "";
    this->title = "";
    this->presenter = "";
    this->duration = "";
    this->nrLikes = -1;
    this->link = "";

}

Tutorial::Tutorial(const std::string &id, const std::string &title, const std::string &presenter,
                   const std::string &duration, int nrLikes, const std::string &link) {

    this->id = id;
    this->title = title;
    this->presenter = presenter;
    this->duration = duration;
    this->nrLikes = nrLikes;
    this->link = link;
}

Tutorial::~Tutorial() = default;

const std::string &Tutorial::getId() const {
    return id;
}

const std::string &Tutorial::getTitle() const {
    return title;
}

const std::string &Tutorial::getPresenter() const {
    return presenter;
}

const std::string &Tutorial::getDuration() const {
    return duration;
}

int Tutorial::getNrLikes() const {
    return nrLikes;
}

const std::string &Tutorial::getLink() const {
    return link;
}

bool Tutorial::operator==(const Tutorial &rhs) const {
    if(this->id == rhs.id || this->title == rhs.title)
        return true;
    return false;
}

bool Tutorial::operator!=(const Tutorial &rhs) const {
    return !(rhs == *this);
}

void Tutorial::increaseLikes()
{
    this->nrLikes++;
}

std::ostream &operator<<(ostream &os, const Tutorial &tutorial) {


    os << tutorial.id << "," << tutorial.title << "," << tutorial.presenter << "," << tutorial.duration << "," <<
    tutorial.nrLikes << "," << tutorial.link << "\n";

    return os;

}

std::istream& operator>>(std::istream& is, Tutorial& tutorial) {
    string line;
    getline(is, line);
    std::stringstream ss(line);
    std::string id, title, presenter, duration, nrLikesStr, link;

    std::getline(ss, id, ',');
    std::getline(ss, title, ',');
    std::getline(ss, presenter, ',');
    std::getline(ss, duration, ',');
    std::getline(ss, nrLikesStr, ',');
    std::getline(ss, link, '\n');

    if(nrLikesStr == "")
        return is;
    int nrLikes = std::stoi(nrLikesStr);
    Tutorial newTutorial(id, title, presenter, duration, nrLikes, link);

    tutorial = newTutorial;

    return is;
}

