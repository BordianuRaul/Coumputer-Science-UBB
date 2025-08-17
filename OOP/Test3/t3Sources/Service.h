#pragma once

#include <vector>
#include <algorithm>
#include "Document.h"
#include <sstream>
#include <fstream>


class Service
{
private:

	vector<Document> documents;
public:
	Service();

	Document parseDocument(string);

	vector<Document> getDocuments();

	void sortDocuments();

	static bool compareDocs(Document&, Document&);

	string bestMatch(string searchString);

};

