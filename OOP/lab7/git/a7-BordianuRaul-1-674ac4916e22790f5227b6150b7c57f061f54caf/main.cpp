////
//// Created by Raul on 3/24/2023.
////
//
#include "Headers/Tests.h"
#include "Headers/UserInterface.h"


int main()
{

   testAll();

    FileRepo repo("data.txt");
    Service service(&repo);

//    service.addTutorial("1", "Welcome to C++ tutorial!", "Mr. Kishore", "13:06", 2500,
//                        "https://www.youtube.com/watch?v=wA8LriKDBAI");
//
//    service.addTutorial("2", "Internet la 10 GIGA/secundă?!", "Dan Cadar", "22:45", 1000,
//                        "https://www.youtube.com/watch?v=J-5aOEfUcoI");
//
//    service.addTutorial("3", "Python for Beginners - Learn Python in 1 Hour", "Mosh", "60:05", 268000,
//                        "https://www.youtube.com/watch?v=kqtD5dpn9C8");
//
//    service.addTutorial("4", "Data Structures and Algorithms for Beginners","Mosh", "78:42", 29000,
//                        "https://www.youtube.com/watch?v=BBpAmxU_NQo");
//
//    service.addTutorial("5", "Object-oriented Programming in 7 minutes | Mosh", "Mosh", "7:33", 84000,
//                        "https://www.youtube.com/watch?v=pTB0EiLXUC8&t=13s");
//
//    service.addTutorial("6", "Python OOP Tutorial 1: Classes and Instances", "Corey", "15:45", 1000,
//                        "https://www.youtube.com/watch?v=ZDa-Z5JzLYM");
//
//    service.addTutorial("7", "Python OOP Tutorial 2: Class Variables", "Corey", "11:40", 12352,
//                        "https://www.youtube.com/watch?v=BJ-VvGyQxho");
//
//    service.addTutorial("9", "Python OOP Tutorial 3: classmethods and staticmethods", "Corey", "25:15", 124230,
//                        "https://www.youtube.com/watch?v=rq8cL2XMM5M");
//
//    service.addTutorial("10", "BEST WAY to read and understand code", "The Cerno", "17:32", 3900,
//                        "https://www.youtube.com/watch?v=XTZVbmz7LpY");

    UserInterface UI (service);
    UI.runUI();

   return 0;
}
