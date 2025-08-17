#include "PriorityQueue.h"
#include <stdexcept>

/**
 *  Average theta(0)
 * @param r
 */

PriorityQueue::PriorityQueue(Relation r) {
    this->relation = r;
    this->capacity = 2;
    this->elements = new Node[capacity];
    for (int i = 0; i < capacity - 1; i++) {
        elements[i].next = i + 1;
    }
    elements[capacity - 1].next = -1;
    this->head = -1;
    this->firstEmpty = 0;
}

/**
 * Best case: Theta(1)
 * Worst case: O(n)
 * Average case: O(n)
 * @param e
 * @param p
 */
void PriorityQueue::push(TElem e, TPriority p) {

    if (firstEmpty == -1) {
        resize();
    }

    int newPosition = firstEmpty;
    firstEmpty = elements[firstEmpty].next;
    elements[newPosition].element = std::make_pair(e, p);

    if (isEmpty() || !relation(elements[head].element.second, p)) {
        elements[newPosition].next = head;
        head = newPosition;
    } else {

        int current = head;
        while (elements[current].next != -1 &&
               relation(elements[elements[current].next].element.second, p))
        {
            current = elements[current].next;

        }
        elements[newPosition].next = elements[current].next;
        elements[current].next = newPosition;
    }
}

// Average Theta(1)
Element PriorityQueue::top() const {
    if (isEmpty()) {
        throw std::exception();
    }
    return elements[head].element;
}

//Average Theta(1)
Element PriorityQueue::pop() {
    if (isEmpty()) {
        throw std::exception();
    }

    int oldHead = head;
    head = elements[head].next;
    elements[oldHead].next = firstEmpty;
    firstEmpty = oldHead;
    return elements[oldHead].element;
}

//Average Theta(0)
bool PriorityQueue::isEmpty() const {
    return head == -1;
}

//Best case: Theta(n)
//Worst case: Theta(n)
//Average case: Theta(n)
void PriorityQueue::resize() {
    Node* newElements = new Node[capacity * 2];
    for (int i = 0; i < capacity; i++) {
        newElements[i] = elements[i];
    }
    for (int i = capacity; i < capacity * 2 - 1; i++) {
        newElements[i].next = i + 1;
    }
    newElements[capacity * 2 - 1].next = -1;
    firstEmpty = capacity;
    capacity *= 2;
    delete[] elements;
    elements = newElements;
}

//Average Theta(0)

PriorityQueue::~PriorityQueue() {
    delete[] elements;
}
