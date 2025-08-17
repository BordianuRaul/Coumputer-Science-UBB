#include "RepoCourier.h"

RepoCourier::RepoCourier(string filename) : filename(filename)
{
	ifstream fileIn(filename);

	Courier courier;

	while (fileIn >> courier)
	{
		this->couriers.push_back(courier);
	}
}

vector<Courier> RepoCourier::getCouriers()
{
	return couriers;
}
