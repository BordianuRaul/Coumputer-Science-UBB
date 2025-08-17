#include "RepoVol.h"



RepoVol::RepoVol(string filename)
{

	ifstream fileIn(filename);

	Volunteer volunteer;
	while (fileIn >> volunteer)
	{
		this->volunteers.push_back(volunteer);
	}

}

int RepoVol::getSize()
{
	return this->volunteers.size();
}

vector<Volunteer> RepoVol::getVolunteers()
{	
	
	sort(this->volunteers.begin(), this->volunteers.end(),
		[](const Volunteer& a, const Volunteer& b) {
			return a.name < b.name;
		});
	return this->volunteers;

}

void RepoVol::add(Volunteer volunteer)
{
	this->volunteers.push_back(volunteer);
}
