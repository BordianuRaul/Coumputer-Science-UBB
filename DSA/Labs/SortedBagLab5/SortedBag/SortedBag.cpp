#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <utility>



//Average = Worst = Best case = O(n)
void SortedBag::resize() {

    this->capacity *= 2;

    auto newBST = new Node[this->capacity];

    for(int i = 0; i < this->capacity / 2; i++)
    {
        newBST[i] = this->BST[i];
    }

    delete[] this->BST;

    this->BST = newBST;

}

//0(1)
SortedBag::SortedBag(Relation r) {

    this->nrElements = 0;
    this->capacity = 2;
    this->BST = new Node[this->capacity];
    this->relation = r;
    this->firstEmpty = 0;
}

//Worst case: O(n)
//Best case: O(1)
//Average case: O(log(n))
void SortedBag::add(TComp e) {


    if(this->firstEmpty == this->capacity)
        this->resize();

    if(this->BST[0].value == NULL_TCOMP || this->BST[0].value == e)
    {
        this->BST[0].value = e;
        if(this->BST[0].frequency == 0) this->firstEmpty++;
        this->BST[0].frequency++;
        this->nrElements++;

    }
    else
    {

        Node current = this->BST[0];
        int location = 0;
        while(true) {

            while (this->relation(e, current.value) && current.leftChild != -1) {

                if (this->BST[current.leftChild].value == e) {
                    this->BST[current.leftChild].frequency++;
                    this->nrElements++;
                    return;
                }

                location = current.leftChild;
                current = this->BST[current.leftChild];


            }

            if(this->BST[current.rightChild].value == e && current.rightChild != -1)
            {
                this->BST[current.rightChild].frequency++;
                this->nrElements++;
                return;
            }


            if(this->relation(e, current.value) && current.leftChild == -1)
            {
                Node newNode;
                newNode.value = e;
                newNode.frequency = 1;

                this->BST[location].leftChild = this->firstEmpty;
                this->BST[this->firstEmpty] = newNode;
                this->nrElements++;
                this->firstEmpty++;
                return;
            }
            else
            {
                if(!this->relation(e, current.value) && current.rightChild == -1)
                {
                    Node newNode;
                    newNode.value = e;
                    newNode.frequency = 1;

                    this->BST[location].rightChild = this->firstEmpty;
                    this->BST[this->firstEmpty] = newNode;
                    this->nrElements++;
                    this->firstEmpty++;

                    return;
                }
            }

            location = current.rightChild;
            current = this->BST[current.rightChild];



        }


    }
}

//Worst case: O(n^2)
//Best case: O(1)
//Average case: O(nlog(n))
bool SortedBag::remove(TComp e) {

    if(!this->search(e))
        return false;

    std::pair<int,int> values[this->capacity];

    int j = 0;
    int n = 0;

    for(int i = 0; i < this->firstEmpty; i++)
    {
        if(this->BST[i].value == e && this->BST[i].frequency > 1)
        {
            values[j].first = e;
            values[j].second = this->BST[i].frequency - 1;
            j++;
            n = this->firstEmpty;
        }

        if(this->BST[i].value != e)
        {
            values[j].first = this->BST[i].value;
            values[j].second = this->BST[i].frequency;
            j++;
        }

    }

    if(n == 0) n = this->firstEmpty - 1;

    delete [] this->BST;
    this->nrElements = 0;
    this->firstEmpty = 0;

    this->BST = new Node[this->capacity];

    for(int i = 0; i < n; i++)
    {
        while(values[i].second > 0)
        {
            this->add(values[i].first);
            values[i].second--;
        }
    }

    return true;

}

//Worst: O(n)
//Best: O(1)
//Average: O(log(n))
bool SortedBag::search(TComp elem) const {


    Node current = this->BST[0];

    while(current.leftChild != -1 || current.rightChild != -1)
    {
        if(current.value == elem)
            return true;

        if(this->relation(elem, current.value))
        {
            if(current.leftChild == -1) return false;

            current = this->BST[current.leftChild];
        }else{
            if(current.rightChild == -1) return false;
            current = this->BST[current.rightChild];
        }
    }

    if(current.value == elem)
        return true;

	return false;
}

//Worst: O(n)
//Best: O(1)
//Average: O(log(n))
int SortedBag::nrOccurrences(TComp elem) const {
	if(!this->search(elem))
	    return 0;

    Node current = this->BST[0];

    while(current.leftChild != -1 || current.rightChild != -1)
    {
        if(current.value == elem)
            return current.frequency;

        if(this->relation(elem, current.value))
        {
            current = this->BST[current.leftChild];
        }else{

            current = this->BST[current.rightChild];
        }
    }

    if(current.value == elem)
        return current.frequency;
}


//0(1)
int SortedBag::size() const {

    return this->nrElements;
}

//0(1)
bool SortedBag::isEmpty() const {

    return this->nrElements == 0;
}

//0(1)
SortedBagIterator SortedBag::iterator(){
	return SortedBagIterator(*this);
}

//0(1)
SortedBag::~SortedBag() {

    delete[] this->BST;
}
