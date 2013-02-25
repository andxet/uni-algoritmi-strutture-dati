/* ESEMPIO DI PROGRAMMA TEST DEGLI ALGORITMI DI ORDINAMENTO */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>

#include "sorting.h"

//#define N 100001
//#define STEP 5000

#define TIPO_TEST_L_STRINGA 50

#define N 2000001
#define STEP 100000
#define START STEP
//#define START 1500

#define ELEM_TEST_SOGLIA 2000000

int ar[N];
int * arOrdinato;
struct timeval t1, t2;
double timediff;

#define MERGESORTB 0 //mergesort - versione-base, con insertion-sort su porzioni sotto una soglia;
#define MERGESORTO 1 //mergesort - versione senza chiamate inutili del merge (ed eventualmente con merge ottimizzato);
#define MERGESORTPA 2 //mergesort - versione "a passo alternato".

#define QUICKSORTLT 3
#define QUICKSORTH 4
#define QUICKSORTO 5

#define true 1
#define false 0

#define TEST_NONORDINATO true
#define TEST_ORDINATO true

#define NUM_ALG 6


char algoritmi[][50] = {"MergeSort base",
	"Mergestort ottimizzato",
	"Mergesort passo alternato",
	"Quicksort libro",
	"Quicksort \"alla Hoare\"",
	"Quicksort ottimizzato"
};

//#define NUM_ALG 6 //Cambiare in base al numero di algoritmi!!

void testSort();
void testSoglia();
void testAlgoritmi();
void testQsort();

////////
//MAIN//
////////

int main() {
    
    //testSoglia(); //Test per calcolare la soglia
    testAlgoritmi();//Test per calcolare il tempo di esecuzione degli algoritmi
    testQsort();//Test per il qsort, con molti o pochi elementi ripetuti
    return 0;
}

/* restituisce TRUE (cioè un numero != 0) se l'array è ordinato,
 restituisce FALSE (cioè 0) se l'array non è ordinato */
int sorted(int a[], int n) {
    int i = 1;
    while(i < n && a[i-1] <= a[i]) i++;
    return i >= n;
}

/* riempie l'array di numeri casuali */
void fillArray(int a[], int n, int max) {
    if(max == 0)
        max = 10;
    srand((int) time(NULL));
    int i;
    for(i = 0; i < n; i++) a[i] = rand()%max;
  }

void fillArray2(int varieta){
    fillArray(ar, N, 100 * N);
    arOrdinato = vcopy(ar, N);
    quicksortOttimizzato(arOrdinato, N);
    if(!sorted(arOrdinato, N)){
        printf("L'algoritmo di sorting non funziona!");
        //exit(-1);
    }
}

void fillArrayOrdered(int a[], int n){
	int i;
	for(i = 0; i < n; i++) a[i] = i;
}

void sort(int * v, int n, int alg){//Richiama la funzione di ordinamento alg
	switch (alg) {
		case QUICKSORTLT:
			quicksort(v, n);
			break;
        //case QUICKSORTF:
            //quicksortFac(v, n);
            //break;
		case QUICKSORTH:
			quicksortHoare(v, n);
			break;
        case QUICKSORTO:
            quicksortOttimizzato(v, n);
        case MERGESORTO:
            mergesortO(v, n);
            break;
        case MERGESORTB:
            mergesortB(v, n);
            break;
        case MERGESORTPA:
            mergesortPA(v, n);
            break;
		default:
			break;
	}
}

int implem(int i){//Restituisce true se l'algoritmo i è implementato
	switch (i) {
        case QUICKSORTLT:
        //case QUICKSORTF:
		//case ISORT:
		case QUICKSORTH:
        case QUICKSORTO:
        case MERGESORTO:
        case MERGESORTB:
        case MERGESORTPA:
			return true;
			break;
		default:
			return false;
			break;
	}
}

void testTempi(int * array, int alg, FILE * log){
    int n;
    for(n = START; n < N; n += STEP) {
		int * v = vcopy(ar, n);
		printf("PASSO %d di %d ", n/STEP - START/STEP + 1, N/STEP - START/STEP + 1);
        
        gettimeofday(&t1, NULL);
        sort(v, n, alg);
        gettimeofday(&t2, NULL);
        
        timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
        timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
        printf("size = %6d: tempo = ms %5.2f;\n", n, timediff);
        fprintf(log, "%5.2f\n", timediff);
        printf("%s", sorted(v, n) ? "" : "---NON ORDINATO!!!!!!---");
    }
}

void testSort(int alg, char * tipoTest){
	if(!TEST_NONORDINATO && !TEST_ORDINATO)
		return;
    
    if(TEST_NONORDINATO){
        
        char nomefile[100];
        sprintf(nomefile, "Tempi %s %s non ordinato.txt", algoritmi[alg], tipoTest);
        FILE * file = fopen(nomefile, "w");
        
        printf("\n//////////\n\t%s %s non ordinato\n\tstart: %d step: %d n: %d\n//////////\n\n", algoritmi[alg], tipoTest, START, STEP, N);
        fprintf(file, "%s, start: %d step: %d n: %d\n\n", algoritmi[alg], START, STEP, N);
    
        int * temp = vcopy(ar, N);
        testTempi(temp, alg, file);
        
        fclose(file);
    }
    
	if(TEST_ORDINATO){
        char nomefile[100];
        sprintf(nomefile, "Tempi %s %s ordinato.txt", algoritmi[alg], tipoTest);
        FILE * file = fopen(nomefile, "w");
        
        printf("\n//////////\n\t%s %s ordinato\n\tstart: %d step: %d n: %d\n//////////\n\n", algoritmi[alg], tipoTest, START, STEP, N);
        fprintf(file, "%s, start: %d step: %d n: %d\n\n", algoritmi[alg], START, STEP, N);
        
        int * temp = vcopy(arOrdinato, N);
        testTempi(temp, alg, file);
        
        fclose(file);
    }
}

void testSoglia(){
    int originale[ELEM_TEST_SOGLIA];
    fillArray(originale, ELEM_TEST_SOGLIA, 9 * ELEM_TEST_SOGLIA / 10);
    int i;
    int * vett;
    FILE * file;
    
    file = fopen("test soglia quicksort.txt", "w");
    vett = vcopy(originale, ELEM_TEST_SOGLIA);
    printf("INIZIO TEST SOGLIA\n%d elementi", ELEM_TEST_SOGLIA);
    gettimeofday(&t1, NULL);
    quicksortHoare(vett, ELEM_TEST_SOGLIA);
    gettimeofday(&t2, NULL);
    timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
    timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
    printf("Tempo quicksortHoare: ms %5.2f\n", timediff); fprintf(file, "Quicksort: %5.2f\n", timediff);
    
    for(i = 0; i < 2000; i++){
        vett = vcopy(originale, ELEM_TEST_SOGLIA);
        gettimeofday(&t1, NULL);
        quicksortOttimizzatoTestSoglia(vett, ELEM_TEST_SOGLIA, i);
        gettimeofday(&t2, NULL);
        timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
        timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
        printf("Soglia: %d, ms %5.2f\n", i, timediff); fprintf(file, "%5.2f\n", timediff);
        printf("%s\n\n", sorted(vett, ELEM_TEST_SOGLIA) ? "ordinato" : "NON ORDINATO!!!!!!");
    }
    fclose(file);
    
    file = fopen("test soglia mergesort.txt", "w");
    vett = vcopy(originale, ELEM_TEST_SOGLIA);
    printf("INIZIO TEST SOGLIA\n%d elementi", ELEM_TEST_SOGLIA);
    gettimeofday(&t1, NULL);
    mergesortASD(vett, ELEM_TEST_SOGLIA);
    gettimeofday(&t2, NULL);
    timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
    timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
    printf("Tempo mergesort: ms %5.2f\n", timediff); fprintf(file, "mergesort: %5.2f\n", timediff);
    
    for(i = 0; i < 2000; i++){
        vett = vcopy(originale, ELEM_TEST_SOGLIA);
        gettimeofday(&t1, NULL);
        mergesortBTestSoglia(vett, ELEM_TEST_SOGLIA, i);
        gettimeofday(&t2, NULL);
        timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
        timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
        printf("Soglia: %d, ms %5.2f\n", i, timediff); fprintf(file, "%5.2f\n", timediff);
        printf("%s\n\n", sorted(vett, ELEM_TEST_SOGLIA) ? "ordinato" : "NON ORDINATO!!!!!!");
    }
    fclose(file);
}

void testAlgoritmi(){
    printf("CICLO MISURAZIONE TEMPI\n\n");
    
    int i;
    fillArray2(9*N/10);
    for(i = 0; i < NUM_ALG; i++)
        if(implem(i))
            testSort(i, "");
    printf("\nFINE TEST\n");
}

void testQsort(){
    printf("CICLO MISURAZIONE TEMPI QUICKSORT\n\n");
    
    int i;
    
    fillArray2(100 * N);//Riempio l'array con pochi elementi uguali
    for(i = 3; i < NUM_ALG; i++)
        if(implem(i))
            testSort(i, "elementi molto diversi");
    
    fillArray2(10);//Riempio l'array con elementi poco vari (10 tipi)
    for(i = 3; i < NUM_ALG; i++)
        if(implem(i))
            testSort(i, "elementi poco vari");
    
    printf("\nFINE TEST\n");
}





