#include "ListIterator.h"
#include "SortedIteratedList.h"
#include <exception>

using namespace std;

//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
ListIterator::ListIterator(const SortedIteratedList& list) : list(list){
	this->current = nullptr;
}
//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
void ListIterator::first(){

    this->current = this->list.head;
}
//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
void ListIterator::next(){

    if(this->valid())
        this->current = current->next;
    else throw exception();
}
//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
bool ListIterator::valid() const{
	if(this->current != nullptr)
        return true;
	return false;
}
//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
TComp ListIterator::getCurrent() const{

    if(this->valid())
        return this->current->value;

    throw exception();
}

Node* ListIterator::getCurrentAddress()
{
    return this->current;
}


