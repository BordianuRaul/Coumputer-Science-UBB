#include <exception>
#include "SetIterator.h"
#include "Set.h"

//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
SetIterator::SetIterator(const Set& s) : set(s), current(0), currentNode(nullptr) {
    this->first();
}

//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
void SetIterator::first() {
    current = 0;
    while (current < set.cap && set.elements[current] == nullptr) {
        current++;
    }
    if (current < set.cap) {
        currentNode = set.elements[current];
    }
    else {
        currentNode = nullptr;
    }
}
//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
void SetIterator::next() {
    if (!valid()) {
        throw std::exception();
    }

    if (currentNode->next != nullptr) {
        currentNode = currentNode->next;
    }
    else {
        current++;
        while (current < set.cap && set.elements[current] == nullptr) {
            current++;
        }

        if (current < set.cap) {
            currentNode = set.elements[current];
        }
        else {
            currentNode = nullptr;
        }
    }
}
//O(1)
TElem SetIterator::getCurrent() {
    if (!valid()) {
        throw std::exception();
    }

    return currentNode->value;
}
//O(1)
bool SetIterator::valid() const {
    return currentNode != nullptr;
}
//Best case: O(1)
//Worst case: O(n)
//Average case: O(n)
void SetIterator::previous() {
    if (!valid()) {
        throw std::exception();
    }

    if (currentNode != set.elements[current]) {
        Node* prev = set.elements[current];
        while (prev->next != currentNode) {
            prev = prev->next;
        }
        currentNode = prev;
    }
    else {
        current--;
        while (current >= 0 && set.elements[current] == nullptr) {
            current--;
        }

        if (current >= 0) {
            Node* prev = set.elements[current];
            while (prev->next != nullptr) {
                prev = prev->next;
            }
            currentNode = prev;
        }
        else {
            currentNode = nullptr;
        }
    }
}


