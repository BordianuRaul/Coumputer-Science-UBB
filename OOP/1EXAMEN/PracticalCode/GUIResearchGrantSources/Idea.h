#pragma once

#include <string>
#include <sstream>
#include <fstream>

using namespace std;

class Idea
{
private:

	string title;
	string description;
	string status;
	string creator;
	int duration;

public:

	Idea();

	Idea(string title, string description, string status, string creator, int duration);

	string getTitle();

	string getDescription();
	
	string getStatus();

	string getCreator();

	int getDuration();

	friend ifstream& operator>>(ifstream& is, Idea& idea);
};

