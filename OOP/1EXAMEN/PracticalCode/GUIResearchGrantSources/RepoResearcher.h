#pragma once

#include "Researcher.h"
#include <vector>

class RepoResearcher
{

private:

	vector<Researcher> researchers;
	string filename;

public:

	RepoResearcher(string filename);

	vector<Researcher> getResearchers();

};

