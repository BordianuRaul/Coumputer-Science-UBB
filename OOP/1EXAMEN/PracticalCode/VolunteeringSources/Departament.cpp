#include "Departament.h"


Departament::Departament()
{
}

Departament::Departament(string name, string description) : name(name), description(description) {}

string Departament::getName()
{
	return this->name;
}

string Departament::getDescription()
{
	return this->description;
}


