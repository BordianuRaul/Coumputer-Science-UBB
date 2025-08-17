//
// Created by Raul on 5/8/2023.
//

#ifndef LAB7_TUTORIALVALIDATOR_H
#define LAB7_TUTORIALVALIDATOR_H

#include <string>
#include "Tutorial.h"

class TutorialException: std::exception
{
private:
    std::vector<std::string> errors;
public:
    TutorialException(std::vector<std::string>);

    const std::vector<std::string> &getErrors() const;

};

class TutorialValidator {

private:
    std::string errors;
public:

    void validate(const Tutorial&);

};


#endif //LAB7_TUTORIALVALIDATOR_H
