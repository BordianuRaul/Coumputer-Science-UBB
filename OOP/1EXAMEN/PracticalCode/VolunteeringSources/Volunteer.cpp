#include "Volunteer.h"

Volunteer::Volunteer()
{
}

Volunteer::Volunteer(string name, string email, vector<string> interests, string departament) :
	name(name), email(email), departament(departament)
{
	for (auto i : interests)
	{
		this->interests.push_back(i);
	}

}

string Volunteer::strInterests()
{
	string data;

	for (auto i : this->interests)
		data = data + i + " ";

	return data;
}


