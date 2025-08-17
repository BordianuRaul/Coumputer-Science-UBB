cmake_minimum_required(VERSION 3.22)
project(a7_BordianuRaul_1)

set(CMAKE_CXX_STANDARD 14)

include_directories(Headers)
include_directories(Repository)

add_executable(a6_BordianuRaul_1
        Domain/Tutorial.cpp
        Headers/CSVRepo.h
        Headers/FileRepo.h
        Headers/HTMLRepo.h
        Headers/Service.h
        Headers/TestFileRepo.h
        Headers/TestRepository.h
        Headers/Tests.h
        Headers/testService.h
        Headers/TestTutorial.h
        Headers/Tutorial.h
        Headers/UserInterface.h
        Repository/CSVRepo.cpp
        Repository/FileRepo.cpp
        Repository/HTMLRepo.cpp
        Repository/Repository.h
        Service/Service.cpp
        Tests/TestFileRepo.cpp
        Tests/TestRepository.cpp
        Tests/Tests.cpp
        Tests/testService.cpp
        Tests/TestTutorial.cpp
        UserInterface/UserInterface.cpp
        main.cpp Validators/RepositoryException.cpp Headers/RepositoryException.h Validators/TutorialValidator.cpp Headers/TutorialValidator.h)
