#Per compilare lanciare il comando make.
#Per avviare il programma, usare il comando ./sorting
all: sortingTest.o sorting.o makefile
	gcc -o sorting sortingTest.o sorting.o

sortingTest.o: sortingTest.c makefile
	gcc -c -O3 sortingTest.c
	
sorting.o: sorting.c sorting.h makefile
	gcc -c -O3 sorting.c
	
clean:
	rm -f *.o sorting 
