all: sortingTest.o sorting.o makefile
	gcc -o sorting sortingTest.o sorting.o

sortingTest.o: sortingTest.c makefile
	gcc -c sortingTest.c
	
sorting.o: sorting.c sorting.h makefile
	gcc -c sorting.c
	
clean:
	rm -f *.o sorting 