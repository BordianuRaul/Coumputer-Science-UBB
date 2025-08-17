#pragma once

#include "Idea.h"
#include <vector>

class RepoIdea
{
private:

	vector<Idea> ideas;
	string filename;
public:

	RepoIdea(string filename);

	vector<Idea> getIdeas();

	void addIdea(Idea idea);
};

