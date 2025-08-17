cmake_minimum_required(VERSION 3.22)
project(SortedIteratedListLab2)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(SortedIteratedListLab2
        App.cpp
        ExtendedTest.cpp
        ExtendedTest.h
        ListIterator.cpp
        ListIterator.h
        ShortTest.cpp
        ShortTest.h
        SortedIteratedList.cpp
        SortedIteratedList.h)
