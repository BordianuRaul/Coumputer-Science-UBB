#pragma once

#include "RepoCourier.h"
#include "RepoPackage.h"
#include "Subject.h"
#include <algorithm>

class Service : public Subject
{
private:

	RepoCourier& repoCourier;
	RepoPackage& repoPackage;
public:

	Service(RepoCourier& repoCourier, RepoPackage& repoPackage);

	vector<Courier> getCouriers();
	vector<Package> getPacksForCourier(Courier courier);

	Courier getCourierByName(string name);

	void addPack(string recipient, pair<string, int> street, pair<int, int> location, bool status);

	vector<Package> getAllPacks();

	vector<string> getAllStreets();

	void changeStatus(Package package);
};

