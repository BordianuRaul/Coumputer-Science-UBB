//
// Created by Raul on 3/22/2023.
//

#ifndef SEMINAR2_SONG_H
#define SEMINAR2_SONG_H

#pragma once
#include <string>

class Song {

private:
public:
    void setArtist(const std::string &artist);

private:
    std::string artist;
    std::string title;
    int duration;

public:

    Song();

    Song(const std::string&,const std::string&, int);

    std::string getArtist() const;



};


#endif //SEMINAR2_SONG_H
