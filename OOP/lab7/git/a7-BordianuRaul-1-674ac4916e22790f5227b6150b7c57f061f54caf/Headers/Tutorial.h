//
// Created by Raul on 3/25/2023.
//

#ifndef A45_BORDIANURAUL_TUTORIAL_H
#define A45_BORDIANURAUL_TUTORIAL_H

#pragma once
#include <string>
#include <cassert>
#include <ostream>
#include <vector>

class Tutorial {

private:

    std::string id;
    std::string title;
    std::string presenter;
    std::string duration; // 35:42
    int nrLikes;
    std::string link;

public:

    /**
     * Constructor without parameters
     */
    Tutorial();

    /**
     * Construcotr with parameters
     */
    Tutorial(const std::string&, const std::string&, const std::string&, const std::string&, int, const std::string&);

    /**
     * Destructor
     */
    ~Tutorial();

    const std::string &getId() const;

    const std::string &getTitle() const;

    const std::string &getPresenter() const;

    const std::string &getDuration() const;

    int getNrLikes() const;

    const std::string &getLink() const;

    /**
     * Overwrites operator ==
     * @param rhs : right hand side tutorial
     * @return : true if the ids are the same, false else
     */
    bool operator==(const Tutorial &rhs) const;

    /**
     * Overwrites operator <
     * @param rhs : right hand side tutorial
     * @return : true if this->nrLikes is less than rhs->nrLikes
     */
    bool operator<(const Tutorial &rhs) const;

    bool operator>(const Tutorial &rhs) const;

    bool operator!=(const Tutorial &rhs) const;

    /**
     * Increases the number of likes with 1
     */
    void increaseLikes();

    friend std::ostream &operator<<(std::ostream &os, const Tutorial &tutorial);

};

std::ostream& operator<<(std::ostream& os, const Tutorial& tutorial);

std::istream& operator>>(std::istream& is, Tutorial& tutorial);



#endif //A45_BORDIANURAUL_TUTORIAL_H
