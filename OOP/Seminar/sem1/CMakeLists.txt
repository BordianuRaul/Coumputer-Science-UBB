cmake_minimum_required(VERSION 3.21)
project(sem1)

set(CMAKE_CXX_STANDARD 14)

add_executable(sem1 Planet.c Planet.h main.c Repository.c Repository.h)
