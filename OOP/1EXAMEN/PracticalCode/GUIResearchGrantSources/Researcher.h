#pragma once

#include <string>
#include <sstream>
#include <fstream>


using namespace std;

class Researcher
{

private:

	string name;

	string position;

public:

	Researcher();

	Researcher(string name, string position);

	string getName();
	
	string getPosition();

	friend ifstream& operator>>(ifstream& is, Researcher& researcher);

};

