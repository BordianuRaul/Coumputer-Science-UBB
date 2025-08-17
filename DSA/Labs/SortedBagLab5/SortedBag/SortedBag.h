#pragma once
//DO NOT INCLUDE SORTEDBAGITERATOR

//DO NOT CHANGE THIS PART
typedef int TComp;
typedef TComp TElem;
typedef bool(*Relation)(TComp, TComp);
const int NULL_TCOMP = -11111;

class SortedBagIterator;
class Node{
public:
    TComp value;
    int frequency;
    int leftChild;
    int rightChild;

    Node(){
        this->value = -11111;
        this->frequency = 0;
        this->rightChild = -1;
        this->leftChild = -1;
    }

    Node& operator=(const Node& other) {
        if (this == &other) {
            return *this;
        }

        this->value = other.value;
        this->frequency = other.frequency;
        this->leftChild = other.leftChild;
        this->rightChild = other.rightChild;

        return *this;
    }
};
class SortedBag {
	friend class SortedBagIterator;

private:


    Node* BST;
    int nrElements;
    int capacity;
    Relation  relation;
    int firstEmpty;


public:

    void resize();

	//constructor
	SortedBag(Relation r);

	//adds an element to the sorted bag
	void add(TComp e);

	//removes one occurence of an element from a sorted bag
	//returns true if an eleent was removed, false otherwise (if e was not part of the sorted bag)
	bool remove(TComp e);

	//checks if an element appearch is the sorted bag
	bool search(TComp e) const;

	//returns the number of occurrences for an element in the sorted bag
	int nrOccurrences(TComp e) const;

	//returns the number of elements from the sorted bag
	int size() const;

	//returns an iterator for this sorted bag
	SortedBagIterator iterator();

	//checks if the sorted bag is empty
	bool isEmpty() const;

	//destructor
	~SortedBag();
};