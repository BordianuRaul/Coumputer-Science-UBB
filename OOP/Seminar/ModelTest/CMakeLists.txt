cmake_minimum_required(VERSION 3.22)
project(ModelTest)

set(CMAKE_CXX_STANDARD 14)

add_executable(ModelTest main.cpp Domain/Car.cpp Domain/Car.h Repo/Repo.cpp Repo/Repo.h Service/Service.cpp Service/Service.h UI/UI.cpp UI/UI.h)
