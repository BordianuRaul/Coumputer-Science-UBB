#pragma once

#include <string>
#include <vector>
#include <fstream>
#include <sstream>
using namespace std;

class Volunteer
{
public:

	string name;
	string email;
	vector<string> interests;
	string departament;

public:

	Volunteer();

	Volunteer(string name, string email, vector<string> interests, string departament);

	friend ifstream& operator>>(ifstream& is, Volunteer& volunteer)
	{
		string line;

		if (getline(is, line))
		{
			stringstream ss(line);

			getline(ss, volunteer.name, ',');
			getline(ss, volunteer.email, ',');

			string subline;
			getline(ss, subline, ',');
			getline(ss, volunteer.departament, '\n');
			string aux;

			volunteer.interests.clear();

			stringstream ssSubline(subline);
			while (getline(ssSubline, aux, '|'))
			{
				volunteer.interests.push_back(aux);
			}
		}

		return is;
	}

	string strInterests();




};

