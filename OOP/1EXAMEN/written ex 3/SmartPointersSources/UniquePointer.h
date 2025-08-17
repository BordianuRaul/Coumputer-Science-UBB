#pragma once


template <typename T>
class UniquePointer
{
public:

	T* ptr;

public:

	UniquePointer(T*);

	~UniquePointer();

	//DELETE COPY CONSTRUCTOR
	UniquePointer(const UniquePointer<T>&) = delete;
	//DELETE OPERATOR=
	UniquePointer<T>& operator=(const UniquePointer<T>&) = delete;

	//OVERLOAD * AND ->

	T& operator*();

	T* operator->();

};

template <typename T>
UniquePointer<T>::UniquePointer(T* paramPtr)
{
	this->ptr = paramPtr;
}

template <typename T>
UniquePointer<T>::~UniquePointer()
{
	delete this->ptr;
}

template <typename T>
T& UniquePointer<T>::operator*()
{
	return *this->ptr;
}

template <typename T>
T* UniquePointer<T>::operator->()
{
	return this->ptr;
}