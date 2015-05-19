 #ifndef DOUBLEHASHING_H
 #define DOUBLEHASHING_H

/*
 * Algodat.h
 *
 *  Created on: 13.05.2014
 *      Author: Jasmin
 */
#include "Container.h"

enum Status {frei, belegt, wiederfrei};

template <typename E>
class DoubleHashing:public Container <E> {

private:
	int hashRow;
	int hashColumn; //Status: 0 = frei, 1 = belegt, 2 = wiederfrei
	double maxSize;
	size_t counter;	//Wieviele Werte in Tabelle

	//Hashtabelle als Array
	E **hashTable;

public:

	//Konstruktor der Hashtabelle
	DoubleHashing(){
		hashRow = 2;
		hashColumn = 2;
		maxSize = 0.7;
		counter = 0;

		//Hashtabelle erzeugen
		hashTable = new E*[hashRow];
		for(int i = 0; i < hashRow; i++){
			hashTable[i] = new E[hashColumn];
		}

		//Befüllen der zweiten Spalte mit 0 (=frei)
		for(int i = 0; i < hashRow; i++){
			//hashTable[i][1] = E(0);
			hashTable[i][2] = frei;
		}
	}

	DoubleHashing(E e, size_t size){
		hashRow = size;
		hashColumn = 2;
		maxSize = 0.7;
		counter = 0;

		//Hashtabelle erzeugen
		hashTable = new E*[hashRow];
		for(int i = 0; i < hashRow; i++){
			hashTable[i] = new E[hashColumn];
		}

		//Befüllen der zweiten Spalte mit 0 (=frei)
		for(int i = 0; i < hashRow; i++){
			//hashTable[i][1] = E(0);
			hashTable[i][2] = frei;
		}
	}

	//Destruktor der Hashtabelle
	~DoubleHashing(){
		std::cout << std::endl << "Destruktor aufgerufen" << std::endl;

		for(int i = 0; i < hashRow; ++i){
			std::cout << "Schleifendurchlauf: " << i << std::endl;
			delete []hashTable[i];
		}

		std::cout << "Schleife beendet" << std::endl;

		delete []hashTable;


		std::cout << "Speicher freigegeben" << std::endl;
	}

	size_t firstHashfkt( const E& e ) const {
		size_t hashVariable = hashValue<E>(e);

		return hashVariable % hashRow;
	}

	size_t secondHashfkt( const E& e ) const {
		//2. Hashfunktion zur Berechnung der Schrittweite vom Typ size_t
		int prim = 23;
		size_t hashVariable = hashValue<E>(e) % prim;
		hashVariable = hashVariable % hashRow;

		//Fall abfangen wenn die Schrittweite modulo 2 = 0 ist!(damit auf jeden Fall ganze Tabelle durchlaufen wird)
		if(hashVariable % 2 == 0){
			hashVariable += 1;
		}

		return hashVariable;
	}


	//Methode in der geprüft wird ob die Hashtabelle vergrößert werden muss
	void tableCheck(){
		double div = double(counter) / double(hashRow);
		std::cout << "div = " << div << std::endl << std::endl;
		if(div >= maxSize)
			tableEnlargement();
	}

	//Methode die die Hashtabelle vergrößert
	void tableEnlargement(){
		//Zeilenanzahl verdoppeln
		int oldHashRow = hashRow;
		hashRow *= 2;
		std::cout << "Alt: " << oldHashRow << ", Neu: " << hashRow << std::endl;

		//Neues Array erstellen
		E **newHashTable;
		newHashTable = new E*[hashRow];
		for(int i = 0; i < hashRow; i++){
			newHashTable[i] = new E[hashColumn];
		}

		//Befüllen mit 0 (=frei)
		for(int i = 0; i < hashRow; i++){
			newHashTable[i][1] = E(0);
			newHashTable[i][2] = E(0);
		}
		std::cout << "neue Hashtable angelegt!" << std::endl;

		//Werte aus altem Array ins neue Array einfügen
		for(int i = 0; i < oldHashRow; i++){
			std::cout << std::endl <<  "SCHRITT " << i << ":" << std::endl;
			if(hashTable[i][2] == E(1)){
				E e = hashTable[i][1];
				int pos = firstHashfkt(e);
				std::cout << "Wert in Table: " << e  << " -> 1. Hashfunktion angewendet = Pos = " << pos << std::endl;

				//Fall 1: Zeile ist frei oder wiederfrei -> Wert einfügen, auf belegt setzen, counter erhöhen
				if(newHashTable[pos][2] == E(0)){
					newHashTable[pos][1] = e;
					newHashTable[pos][2] = E(1);
					std::cout << "Fall 1 - erstes Feld = 0" << std::endl;

				//Fall 2: Zeile ist belegt -> Wert ist enthalten -> KEINE AKTION!
				}else if(newHashTable[pos][2] == E(1) && newHashTable[pos][1] == e){
					std::cout << "Fall 2 - Wert bereits enthalten" << std::endl;

				//Fall 3: Zeile ist belegt -> Wert ist nicht enthalten
				}else if(newHashTable[pos][2] == E(1) && newHashTable[pos][1] != e){
					int step = secondHashfkt(e);
					int newPos = pos + step;
					bool search = false;

					while(search == false){
						if(newHashTable[newPos][2] == 0){
							newHashTable[newPos][1] = e;
							newHashTable[newPos][2] = E(1);
							search = true;
						}else if(newHashTable[newPos][2] == E(1) && newHashTable[newPos][1] == e){
							search = true;
						}else if(newHashTable[newPos][2] == E(1) && newHashTable[newPos][1] != e){
							newPos += step;
						}
					}
					std::cout << "Fall 3 - zweite Hashfunktion aufgerufen" << std::endl;
				}

			}
		}




		std::cout << "Pointer wird überschrieben" << std::endl;
		//neues Array über altes Array speichern
		hashTable = newHashTable;
		std::cout << "alter hashPointer überschrieben" << std::endl;
	}


	void add( const E& e )	{
		std::cout << "EINFÜGEN von " << e << std::endl << std::endl;
		int pos = firstHashfkt(e);
		std::cout << "counter vor einfügen: " << counter << std::endl;



		//Fall 1: Zeile ist frei oder wiederfrei -> Wert einfügen, auf belegt setzen, counter erhöhen
		if(hashTable[pos][2] == E(0) || hashTable[pos][2] == E(2)){
			hashTable[pos][1] = e;
			hashTable[pos][2] = E(1);
			counter += 1;
			std::cout << "Fall 1 gewählt!" << std::endl;
		//Fall 2: Zeile ist belegt -> Wert ist enthalten -> KEINE AKTION!
		}else if(hashTable[pos][2] == E(1) && hashTable[pos][1] == e){
			std::cout << "Fall 2 gewählt!" << std::endl;
		//Fall 3: Zeile ist belegt -> Wert ist nicht enthalten
		}else if(hashTable[pos][2] == E(1) && hashTable[pos][1] != e){
			int step = secondHashfkt(e);
			int newPos = pos + step;
			bool search = false;

			while(search == false){
				if(hashTable[newPos][2] == E(0) || hashTable[newPos][2] == E(2)){ //wiederfrei extra??
					hashTable[newPos][1] = e;
					hashTable[newPos][2] = E(1);
					counter += 1;
					search = true;
				}else if(hashTable[newPos][2] == E(1) && hashTable[newPos][1] == e){
					search = true;
				}else if(hashTable[newPos][2] == E(1) && hashTable[newPos][1] != e){
					newPos += step;
				}
			}
			std::cout << "Fall 3 gewählt!" << std::endl;
		}

		std::cout << "counter nach einfügen: " << counter << std::endl << std::endl;
		tableCheck();


	}

//1.ABGABE
	void add( const E e[], size_t s ) {
		for(size_t i = 0; i < s; i++){
			//E value = e[i];
			add(e[i]);
		}
	}


	void remove ( const E& e) {

	}


	void remove( const E e[], size_t s ) {

	}

//1. ABGABE
	bool member( const E& e ) const {
		//do while solange != frei
		std::cout << "SUCHE GESTARTET" << std::endl << std::endl;

		int pos = firstHashfkt(e);

		if(hashTable[pos][2] == E(1) && hashTable[pos][1] == e){
			return true;
		}else if(hashTable[pos][2] == 1 && hashTable[pos][1] != e){
			int step = secondHashfkt(e);
			int newPos = pos + step;
			bool search = false;

			while(search == false){
				if(hashTable[newPos][2] == E(1) && hashTable[newPos][1] == e){
					return true;
				}else if(hashTable[newPos][2] == E(1) && hashTable[newPos][1] != e){
					newPos += step;
				}else if(hashTable[newPos][2] == E(0) || hashTable[newPos][2] == E(2)){//WIEDERFREI EXTRA??
					return false;
				}
			}
		}else if(hashTable[pos][2] == E(0) || hashTable[pos][2] == E(2)){
			return false;
		}
		return false;
	}

	size_t size( ) const {
		return counter;
	}

	bool empty( ) const {
		if(counter == 0){
			return true;
		}else{
			return false;
		}
	}

	size_t apply( const Functor<E>& f, Order order = dontcare ) const {
		return 0;
	}

	E min( ) const{
		return E();
	}

	E max( ) const {
		return E();
	}

	std::ostream& print( std::ostream& o ) const {
		for(int i= 0; i < hashRow; i++){
			o << i << ": Wert: " << hashTable[i][1] << ", Status: " <<  hashTable[i][2] << std::endl;
		}

		return o;
	}

};

#endif //DOUBLEHASHING_H
