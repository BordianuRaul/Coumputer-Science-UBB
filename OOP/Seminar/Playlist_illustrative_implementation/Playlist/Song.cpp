#include "Song.h"
#include <string>

Song::Song(): title(""), artist(""), duration(Duration()), source("") {}

Song::Song(const std::string& artist, const std::string& title, const Duration& duration, const std::string& source)
{
	this->artist = artist;
	this->title = title;
	this->duration = duration;
	this->source = source;
}

std::string Song::toString()
{
	return this->getArtist() + " - " + this->getTitle() + ", duration: " + std::to_string(this->duration.getMinutes()) + ":" + std::to_string(this->duration.getSeconds()) + ", " + this->getSource();
}
