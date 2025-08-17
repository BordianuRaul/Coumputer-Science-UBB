#pragma once

#include <string>

#include <vector>

using namespace std;

class Document
{
private:
	string name;
	vector<string> keyWords;
	string content;

public:

	Document(string name, vector<string> keyWords, string content);

	string getName();
	vector<string> getKeyWords();
	string getContent();

	string toString();

	string getStringKeyWords();

};
