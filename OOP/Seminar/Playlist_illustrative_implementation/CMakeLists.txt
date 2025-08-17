cmake_minimum_required(VERSION 3.22)
project(Playlist_illustrative_implementation)

set(CMAKE_CXX_STANDARD 14)

include_directories(Playlist)

add_executable(Playlist_illustrative_implementation
        Playlist/DynamicVector.cpp
        Playlist/DynamicVector.h
        Playlist/main.cpp
        Playlist/PlayList.cpp
        Playlist/PlayList.h
        Playlist/Repository.cpp
        Playlist/Repository.h
        Playlist/Service.cpp
        Playlist/Service.h
        Playlist/Song.cpp
        Playlist/Song.h
        Playlist/UI.cpp
        Playlist/UI.h)
