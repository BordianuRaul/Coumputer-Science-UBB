//
// Created by Raul on 5/3/2023.
//

#include <fstream>
#include "HTMLRepo.h"

using namespace std;

HTMLRepo::HTMLRepo(std::string filename)
{
    this->filename = filename;
    this->htmlFile = "WatchList.html";
}

void HTMLRepo::writeToFileToOpen(std::vector<Tutorial> watchList) {

    string path = "../" + this->htmlFile;

    ofstream fileOut(path);
    fileOut << "<!DOCTYPE html>\n<html><head><title>WatchList</title></head><body>\n";
    fileOut << "<table border=\"1\">\n";
    fileOut << "<tr><td>ID</td><td>Title</td><td>Presenter</td><td>Duration</td><td>NrLikes</td><td>Link</td></tr>\n";
    for (const auto& tutorial : watchList)
    {
        fileOut << "<tr><td>" << tutorial.getId() << "</td><td>"
                << tutorial.getTitle() << "</td><td>"
                << tutorial.getPresenter() << "</td><td>"
                << tutorial.getDuration() <<"</td><td>"
                << to_string(tutorial.getNrLikes()) << "</td><td>"
                << "<a href=\"" << tutorial.getLink() << "\">" << tutorial.getLink() << "</a></td></tr>\n";
    }
    fileOut << "</table></body></html>";
    fileOut.close();
}

void HTMLRepo::openFile() {

    string path = "../WatchList.html";
    string command = "google-chrome " + path;
    system(command.c_str());

}

HTMLRepo::~HTMLRepo() noexcept {
}