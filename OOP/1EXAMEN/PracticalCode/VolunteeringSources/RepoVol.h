#pragma once

#include "Volunteer.h"
#include <fstream>
#include <algorithm>

class RepoVol
{
private:
	vector<Volunteer> volunteers;

public:

	RepoVol(string filename);

	int getSize();

	vector<Volunteer> getVolunteers();

	void add(Volunteer);
};

