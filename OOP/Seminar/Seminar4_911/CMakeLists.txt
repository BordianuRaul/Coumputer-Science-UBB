cmake_minimum_required(VERSION 3.22)
project(Seminar4_911)

set(CMAKE_CXX_STANDARD 14)

include_directories(Playlist)

add_executable(Seminar4_911
        Playlist/main.cpp
        Playlist/PlayList.cpp
        Playlist/PlayList.h
        Playlist/Repository.cpp
        Playlist/Repository.h
        Playlist/RepositoryExceptions.cpp
        Playlist/RepositoryExceptions.h
        Playlist/Service.cpp
        Playlist/Service.h
        Playlist/Song.cpp
        Playlist/Song.h
        Playlist/UI.cpp
        Playlist/UI.h
        Playlist/Utils.cpp
        Playlist/Utils.h Playlist/FilePlayList.cpp Playlist/FilePlayList.h
        )
