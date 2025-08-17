//
// Created by Raul on 3/22/2023.
//

#include "Song.h"

Song::Song() : artist{""}, title{""}, duration{0}
{

}

Song::Song(const std::string &artist, const std::string &title, int duration) : artist{artist}, title{title}, duration{duration}
{
//
//    this->artist = artist;
//    this->title = title;
//    this->duration = duration;
//
}

std::string Song::getArtist() const
{


    return this->artist;
}

void Song::setArtist(const std::string &newArtist) {
    Song::artist = newArtist;
}

