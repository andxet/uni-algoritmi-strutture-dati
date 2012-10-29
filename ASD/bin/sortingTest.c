/* ESEMPIO DI PROGRAMMA TEST DEGLI ALGORITMI DI ORDINAMENTO */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <sys/time.h>

#include "sorting.h"

#define N 100001
#define STEP 5000

int ar[N];


  /* riempie l'array di numeri casuali */
  void fillArray(int a[], int n, int max) {
    srand(time(NULL));
    int i;
    for(i = 0; i < n; i++) a[i] = rand()%max;
  }

 /* restituisce TRUE (cioè un numero != 0) se l'array è ordinato,
    restituisce FALSE (cioè 0) se l'array non è ordinato */
  int sorted(int a[], int n) {
    int i = 1;
    while(i < n && a[i-1] <= a[i]) i++;
    return i >= n;
  }

 int main() {
    struct timeval t1, t2;
    double timediff;

    FILE* pFile = fopen("tempi.txt", "w");

    printf("CICLO MISURAZIONE TEMPI\n\n");
    int n;

    printf("ISORT\n\n"); fprintf(pFile, "ISORT\n\n");
    for(n = STEP; n < N; n += STEP) {
      fillArray(ar, n, 9*n/10);
      printf("%s\n", sorted(ar, n) ? "ordinato" : "non ordinato");
      printf("sto ordinando\n");
      gettimeofday(&t1, NULL);
      isort(ar, n);
      gettimeofday(&t2, NULL);
      timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
      timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
      printf("size = %6d: tempo = ms %9.2f;\n", n, timediff);
      fprintf(pFile, "size = %6d: tempo = ms %9.2f; ", n, timediff);
      printf("%s\n\n", sorted(ar, n) ? "ordinato" : "non ordinato");

      scambia(ar, 5, 50);
      gettimeofday(&t1, NULL);
      isort(ar, n);
      gettimeofday(&t2, NULL);
      timediff = (t2.tv_sec - t1.tv_sec) * 1000.0;      // sec to ms
      timediff += (t2.tv_usec - t1.tv_usec) / 1000.0;   // us to ms
      printf("su quasi ord.: tempo = ms %5.2f;\n", timediff);
      fprintf(pFile, "su quasi ord.: tempo = ms %5.2f\n", timediff);
      printf("%s\n\n", sorted(ar, n) ? "ordinato" : "non ordinato");
    }

    // ...

    printf("\n\n");
    fclose(pFile);
    return 0;
  }


    /* Modificare il programma precedente
       aggiungendo un analogo testing per tutti gli altri algoritmi
       di ordinamento realizzati o che realizzeremo
       (per ora ssortMax, isort, mergesort).
       Testare (e confrontare fra di loro) gli algoritmi precedenti
       anche su array ordinati.

       Esaminare il file dei risultati prodotto
       e confrontare tali risultati con le previsioni teoriche.

       Potete scegliere fra:
       1) testare ciascun algoritmo separatamente
          su array di lunghezza crescente: così
          è facile costruire la tabella dei tempi
          per ciascun algoritmo;
       2) per ciascun array di una data lunghezza
          testare tutti gli algoritmi su copie identiche
          dello stesso array: così è facile confrontare
          fra di loro i diversi algoritmi.

    */



