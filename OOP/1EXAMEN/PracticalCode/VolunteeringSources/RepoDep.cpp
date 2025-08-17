#include "RepoDep.h"

RepoDep::RepoDep(string filename)
{

	ifstream inputFile(filename);

	Departament dep;

	while (inputFile >> dep)
	{
		this->deps.push_back(dep);
	}

	inputFile.close();

}

vector<string> RepoDep::getDepartamentsNames()
{
	vector<string> names;

	for (auto d : this->deps)
	{
		names.push_back(d.getName());
	}

	return names;
}

vector<Departament> RepoDep::getDeps()
{
	return this->deps;
}

int RepoDep::getSize()
{
	return this->deps.size();
}
