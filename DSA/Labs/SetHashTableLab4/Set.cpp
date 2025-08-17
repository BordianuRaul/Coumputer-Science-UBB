#include "Set.h"
#include "SetITerator.h"
#include "exception"
#include <math.h>

// theta(1);
Set::Set() {
    cap = 2;
    nrElem = 0;
    elements = new Node*[cap];
    for (int i = 0; i < cap; i++) {
        elements[i] = nullptr;
    }
}
//theta(1);
int Set::hash(TElem element) const {
    return abs(element) % cap;
}

//theta(n)
void Set::resize() {
    cap *= 2;
    Node** newElements = new Node*[cap];
    for (int i = 0; i < cap; i++) {
        newElements[i] = nullptr;
    }

    for (int i = 0; i < cap / 2; i++) {
        Node* current = elements[i];

        while (current != nullptr) {

            Node* next = current->next;
            int newIndex = hash(current->value);
            current->next = newElements[newIndex];
            newElements[newIndex] = current;
            current = next;

        }
    }

    delete[] elements;
    elements = newElements;
}

//Best case: O(1);
//Worst case: O(n);
//Average case: O(1 + load factor);
bool Set::add(TElem element) {
    if (search(element)) {
        return false;
    }

    if (nrElem >= cap) {
        resize();
    }

    int index = hash(element);
    Node* newNode = new Node(element);
    newNode->next = elements[index];
    elements[index] = newNode;
    nrElem++;

    return true;
}

//Best case: O(1)
//Worst case: O(n)
//Average case: O(1 + load factor)
bool Set::remove(TElem element) {
    int index = hash(element);
    Node* current = elements[index];
    Node* prev = nullptr;

    while (current != nullptr) {
        if (current->value == element) {
            if (prev == nullptr) {
                elements[index] = current->next;
            } else {
                prev->next = current->next;
            }

            delete current;
            nrElem--;
            return true;
        }

        prev = current;
        current = current->next;
    }

    return false;
}
//Best case: O(1)
//Worst case: O(n)
//Average case: O(1 + load factor)
bool Set::search(TElem element) const {
    int index = hash(element);
    Node* current = elements[index];

    while (current != nullptr) {
        if (current->value == element) {
            return true; // element found
        }
        current = current->next;
    }

    return false; // element not found
}
//0(1)
int Set::size() const {
    return nrElem;
}
//0(1)
bool Set::isEmpty() const {
    return nrElem == 0;
}
//0(1)
SetIterator Set::iterator() const {
    return SetIterator(*this);
}

//O(n)
Set::~Set() {
    for (int i = 0; i < cap; i++) {
        Node* current = elements[i];
        while (current != nullptr) {
            Node* next = current->next;
            delete current;
            current = next;
        }
    }

    delete[] elements;
    elements = nullptr;
    nrElem = 0;
}


//Best: 0(n)
//Worst: 0(n)
//Average: O(n)
void Set::filter(Condition cond) {
    for (int i = 0; i < cap; i++) {
        Node* current = elements[i];
        Node* prev = nullptr;

        while (current != nullptr) {
            Node* next = current->next;
            if (!cond(current->value)) {
                if (prev == nullptr) {
                    elements[i] = next;
                } else {
                    prev->next = next;
                }

                delete current;
                nrElem--;
            } else {
                prev = current;
            }

            current = next;
        }
    }
}


