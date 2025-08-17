//
// Created by Raul on 3/26/2023.
//

#ifndef A45_BORDIANURAUL_REPOSITORY_H
#define A45_BORDIANURAUL_REPOSITORY_H

#include <vector>
#include <algorithm>

template<class T>
class Repository {
protected:
    std::vector<T> elements;
public:
    Repository();

    ~Repository();

    virtual void addElem(T elem);

    virtual void deleteRepo(T elem);

    int getSize();

    std::vector<T> read();

    bool search(T elem);

    Repository& operator=(const Repository<T>&);

};

template <class T>
/**
 * Initialises an repo
 * @tparam T: type of the element
 */
Repository<T>::Repository() = default;

template <class T>
/**
 * Destructor of the class
 * @tparam T: type of the element
 */
Repository<T>::~Repository<T>()
{
    this->elements.clear();
};

template <class T>
/**
 * Adds an element in the repo
 * @tparam T : type of the element
 * @param elem : element to be added
 */
void Repository<T>::addElem(T elem) {
    this->elements.push_back(elem);
}

template <class T>
/**
 * Deletes an element from the repo
 * @tparam T : type of the element
 * @param elem : element to be deleted
 */
void Repository<T>::deleteRepo(T elemToRemove) {

    this->elements.erase(std::remove_if(this->elements.begin(), this->elements.end(),
                                  [&](const T& elem){ return elem == elemToRemove; }),
                         this->elements.end());
}

template <class T>
/**
 * Returns the nr of elements from the repo
 * @tparam T :type of the elements
 * @return :nr of elements from the repo
 */
int Repository<T>::getSize() {
    return this->elements.size();
}

template <class T>
/**
 * Returns a list with the elements from the repo
 * @tparam T : type of the element
 * @return :list with the elements from the repo
 */
 std::vector<T> Repository<T>::read() {
    return this->elements;
}

template <class T>
/**
 * Searches an element in the repo
 * @tparam T : type of the elements
 * @param element : element that is searched
 * @return : true if the element is found, else false
 */
bool Repository<T>::search(T element) {


    auto it = std::find(this->elements.begin(), this->elements.end(), element);

    if(it != this->elements.end())
        return true;
    else return false;

}

template <class T>
/**
 * Assigment operator for the class
 * @tparam T :type of the elements
 * @param rhs :right hand side repo
 */
Repository<T>& Repository<T>::operator=(const Repository<T> &rhs){

    if(this != &rhs)
        this->elements =rhs.elements;
    return *this;
}



#endif //A45_BORDIANURAUL_REPOSITORY_H
