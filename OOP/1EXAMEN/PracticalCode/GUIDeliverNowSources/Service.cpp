#include "Service.h"

Service::Service(RepoCourier& repoCourier, RepoPackage& repoPackage) : repoPackage(repoPackage), repoCourier(repoCourier)
{
	
}

vector<Courier> Service::getCouriers()
{
	return this->repoCourier.getCouriers();
}

vector<Package> Service::getPacksForCourier(Courier courier)
{
	vector<Package> found;
	auto undelivered = this->repoPackage.getUndeliveredPacks();

	for (auto p : undelivered)
	{
		for (auto s : courier.getStreets())
		{
			if (s == p.getAddress().first)
			{
				found.push_back(p);
			}
		}
	}

	return found;
}

Courier Service::getCourierByName(string name)
{
	for(auto c : this->repoCourier.getCouriers())
	{
		if (c.getName() == name)
			return c;
	}
}

void Service::addPack(string recipient, pair<string, int> street, pair<int, int> location, bool status)
{

	Package pack(recipient, street, location, status);
	this->repoPackage.addPack(pack);

}

vector<Package> Service::getAllPacks()
{
	return this->repoPackage.getPacks();
}

vector<string> Service::getAllStreets()
{
	vector<string> found;

	auto packs = this->repoPackage.getPacks();

	for (auto p : packs)
	{
		bool ok = false;
		for (auto f : found)
		{
			if (f == p.getAddress().first)
			{
				ok = true;
				break;
			}
		}

		if (!ok) found.push_back(p.getAddress().first);
	}
	return found;
}

void Service::changeStatus(Package package)
{
	this->repoPackage.changeStatus(package);
}
