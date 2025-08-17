#include "SMIterator.h"
#include "SortedMap.h"
#include <exception>

using namespace std;

SMIterator::SMIterator(const SortedMap& m) : map(m){

    this->current = 0;

}

void SMIterator::first(){
	this->current = 0;
}

void SMIterator::next(){

    if(this->valid())
        this->current++;
    else throw exception();
}

bool SMIterator::valid() const{

    if(this->current < this->map.size()) return true;

	return false;
}

TElem SMIterator::getCurrent() const{

    if(this->valid())
    {
        return this->map.data[this->current];
    }
    else throw exception();
}

void SMIterator::jumpForward(int k) {

    if(this->current + k < this->map.size())
        this->current += k;
    else throw exception();

    //O(jumpForward) = 1 + O(map.size())
    //O(jumpForward) = 1 + 1
    //O(jumpForward) = 1


}