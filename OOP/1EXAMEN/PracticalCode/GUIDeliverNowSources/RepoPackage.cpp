#include "RepoPackage.h"

RepoPackage::RepoPackage(string filename) : filename(filename)
{

	ifstream fileIn(filename);
	Package pack;

	while (fileIn >> pack)
	{
		this->packages.push_back(pack);
	}

}

vector<Package> RepoPackage::getUndeliveredPacks()
{
	vector<Package> undeliverd;

	for (auto p : packages)
	{
		if (!p.getDeliveryStatus())
		{
			undeliverd.push_back(p);
		}
	}

	return undeliverd;
}

void RepoPackage::addPack(Package pack)
{
	this->packages.push_back(pack);
}

vector<Package> RepoPackage::getPacks()
{
	return packages;
}
