cmake_minimum_required(VERSION 3.21)
project(SortedMapLab1)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(SortedMapLab1
        App.cpp
        ExtendedTest.cpp
        ExtendedTest.h
        ShortTest.cpp
        ShortTest.h
        SMIterator.cpp
        SMIterator.h
        SortedMap.cpp
        SortedMap.h)
