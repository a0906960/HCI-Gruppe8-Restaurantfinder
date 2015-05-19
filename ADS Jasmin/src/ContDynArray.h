 #ifndef CONTDYNARRAY_H
 #define CONTDYNARRAY_H
 
 // ContDynArray.h
 // 
 // UE Algorithmen und Datenstrukturen - SS 2014 Universitaet Wien
 // https://cewebs.cs.univie.ac.at/ADS/ss14/
 //
 // Beispielimplementierung mit Datenstruktur "unsortiertes Feld"
 // Achtung: Da hier keine effiziente Suchdatenstruktur zum Einsatz kommt, 
 // ist das Laufzeitverhalten extrem schlecht.
 
 #include <iostream>
 #include "Container.h"
 
 class ContDynArrayEmptyException : public ContainerException {
 public:
	 virtual const char * what() const throw() { return "ContDynArray: empty"; }
 };

 template <typename E = int, size_t N = 2>
 class ContDynArray : public Container<E> {
	 size_t hashRow;
	 size_t counter;
	 double maxSize;
	 E * values;
	 int * status;
	 void sort(E* sorted, int left, int right) const;
 public:
	 ContDynArray(double max = 0.7){
		 if(N == 0 || N < 0){
			 hashRow = 2;
		 }else{
			 hashRow = N;
		 }

		 counter = 0;
		 maxSize = max;

		 values = new E[this->hashRow];
		 status = new int[this->hashRow];

		 for(int i = 0; i < hashRow; i++){
			 status[i] = 0;
		 }
	 }
	 virtual ~ContDynArray( ) { delete[] values, delete[] status; }
 
	 using Container<E>::add;
	 virtual void add( const E e[], size_t len );
 
	 using Container<E>::remove;
	 virtual void remove( const E e[], size_t len );
 
	 virtual bool member( const E& e ) const;
	 virtual size_t size( ) const { return counter; }
 
	 virtual E min( ) const;
	 virtual E max( ) const;
 
	 virtual std::ostream& print( std::ostream& o ) const;

	 virtual size_t apply( const Functor<E>& f, Order order = dontcare ) const;

	 virtual size_t firstHashfkt (const E& e) const;

	 virtual size_t secondHashfkt (const E& e) const;

	 virtual void tableCheck();

	 void tableEnlargement();
 };

template <typename E, size_t N>
size_t ContDynArray<E,N>::firstHashfkt( const E& e ) const {
	size_t hashVariable = hashValue<E>(e);

	return hashVariable % hashRow;
}

template <typename E, size_t N>
size_t ContDynArray<E,N>::secondHashfkt( const E& e ) const {
	size_t hashVariable = hashValue<E>(e);
	hashVariable = 1;

	return hashVariable;
}

//Methode in der geprüft wird ob die Hashtabelle vergrößert werden muss
template <typename E, size_t N>
void ContDynArray<E,N>::tableCheck(){
	double div = double(counter) / double(hashRow);

	if(maxSize < div){
		tableEnlargement();
	}
}

template <typename E, size_t N>
void ContDynArray<E,N>::tableEnlargement(){
		//Zeilenanzahl verdoppeln
		int oldHashRow = hashRow;
		hashRow *= 2;

		//Neues Array erstellen
		E * newValues;
		int * newStatus;
		newValues = new E[this->hashRow];
		newStatus = new int[this->hashRow];

		for(int i = 0; i < hashRow; i++){
			newStatus[i] = 0;
		}

		//Werte aus altem Array ins neue Array einfügen
		for(int i = 0; i < oldHashRow; i++){
			if(status[i] == 1){
				E e = values[i];
				int pos = firstHashfkt(e);

				//Fall 1: Zeile ist frei oder wiederfrei -> Wert einfügen, auf belegt setzen, counter erhöhen
				if(newStatus[pos] == 0) {
					newValues[pos] = e;
					newStatus[pos] = 1;

				//Fall 2: Zeile ist belegt -> Wert ist nicht enthalten
				}else { //(newStatus[pos] == 1 && newValues[pos] != e)
					int step = secondHashfkt(e);
					int newPos = (pos + step) % hashRow;

					bool search = false;

					while(search == false && newPos != pos){
						if(newStatus[newPos] == 0){
							newValues[newPos] = e;
							newStatus[newPos] = 1;
							search = true;
						}else { //if(newStatus[newPos] == 1 && newValues[newPos] != e){
							newPos = (newPos + step) % hashRow;
						}
					}
				}
			}
		}

		//neues Array über altes Array speichern
		delete[] values;
		delete[] status;
		values = newValues;
		status = newStatus;
	}


 template <typename E, size_t N>
 void ContDynArray<E,N>::add( const E e[], size_t len ) {
	 for(size_t i = 0; i < len; i++){
		 int pos = firstHashfkt(e[i]);

		 if(status[pos] == 0){
			 values[pos] = e[i];
			 status[pos] = 1;
			 counter++;

		 }else if(status[pos] == 2){
			 bool search = member(e[i]);
			 if(search == false){
				 values[pos] = e[i];
				 status[pos] = 1;
				 counter++;
			 }

		 }else if(status[pos] == 1 && values[pos] == e[i]){

		 }else { //if(status[pos] == 1 && values[pos] != e[i])
			 int step = secondHashfkt(e[i]);
			 int newPos = (pos + step) % hashRow;

			 bool search = false;

			 while(search == false && newPos != pos){
				 if(status[newPos] == 0 || status[newPos] == 2){
					 values[newPos] = e[i];
				 	 status[newPos] = 1;
				 	 counter++;
				 	 search = true;
				  }else if(status[newPos] == 2){
				 	 bool search = member(e[i]);
				 	 if(search == false){
				 		 values[newPos] = e[i];
				 		 status[newPos] = 1;
				 		 counter++;
				 	 }
				  }else if(status[newPos] == 1 && values[newPos] == e[i]){
				 	 search = true;
				  }else { //if(status[newPos] == 1 && values[newPos] != e[i])
				 	 newPos = (newPos + step) % hashRow;
				  }
			 }
		 }
		 tableCheck();
	 }
}
 
 template <typename E, size_t N>
 void ContDynArray<E,N>::remove( const E e[], size_t len ) {
	 for(size_t i = 0; i < len; i++){
		 int pos = firstHashfkt(e[i]);

		 if(status[pos] == 1 && values[pos] == e[i]){
			 status[pos] = 2;
			 counter--;
		 }else if(status[pos] == 0){

		 }else{
			 int step = secondHashfkt(e[i]);
			 int newPos = (pos + step) % hashRow;

			 bool search = false;

			 while(search == false && newPos != pos){
				 if(status[newPos] == 1 && values[newPos] == e[i]){
					 status[newPos] = 2;
					 counter--;
					 search = true;
				 }else if(status[newPos] == 0){
					 search = true;
				 }else{
					 newPos = (newPos + step) % hashRow;
				 }
			 }
		 }
	 }
 }
 
 template <typename E, size_t N>
 bool ContDynArray<E,N>::member( const E& e ) const {
	 int pos = firstHashfkt(e);

	 if(status[pos] == 1 && values[pos] == e){
	 	return true;

	 }else if(status[pos] == 0){
		return false;

	 }else { // if(status[pos] == 1 && values[pos] != e)||status[pos] == 2
	 	int step = secondHashfkt(e);
	 	int newPos = (pos + step) % hashRow;

	 	while(newPos != pos){
	 		if(status[newPos] == 1 && values[newPos] == e){
	 			return true;
	 		}else if(status[newPos] == 0){
	 			return false;
	 		}else{
	 			newPos = (newPos + step) % hashRow;
	 		}
	 	}
	 }

	 return false;
}
 

 template <typename E, size_t N>
 E ContDynArray<E,N>::min( ) const {
	 if (this->empty()){
		 throw ContDynArrayEmptyException();
	 }

	 bool search = false;
	 E min;
	 int i = 0;

	 while(search == false && i < hashRow){
		 if(status[i] == 1){
			min = values[i];
			search = true;
		 }else{
			 i++;
		 }
	 }

	 for(int j = 1; j < hashRow; j++){
		  if(status[j] == 1 && min > values[j]){
			 min = values[j];
		 }
	 }
	 return min;
 }
 
 template <typename E, size_t N>
 E ContDynArray<E,N>::max( ) const {
	 if (this->empty()){
	 	 throw ContDynArrayEmptyException();
	  }

	 bool search = false;
	 E max;
	 int i = 0;

	 while(search == false && i < hashRow){
	 	 if(status[i] == 1){
	 		max = values[i];
	 		search = true;
	 	 }
	 	 i++;
	  }

	  for(size_t j = 1; j < hashRow; j++){
	 	 if(status[j] == 1 && values[j] > max){
	 		 max = values[j];
	 	 }
	  }
	  return max;
}
 
 template <typename E, size_t N>
 std::ostream& ContDynArray<E,N>::print( std::ostream& o ) const {
	 o << "ContDynArray [ count=" << counter << " hashRow=" << hashRow << " values=";
	 for (size_t i = 0; i < hashRow; ++i){
		 if(status[i] == 1){
			 o << ' ' << values[i];
		 }

	 }
	 o << " ]" << std::endl;

	 for(int i= 0; i < hashRow; i++){
	 	o << i << ": Wert: " << values[i] << ", Status: " <<  status[i] << std::endl;
	 }

	 return o;
 }
 
 template <typename E, size_t N>
 size_t ContDynArray<E,N>::apply( const Functor<E>& f, Order order ) const {
	 size_t rc = 0;

	 if (order != dontcare && counter != 0){
		 E * sorted = new E[this->counter];

		 int j = 0;

		 for(int i = 0; i < hashRow; i++){
			 if(status[i] == 1){
				 sorted[j] = values[i];
		 		 j++;
		 	 }
		 }

		 int left = 0;
		 int right = counter - 1;

		 sort(sorted, left, right);

		 if(order == descending){
			 for(int i = counter - 1; i >= 0; i--){
				 ++rc;
				 if(!f(sorted[i])) break;
			 }
			 delete[] sorted;
		 }else if(order == ascending){
			 for(int i = 0; i < counter; i++){
				 ++rc;
				 if(!f(sorted[i])) break;
			 }
			 delete[] sorted;
		 }

	 }else{
		 for(int i = 0; i < hashRow; i++){
			 if(status[i] == 1){
				 ++rc;
				 if (!f(values[i])) break;
			 }
		 }
	 }


	 return rc;
 }

 template <typename E, size_t N>
 void ContDynArray<E,N>::sort(E* sorted, int left, int right) const{
	 int l, r;
	 E pivot;

	 if(right > left){
		 pivot = sorted[right];
		 l = left - 1;
		 r = right;

		 for(;;){
			 while(pivot > sorted[++l]);
			 while(sorted[--r] > pivot){
				 if (r == left) {
					 break;
				 }
			 }

			 if(l >= r) {
				 break;
			 }

			 std::swap(sorted[l], sorted[r]);
		 }

		 std::swap(sorted[l], sorted[right]);

		 sort(sorted, left, l-1);
		 sort(sorted, l+1, right);
	 }
 }

 #endif //CONTDYNARRAY_H
