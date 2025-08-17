#pragma once

#include <vector>
#include "Departament.h"
#include <fstream>

class RepoDep
{

private:

	vector<Departament> deps;

public:

	RepoDep(string filename);

	vector<string> getDepartamentsNames();

	vector<Departament> getDeps();

	int getSize();

};

