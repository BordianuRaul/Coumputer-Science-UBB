#pragma once

template <typename T>
class Node
{
public:
	T data;
    Node<T>* next;

public:
    Node()
    {
        this->next = nullptr;
    }
};

template <typename T>
class LinkedList
{
private:

    Node<T>* head;

public:
	LinkedList(Node<T>* f = nullptr)
    {
        this->head = f;
    }
	LinkedList(const LinkedList& l)
    {
        this->head = l.head;
    }
	~LinkedList()
    {
        while(head != nullptr)
        {
            Node<T>* current = this->head->next;
            delete head;
            head = current;

        }

    }
		
	// assignment operator for a LinkedList
	LinkedList& operator=(const LinkedList& l)
    {

        if(this == &l)
            return *this;

        Node<T>* current = l.head;
        while(current != nullptr)
        {
            this->head = new Node<T>;
            this->head->data = current->data;
            this->head = this->head->next;
            current = current->next;
        }
        return *this;

    }

	T& operator[](int pos)
    {
        Node<T>* current = this->head;
        int i = 0;

        while(current != nullptr)
        {
            if(i == pos)
                return current->data;
            i++;
            current = current->next;

        }
    }

	// Adds an element to the current LinkedList.
	void add(const T& e)
    {
        Node<T>* current = this->head;

        if(this->head == nullptr)
        {
            this->head = new Node<T>;
            this->head->data = e;
            return;
        }

        while (current->next != nullptr)
        {
            current = current->next;
        }

        auto* newNode = new Node<T>;
        newNode->data = e;

        current->next = newNode;

    }

	int getSize() const
    {
        int size = 0;

        Node<T>* current = this->head;

        while(current != nullptr)
        {
            size++;
            current = current->next;
        }
        return size;
    }
};
