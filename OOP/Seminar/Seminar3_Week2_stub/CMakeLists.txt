cmake_minimum_required(VERSION 3.21)
project(Seminar3_Week2_stub)

set(CMAKE_CXX_STANDARD 14)

include_directories(Seminar3_Week2_stub)

add_executable(Seminar3_Week2_stub
        Seminar3_Week2_stub/DynamicVector.h
        Seminar3_Week2_stub/LinkedList.h
        Seminar3_Week2_stub/main.cpp
        Seminar3_Week2_stub/Song.cpp
        Seminar3_Week2_stub/Song.h
        Seminar3_Week2_stub/Tests.cpp
        Seminar3_Week2_stub/Tests.h)
