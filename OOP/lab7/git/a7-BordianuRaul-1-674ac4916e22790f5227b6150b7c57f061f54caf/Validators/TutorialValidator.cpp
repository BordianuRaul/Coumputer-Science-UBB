//
// Created by Raul on 5/8/2023.
//

#include "TutorialValidator.h"

void TutorialValidator::validate(const Tutorial & tutorial) {

    std::vector<std::string> errors;

    if(tutorial.getNrLikes() < 0)
        errors.emplace_back("The number of likes can't be negative.\n");

    if(!errors.empty())
        throw TutorialException(errors);



}

TutorialException::TutorialException(std::vector<std::string> errors): errors{errors}{}

const std::vector<std::string> &TutorialException::getErrors() const {
    return this->errors;
}
