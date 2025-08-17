#include "SortedBagIterator.h"
#include "SortedBag.h"
#include <exception>

using namespace std;

//0(1)
SortedBagIterator::SortedBagIterator(SortedBag& b) : bag(b) {

    this->first();
}
//0(1)
TComp SortedBagIterator::getCurrent() {

    if(!this->valid())
        throw exception();
    return this->current.value;

}

//0(1)
bool SortedBagIterator::valid() {


//    Node maxNode = this->bag.BST[0];
//
//    while(maxNode.rightChild != -1)
//        maxNode = this->bag.BST[maxNode.rightChild];
//
//    if(this->current.frequency > 1)
//        return true;
//
//    if(current.value == maxNode.value)
//        return false;
//
//    return true;

    if(this->current.value == NULL_TCOMP)
        return false;
    else return true;

}

//Worst case: O(n)
//Best case: O(1)
//Average case: O(n)
void SortedBagIterator::next() {

    if(!this->valid())
        throw exception();

    if(this->current.frequency > 1) {
        this->current.frequency--;
        return;
    }
    int i = 0;
    Node next;
    while(i < this->bag.firstEmpty && this->bag.relation(this->bag.BST[i].value, this->current.value))
        i++;

    next = this->bag.BST[i];

    while(i < this->bag.firstEmpty) {
        if (this->bag.relation(this->bag.BST[i].value, next.value) && this->bag.relation(this->current.value, this->bag.BST[i].value)
        && this->bag.BST[i].value != current.value)
            next = this->bag.BST[i];
        i++;
    }

    if(i == this->bag.firstEmpty)
        this->current = this->bag.BST[i];
    this->current = next;


}

//Worst case: O(n)
//Best case: O(1)
//Average case: O(n)
void SortedBagIterator::first() {

    this->current = this->bag.BST[0];

    while(current.leftChild != -1 && this->bag.relation(this->bag.BST[current.leftChild].value, current.value))
        current = this->bag.BST[current.leftChild];

}

//Worst case: O(n^2)
//Best case: O(n)
//Average case: O(n^2)

TComp SortedBagIterator::removeCurrent() {


    TComp currentValue = this->current.value;

    if(this->valid())
        this->next();
    else throw exception();

    this->bag.remove(currentValue);
    return currentValue;
}

