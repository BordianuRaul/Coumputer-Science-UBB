#pragma once

#include "Courier.h"

class RepoCourier
{
private:
	vector<Courier> couriers;
	string filename;

public:

	RepoCourier(string filename);

	vector<Courier> getCouriers();
};

