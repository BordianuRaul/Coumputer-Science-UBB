#include "Courier.h"

Courier::Courier()
{
}

Courier::Courier(string name, vector<string> streets, pair<int, int> zone) : name(name), streets(streets), zone(zone)
{
}

string Courier::getName()
{
	return name;
}

vector<string> Courier::getStreets()
{
	return streets;
}

pair<int, int> Courier::getZone()
{
	return zone;
}

ifstream& operator>>(ifstream& is, Courier& courier)
{
	string line;
	if (getline(is, line))
	{
		stringstream ss(line);

		getline(ss, courier.name, ',');

		string streets;

		getline(ss, streets, ',');

		stringstream street_stream(streets);

		string street;
		vector<string> auxStreets;
		while (getline(street_stream, street, '|'))
		{
			auxStreets.push_back(street);
		}
		courier.streets = auxStreets;

		string x, y;

		getline(ss, x, ',');
		getline(ss, y, '\n');
		courier.zone.first = stoi(x);
		courier.zone.second = stoi(y);
	}

	return is;
}
