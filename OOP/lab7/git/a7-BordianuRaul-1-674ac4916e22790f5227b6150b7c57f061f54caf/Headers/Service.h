//
// Created by Raul on 3/28/2023.
//

#ifndef A45_BORDIANURAUL_SERVICE_H
#define A45_BORDIANURAUL_SERVICE_H

#include "FileRepo.h"
#include "Tutorial.h"
#include <exception>
#include <string>

class Service {

private:

    FileRepo* repo;
    std::vector<Tutorial> watchList;

public:

    /**
     * Constructor without parameters
     */
    Service();

    /**
     * Constructor with parameters
     */
    explicit Service(FileRepo*);

    /**
     * Destructor of the class
     */
    ~Service();

    /**
     * Adds a tutorial
     */
    void addTutorial(const std::string&, const std::string&, const std::string&, const std::string&, int,
                     const std::string&);

    /**
     * Deletes a tutorial
     */
    void deleteTutorial(const std::string&);

    /**
     * Updates a tutorial
     */
    void updateTutorial(const std::string&, const std::string&, const std::string&, const std::string&, int,
                        const std::string&);

    /**
     *Gets the number of tutorials that are currently in the repo
     */
    int getSize();

    /**
     * Gets a tutorial at a certain index
     */
    Tutorial getTutorial(int);

    /**
     * Assigment operator for the class
     */
    Service& operator=(const Service&);

    /**
     * Opens a link in the browser
     */
    void accessLink(const std::string&);

    /**
     * Filters the tutorials by the name of the presenter
     * @return List with the filtered tutorials
     */
    std::vector<Tutorial> filterByPresenter(const std::string&);

    /**
     * Adds a tutorial to the watch list
     */
    void addToWatchList(const Tutorial&);

    /*
     * Returns the watch list
     */
    std::vector<Tutorial> getWatchList();

    /**
     * deletes a tutorial from the watch list
     */
    void deleteWatchList(const Tutorial&);

    /**
     * Searches for a tutorial in the watch list
     * @param title :title of the tutorial that is searched
     * @return :the tutorial found if it exists, else throws and exception
     */
    Tutorial searchWatchList(const std::string& title);

    /**
     * Increases the nr of likes of a tutorial with 1
     * @param tutorial :tutorial which nr of likes will be increased
     */
    void increaseLikes(const Tutorial& tutorial);

    /**
     * Searches for a tutorial in the repo
     * @return :if it exists returns true, else returns false
     */
    bool searchTutorial(const std::string&, const std::string&);

    void repoVariant(int);

    void openFile();



};

#endif //A45_BORDIANURAUL_SERVICE_H
