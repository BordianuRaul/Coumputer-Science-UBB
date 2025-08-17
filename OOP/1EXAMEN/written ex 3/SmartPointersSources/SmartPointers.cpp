// SharedPointers.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "UniquePointer.h"
#include "SharedPointer.h"
#include "Set.h"
#include <string>


void function2() {

	SharedPointer<string> s1{ new string{ "A" } };
	SharedPointer<string> s2 = s1;
	SharedPointer<string> s3{ new string{ "C" } };

	Set<SharedPointer<string>> set1{};

	try {
		set1 = set1 + s1; // IMPLEMENT THIS OPERATION
		set1 = set1 + s2; // IMPLEMENT THIS OPERATION
		
	}
	
	catch (std::runtime_error& ex) {
		cout << ex.what(); // prints: "Element already exists!"
	}
	
	SharedPointer<int> i1{ new int{ 1 } };
	SharedPointer < int> i2{ new int{ 2 } };
	SharedPointer < int> i3{ new int{ 3 } };
	Set<SharedPointer<int>> set2{};
	set2 = set2 + i1; // IMPLEMENT THIS OPERATION set2 = set2 + 12; // IMPLEMENT THIS OPERATION set2 = set2 + 13; // IMPLEMENT THIS OPERATION set2.remove(11).remove(13);
	for (auto e : set2)

		cout << *e << ", "; // prints 2, // memory is correctly deallocated
}
int main()
{
	function2();

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
