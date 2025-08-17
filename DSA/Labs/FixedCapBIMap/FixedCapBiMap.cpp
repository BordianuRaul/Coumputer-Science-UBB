#include "FixedCapBiMap.h"
#include "FixedCapBiMapIterator.h"
#include <exception>

using namespace std;


FixedCapBiMap::FixedCapBiMap(int capacity) {

    if(capacity <= 0)
        throw exception();

    this->elements = new TElem[capacity];
    this->capacity = capacity;
    this->nrElements = 0;

}

FixedCapBiMap::~FixedCapBiMap() {

    delete[] this->elements;
}

bool FixedCapBiMap::add(TKey c, TValue v){


    if(this->nrElements == this->capacity)
        throw exception();

    int count = 0;
    int index = 0;

    while(count < 2 && index < this->nrElements){

        if(this->elements[index].first == c){
            count++;
        }

        index++;

    }

    if(count == 2){
        return false;
    }
    else{

        this->elements[this->nrElements].first = c;
        this->elements[this->nrElements].second = v;
        this->nrElements++;
        return true;
    }


}

ValuePair FixedCapBiMap::search(TKey c) const{


    std::pair<TValue, TValue> foundElement;
    foundElement.first = NULL_TVALUE;
    foundElement.second = NULL_TVALUE;
    int i = 0;
    while(i < this->nrElements)
    {
        if(this->elements[i].first == c){
            
            if(foundElement.first == NULL_TVALUE) foundElement.first = this->elements[i].second;
            else {
                foundElement.second = this->elements[i].second;
                return foundElement;
            }
        }

        i++;
    }

	return foundElement;
}

bool FixedCapBiMap::remove(TKey c, TValue v){

    int i = 0;
    while(i < this->nrElements)
    {
        if(this->elements[i].first == c && this->elements[i].second == v) break;
        else i++;
    }

    if(i == this->nrElements) return false;
    else{
        this->elements[i] = this->elements[this->nrElements - 1];
        this->nrElements--;
        return true;
    }

}


int FixedCapBiMap::size() const {

	return this->nrElements;
}

bool FixedCapBiMap::isEmpty() const{

	return this->nrElements == 0;
}

bool FixedCapBiMap::isFull() const {

    return this->nrElements == this->capacity;

}

FixedCapBiMapIterator FixedCapBiMap::iterator() const {
	return FixedCapBiMapIterator(*this);
}



