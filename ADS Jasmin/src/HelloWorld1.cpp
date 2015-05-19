//============================================================================
// Name        : HelloWorld1.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
#include "ContDynArray.h"
#include <iostream>
using namespace std;

int main() {
	ContDynArray< int, 2 > meintab;

	for( int i = 0; i < 5; ++ i ){
		meintab.add(i);
	}

	//cout << "Minimum " << meintab.min() << endl;
	//cout << "Maximum " << meintab.max() << endl;




	//cout << meintab.size() << endl;
	//bool enthalten = meintab.member(8);
	//cout << "Wert enthalten: " << enthalten << endl << endl;

	cout << "hier beginnt print: " << endl;
	meintab.print(std::cout);
	cout << "hallo" << endl;

	return 0;
}
