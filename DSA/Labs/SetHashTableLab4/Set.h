#pragma once
//DO NOT INCLUDE SETITERATOR

//DO NOT CHANGE THIS PART
#define NULL_TELEM -111111
typedef int TElem;
typedef bool (*Condition)(TElem);

class SetIterator;

class Node {
public:
    TElem value;
    Node* next;

    Node(TElem value) : value(value), next(nullptr) {}
};

class Set {

	friend class SetIterator;

    private:

    Node** elements;
    int nrElem;
    int cap;
    int hash(TElem element) const;
    void resize();


    public:

        Set();

        bool add(TElem e);

        bool remove(TElem e);

        bool search(TElem elem) const;

        int size() const;

        bool isEmpty() const;

        SetIterator iterator() const;

        ~Set();

        void filter(Condition cond);



};





