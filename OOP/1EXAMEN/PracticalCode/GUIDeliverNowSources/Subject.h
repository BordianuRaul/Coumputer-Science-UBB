#pragma once

#include <vector>
#include "Observer.h"

using namespace std;

class Subject
{
public:
	vector<Observer*> observers;

	void addObserver(Observer* observer);

	void notify();
};

