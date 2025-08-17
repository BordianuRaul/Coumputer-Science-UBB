#include "Service.h"

Service::Service(RepoIdea& repoIdea, RepoResearcher& repoResearcher) : repoIdea(repoIdea), repoResearcher(repoResearcher)
{

}

void Service::addIdea(string title, string description, string status, string creator, int duration)
{
    Idea idea(title, description, status, creator, duration);

    this->repoIdea.addIdea(idea);
}

vector<Idea> Service::getIdeas()
{
    return this->repoIdea.getIdeas();
}
