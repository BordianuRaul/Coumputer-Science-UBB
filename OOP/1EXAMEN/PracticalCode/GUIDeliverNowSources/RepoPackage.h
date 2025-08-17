#pragma once

#include "Package.h"
#include <vector>

class RepoPackage
{
private:
	vector<Package> packages;
	string filename;

public:
	RepoPackage(string filename);

	vector<Package> getUndeliveredPacks();

	void addPack(Package pack);

	vector<Package> getPacks();

	void changeStatus(Package package)
	{
		for (int i = 0; i < this->packages.size(); i++)
		{
			
			if (package.getRecipient() == packages[i].getRecipient() && package.getDeliveryStatus() == packages[i].getDeliveryStatus())
			{
				packages[i].changeStatus();
				break;
			}
		}
	}
};

