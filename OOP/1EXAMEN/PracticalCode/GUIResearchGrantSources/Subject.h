#pragma once

#include "Observer.h"
#include <vector>

using namespace std;

class Subject
{
protected:

	vector<Observer*> observers;

public:
	void addObserver(Observer*);

	void notify();

};

