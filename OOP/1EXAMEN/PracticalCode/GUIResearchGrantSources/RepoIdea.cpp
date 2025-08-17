#include "RepoIdea.h"

RepoIdea::RepoIdea(string filename) : filename(filename)
{

	ifstream fileIn(filename);

	Idea idea;

	while (fileIn >> idea)
	{
		this->ideas.push_back(idea);
	}

	fileIn.close();
}

vector<Idea> RepoIdea::getIdeas()
{
	return ideas;
}

void RepoIdea::addIdea(Idea idea)
{
	this->ideas.push_back(idea);
}
