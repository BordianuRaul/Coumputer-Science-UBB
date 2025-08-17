#include "RepoResearcher.h"

RepoResearcher::RepoResearcher(string filename) : filename(filename)
{

	ifstream fileIn(filename);

	Researcher researcher;
	while (fileIn >> researcher)
	{
		this->researchers.push_back(researcher);
	}

	fileIn.close();

}

vector<Researcher> RepoResearcher::getResearchers()
{
	return researchers;
}
