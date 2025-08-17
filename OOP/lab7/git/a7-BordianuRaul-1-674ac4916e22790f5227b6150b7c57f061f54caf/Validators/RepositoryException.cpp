//
// Created by Raul on 5/8/2023.
//

#include "RepositoryException.h"


FileException::FileException(const std::string & msg) : message(msg)
{
}

const char * FileException::what()
{
    return message.c_str();
}

RepositoryException::RepositoryException() : exception{}, message{""}
{
}

RepositoryException::RepositoryException(const std::string & msg): message{msg}
{
}

const char * RepositoryException::what()
{
    return this->message.c_str();
}

const char * DuplicateTutorialException::what()
{
    return "There is another tutorial with the same artist and title!";
}
