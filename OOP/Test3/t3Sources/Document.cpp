#include "Document.h"

Document::Document(string name, vector<string> keyWords, string content)
{

	this->name = name;
	this->content = content;
	for (auto& keyWorkd : keyWords)
	{
		this->keyWords.push_back(keyWorkd);
	}

}


string Document::getContent()
{
	return this->content;
}

string Document::getName()
{
	return this->name;
}

vector<string> Document::getKeyWords()
{
	return this->keyWords;
}

string Document::toString()
{
	string stringDoc;

	string keyWords;

	for (auto& keyWord : this->keyWords)
	{
		keyWords += keyWord + ";";
	}

	stringDoc += this->name + " | " + keyWords;

	return stringDoc;
}

string Document::getStringKeyWords()
{
	string keyWords;

	for (auto& keyWord : this->keyWords)
	{
		keyWords += keyWord + " ";
	}

	return keyWords;
}
