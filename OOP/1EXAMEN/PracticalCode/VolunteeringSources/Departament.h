#pragma once

#include <string>
#include <sstream>
using namespace std;

class Departament
{
private:
	
	string name;
	string description;

public:

	Departament();

	Departament(string, string);

	string getName();

	string getDescription();

	friend istream& operator>>(istream& is, Departament& dep)
	{
		string line;

		if (getline(is, line))
		{

			stringstream ss(line);

			getline(ss, dep.name, ',');
			getline(ss, dep.description, '\n');


		}

		return is;
	}
};

