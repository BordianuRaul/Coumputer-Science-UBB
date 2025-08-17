#include "Service.h"

Service::Service()
{
	ifstream inFile("data.txt");


	if (inFile.is_open())
	{
		string line;

		while (getline(inFile, line))
		{
			Document document = this->parseDocument(line);
			this->documents.push_back(document);
		}
	}
}

Document Service::parseDocument(string line)
{

	stringstream ss(line);

	string name, subLine, content;

	getline(ss, name, '|');
	getline(ss, subLine, '|');
	getline(ss, content, '\n');

	vector<string>keyWordsAux;
	string aux;

	stringstream ssSubLine(subLine);

	while (getline(ssSubLine, aux, ','))
	{
		keyWordsAux.push_back(aux);
	}

	return Document(name, keyWordsAux, content);

}

vector<Document> Service::getDocuments()
{
	return this->documents;
}

void Service::sortDocuments()
{


	sort(this->documents.begin(), this->documents.end(), compareDocs);

}

bool Service::compareDocs(Document& lhs, Document& rhs)
{
	return lhs.getName() < rhs.getName();
}

string Service::bestMatch(string searchString)
{

	float ratio = -1;

	string bestMatch;

	for (auto& doc : this->documents)
	{
		if (doc.getName().find(searchString) != std::string::npos)
		{
			float auxRatio = float(float(searchString.size()) / float(doc.getName().size()));

			if (auxRatio > ratio)
			{
				ratio = auxRatio;

				string stringDoc;
				string keyWords;

				for (auto& keyWord : doc.getKeyWords())
				{
					keyWords += keyWord + " , ";
				}

				stringDoc = doc.getName() + " | " + keyWords + " | " + doc.getContent();

				bestMatch = stringDoc;
			}
		}

	}
	return bestMatch;
}
