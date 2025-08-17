#pragma once

using namespace std;

template <typename T>
class SharedPointer
{
public:

	T* data;

public:

	SharedPointer(T*);

	~SharedPointer();

	T& operator*();

	T* operator->();

	bool operator==(const SharedPointer<T>& other) const {
		return this->data == other.data;
	}

};

template <typename T>
SharedPointer<T>::SharedPointer(T* ptr)
{
	this->data = ptr;
}

template <typename T>
SharedPointer<T>::~SharedPointer()
{
	delete this->data;
}

template <typename T>
T* SharedPointer<T>::operator->()
{
	return this->data;
}

template <typename T>
T& SharedPointer<T>::operator*()
{
	return *this->data;
}
