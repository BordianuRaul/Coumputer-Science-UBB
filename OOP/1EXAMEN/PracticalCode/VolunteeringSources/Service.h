#pragma once

#include "RepoDep.h"
#include "RepoVol.h"
#include "Observer.h"

class Service
{
private:

	RepoDep* repoDep;
	RepoVol* repoVol;
	vector<Observer*> observers;

public:
	
	Service();

	void addObserver(Observer*);

	void notifyObservers()
	{
		for (Observer* observer : observers)
		{
			observer->update();
		}
	}


	Service(RepoDep*, RepoVol*);

	vector<Departament> getDepartaments();

	int getNrDeps();

	int getNrVols();

	vector<Volunteer> getVolunteers();

	void addVolunteer(string, string, vector<string>);

};

