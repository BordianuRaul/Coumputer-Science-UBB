#pragma once

#include <string>
#include <utility>
#include <sstream>
#include <fstream>

using namespace std;

class Package
{
private:

	string recipient;
	pair<string, int> address;
	pair<int, int> location;
	bool deliveryStatus;

public:

	Package();
	Package(string recipient, pair<string, int> address, pair<int, int> location, bool deliveryStatus);

	string getRecipient();
	pair<string, int> getAddress();
	pair<int, int> getLocation();
	bool getDeliveryStatus();
	string toString();

	friend ifstream& operator>>(ifstream& is, Package& package);

	void changeStatus();
};

