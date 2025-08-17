#include "Researcher.h"

Researcher::Researcher()
{
}

Researcher::Researcher(string name, string position) : name(name), position(position)
{
}

string Researcher::getName()
{
	return name;
}

string Researcher::getPosition()
{
	return position;
}

ifstream& operator>>(ifstream& is, Researcher& researcher)
{
	
	string line;
	if (getline(is, line))
	{
		stringstream ss(line);

		getline(ss, researcher.name, ',');
		getline(ss, researcher.position, '\n');
	}

	return is;



}
