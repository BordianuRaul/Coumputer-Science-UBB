#include "SMIterator.h"
#include "SortedMap.h"
#include <exception>
using namespace std;

SortedMap::SortedMap(Relation r) {

    this->data = new TElem[1];
    this->nrElements = 0;
    this->capacity = 1;
    this->relation = r;

}

TValue SortedMap::add(TKey k, TValue v) {


    int index = 0;

    while(index < this->nrElements)
    {
        if(this->data[index].first == k)
        {
            TValue oldValue = this->data[index].second;
            this->data[index].second = v;
            return oldValue;
        }
        index++;
    }

    if(this->nrElements == this->capacity - 1)
    {
        auto* newData = new TElem[this->capacity * 2];

        for(int i = 0; i < this->nrElements; i++)
        {
            newData[i].first = this->data[i].first;
            newData[i].second = this->data[i].second;
        }

        this->capacity *= 2;
        delete[] this->data;

        this->data = newData;
    }

    index = this->nrElements - 1;

    while(index >= 0)
    {
        if(!this->relation(this->data[index].first, k)) {
            this->data[index + 1].first = this->data[index].first;
            this->data[index + 1].second = this->data[index].second;
        }
        else{
            break;
        }
        index--;
    }

    this->data[index + 1].first = k;
    this->data[index + 1].second = v;

    this->nrElements++;

	return NULL_TVALUE;
}

TValue SortedMap::search(TKey k) const {

    int index = 0;
    while(index < this->nrElements)
    {
        if(this->data[index].first == k)
            return this->data[index].second;
        index++;
    }
	return NULL_TVALUE;
}

TValue SortedMap::remove(TKey k) {

    if(this->search(k) == NULL_TVALUE)
	    return NULL_TVALUE;

    int index = 0;

    while(index < this->nrElements)
    {
        if(this->data[index].first == k)
            break;
        index++;
    }

    TValue deletedValue = this->data[index].second;

    for(; index < this->nrElements - 1; index++)
        this->data[index] = this->data[index + 1];

    this->nrElements--;
    return deletedValue;
}

int SortedMap::size() const {
	return this->nrElements;

}

bool SortedMap::isEmpty() const {

    if(this->nrElements == 0)
        return true;
	return false;
}

SMIterator SortedMap::iterator() const {
	return SMIterator(*this);
}

SortedMap::~SortedMap() {

    delete[] this->data;
}
