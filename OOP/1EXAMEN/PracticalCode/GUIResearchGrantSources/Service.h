#pragma once

#include "RepoIdea.h"
#include "RepoResearcher.h"
#include "Subject.h"

class Service : public Subject
{
private:

	RepoIdea& repoIdea;
	RepoResearcher& repoResearcher;
public:

	Service(RepoIdea& repoIdea, RepoResearcher& repoResearcher);

	void addIdea(string title, string description, string status, string creator, int duration);

	vector<Idea> getIdeas();

};

