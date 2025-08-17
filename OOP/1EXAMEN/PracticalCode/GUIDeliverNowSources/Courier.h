#pragma once

#include <string>
#include <vector>
#include <sstream>
#include <fstream>
#include <utility>

using namespace std;

class Courier
{
private:

	string name;
	vector<string> streets;
	pair<int, int> zone;

public:

	Courier();

	Courier(string name, vector<string> streets, pair<int, int> zone);

	string getName();

	vector<string> getStreets();

	pair<int, int> getZone();

	friend ifstream& operator>>(ifstream& is, Courier& courier);

};

