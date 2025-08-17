#include "FixedCapBiMap.h"
#include "FixedCapBiMapIterator.h"
#include <exception>
using namespace std;


FixedCapBiMapIterator::FixedCapBiMapIterator(const FixedCapBiMap& d) : map(d)
{

    this->currentPos = 0;

}


void FixedCapBiMapIterator::first() {

    this->currentPos = 0;
}


void FixedCapBiMapIterator::next() {

    if(this->valid())
        this->currentPos++;

    else throw exception();

}


TElem FixedCapBiMapIterator::getCurrent(){

    if(this->valid()) return this->map.elements[this->currentPos];
    else throw exception();
}


bool FixedCapBiMapIterator::valid() const {

    return this->currentPos < this->map.nrElements;

}

void FixedCapBiMapIterator::updateCurrent(TValue v){

    if(this->valid())
        this->map.elements[this->currentPos].second = v;
    else throw exception();
}