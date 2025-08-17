#include "ListIterator.h"
#include "SortedIteratedList.h"
#include <iostream>
using namespace std;
#include <exception>

//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
SortedIteratedList::SortedIteratedList(Relation r) : head(nullptr), relation(r) {}

//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
int SortedIteratedList::size() const {

    int count = 0;

    Node* current = this->head;

    while(current != nullptr)
    {
        count++;
        current = current->next;
    }

    return count;

}

//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
bool SortedIteratedList::isEmpty() const {

	if(this->head == nullptr)
        return true;
    return false;
}

//Best case: O(1)
//Worst case: O(1)
//Average case: Theta(1)
ListIterator SortedIteratedList::first() const {

    ListIterator it(*this);

    it.first();

	return it;
}

//Best case: O(1)
//Worst case: O(1)
//Average case: O(1)
TComp SortedIteratedList::getElement(ListIterator poz) const {

    if(poz.valid())
        return poz.getCurrent();
    throw exception();
}

//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
TComp SortedIteratedList::remove(ListIterator& poz) {


    if (!poz.valid()) {
        throw exception();
    }

    Node* nodeToRemove = poz.current;
    TComp removedElement = nodeToRemove->value;

    if (nodeToRemove == this->head) {

        this->head = this->head->next;

    } else {

        Node* prevNode = this->head;
        while (prevNode != nullptr && prevNode->next != nodeToRemove) {
            prevNode = prevNode->next;

        }

        if (prevNode == nullptr) {
            throw exception();
        }

        prevNode->next = nodeToRemove->next;
    }

    poz.current = nodeToRemove->next;
    delete nodeToRemove;
    return removedElement;
}

//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
ListIterator SortedIteratedList::search(TComp e) const{


    Node* current = this->head;
    ListIterator it(*this);
    it.first();

    while(current != nullptr)
    {
        if(current->value == e)
        {
            return it;
        }
        current = current->next;
        it.next();
    }

	return it;
}

//Best case: Theta(1),
//Worst case: Theta(n)
//Overall case: O(n)
void SortedIteratedList::add(TComp e) {

    Node* newNode = new Node(e);

    if (this->head == nullptr || this->relation(e, head->value)) {
        newNode->next = this->head;
        this->head = newNode;
    } else {
        Node* current = this->head;

        while (current->next != nullptr && this->relation(current->next->value, e)) {
            current = current->next;
        }
        newNode->next = current->next;
        current->next = newNode;
    }

}
//Best case: O(n)
//Worst case: O(n)
//Average case: Theta(n)
SortedIteratedList::~SortedIteratedList() {

    while(head != nullptr)
    {
        Node* current = this->head->next;
        delete head;
        head = current;

    }
}

//Best case: O(1) -> start == end
//Worst case: O(n) ->start = first element, ->end = last element
//Average case: O(n)
void SortedIteratedList::removeBetween(ListIterator start, ListIterator end) {

    if(!start.valid())
        throw exception();

    if(!end.valid())
        throw exception();

    while(start.getCurrentAddress() != end.getCurrentAddress())
    {
        ListIterator aux = start;
        start.next();
        this->remove(aux);
    }

    this->remove(end);


}
