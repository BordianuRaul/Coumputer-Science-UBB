#include "Package.h"

Package::Package()
{
}

Package::Package(string recipient, pair<string, int> address, pair<int, int> location, bool deliveryStatus) : recipient(recipient), address(address),
location(location), deliveryStatus(deliveryStatus)
{
}

string Package::getRecipient()
{
	return recipient;
}

pair<string, int> Package::getAddress()
{
	return address;
}

pair<int, int> Package::getLocation()
{
	return location;
}

bool Package::getDeliveryStatus()
{
	return deliveryStatus;
}

string Package::toString()
{
	
	string stringPackage = this->recipient + " " + this->address.first + " nr" + to_string(this->address.second) +
		" " + to_string(this->location.first) + "-" + to_string(this->location.second);

	return stringPackage;

}

void Package::changeStatus()
{
	this->deliveryStatus = true;
}

ifstream& operator>>(ifstream& is, Package& package)
{
	string line;
	if (getline(is, line))
	{
		stringstream ss(line);

		getline(ss, package.recipient, ',');
		getline(ss, package.address.first, ',');

		string y;
		getline(ss, y, ',');
		package.address.second = stoi(y);

		string x;
		getline(ss, x, ',');
		getline(ss, y, ',');

		package.location.first = stoi(x);
		package.location.second = stoi(y);

		string status;
		getline(ss, status, '\n');

		if (status == "false")
			package.deliveryStatus = false;
		else package.deliveryStatus = true;

		

	}

	return is;
}
