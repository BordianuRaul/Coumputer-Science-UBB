#pragma once

using namespace std;
#include <vector>

template <typename T>
class Set
{
private:
	vector<T> data;


public:

	Set();

	Set& operator+(T elem);

    typename std::vector<T>::iterator begin() {
        return data.begin();
    }

    typename std::vector<T>::iterator end() {
        return data.end();
    }

};

template<typename T>
inline Set<T>::Set()
{



}

template<typename T>
Set<T>& Set<T>::operator+(T elem)
{

	for (auto it : this->data)
	{
		if (it == elem)
			throw exception();
	}

	this->data.push_back(elem);

	return *this;
}
