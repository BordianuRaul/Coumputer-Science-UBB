#include "Service.h"

Service::Service()
{
	this->repoDep = new RepoDep("filename.txt");
	this->repoVol = new RepoVol("filename2.txt");
}

void Service::addObserver(Observer* observer)
{
	this->observers.push_back(observer);
}

Service::Service(RepoDep* deps, RepoVol* volunteers)
{

	this->repoDep = deps;
	this->repoVol = volunteers;

}

vector<Departament> Service::getDepartaments()
{
	return this->repoDep->getDeps();
}

int Service::getNrDeps()
{
	return this->repoDep->getSize();
}

int Service::getNrVols()
{
	return this->repoVol->getSize();
}

vector<Volunteer> Service::getVolunteers()
{
	return this->repoVol->getVolunteers();
}

void Service::addVolunteer(string name, string email, vector<string> preferences)
{

	Volunteer volunteer(name, email, preferences, "");

	this->repoVol->add(volunteer);
	this->notifyObservers();
}
