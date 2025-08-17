#include "Idea.h"

Idea::Idea()
{
}

Idea::Idea(string title, string description, string status, string creator, int duration) : title(title), description(description), status(status), creator(creator),
duration(duration)
{
}

string Idea::getTitle()
{
	return title;
}

string Idea::getDescription()
{
	return description;
}

string Idea::getStatus()
{
	return status;
}

string Idea::getCreator()
{
	return creator;
}

int Idea::getDuration()
{
	return duration;
}

ifstream& operator>>(ifstream& is, Idea& idea)
{
	string line;

	if (getline(is, line))
	{
		stringstream ss(line);

		getline(ss, idea.title, ',');
		getline(ss, idea.description, ',');
		getline(ss, idea.status, ',');
		getline(ss, idea.creator, ',');

		string duration;

		getline(ss, duration, '\n');
		idea.duration = stoi(duration);
	}

	return is;
}
