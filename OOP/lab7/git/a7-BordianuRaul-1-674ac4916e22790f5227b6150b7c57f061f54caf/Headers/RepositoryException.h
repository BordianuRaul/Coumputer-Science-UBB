//
// Created by Raul on 5/8/2023.
//

#ifndef LAB7_REPOSITORYEXCEPTION_H
#define LAB7_REPOSITORYEXCEPTION_H

#include <exception>
#include <string>

using namespace std;

class FileException : public std::exception
{
protected:
    std::string message;
public:
    FileException(const std::string& msg);
    virtual const char* what();
};

class RepositoryException : public std::exception
{
protected:
    std::string message;

public:
    RepositoryException();
    RepositoryException(const std::string& msg);
    virtual ~RepositoryException() {}
    virtual const char* what();
};

class DuplicateTutorialException : public RepositoryException
{
    const char* what();
};


#endif //LAB7_REPOSITORYEXCEPTION_H
