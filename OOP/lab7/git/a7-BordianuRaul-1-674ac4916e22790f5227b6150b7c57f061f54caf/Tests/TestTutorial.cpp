//
// Created by Raul on 3/26/2023.
//

#include "../Headers/TestTutorial.h"


void testConstructor()
{

    Tutorial emptyTutorial;

    Tutorial tutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                      "https://www.youtube.com/watch?v=wA8LriKDBAI");

    assert(tutorial.getId() == "1ABC");
    assert(tutorial.getTitle() == "Welcome to C++ tutorial!");
    assert(tutorial.getPresenter() == "Mr. Kishore");
    assert(tutorial.getDuration() == "13:06");
    assert(tutorial.getNrLikes() == 2500);
    assert(tutorial.getLink() == "https://www.youtube.com/watch?v=wA8LriKDBAI");

}

void testEqualityOperator()
{
    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    Tutorial testTutorial1("2DEF", "Welcome to Python tutorial!", "Mosh", "60:05", 267000,
                           "https://www.youtube.com/watch?v=kqtD5dpn9C8");

    assert(testTutorial != testTutorial1);

    assert(testTutorial1 == testTutorial1);

}

void testIncreaseLikes()
{
    Tutorial testTutorial("1ABC", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
                          "https://www.youtube.com/watch?v=wA8LriKDBAI");

    assert(testTutorial.getNrLikes() == 2500);

    testTutorial.increaseLikes();

    assert(testTutorial.getNrLikes() == 2501);
}

void testAllTutorial()
{
    testConstructor();
    testEqualityOperator();
    testIncreaseLikes();
}