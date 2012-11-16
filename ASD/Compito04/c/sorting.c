#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <sys/time.h>

#include "sorting.h"


/** SELECTION SORT */

  void ssort(int a[], int n) {
	  int i = 0, j, min;
	  while (i < n) {
		  min = i;
		  for(j = 0; j < n - i; j ++){
			  if(a[min] > a[j+i])
				  min = j+i;
		  }
		  scambia(a, i, min);
		  i++;
	  }
  }

  void ssortMax(int a[], int n) {
	  int i = n, j, max;
	  while (i > 0){
		  max = i;
		  for (j = i - 1; j >= 0; j--) {
			  if(a[max] < a[j])
				  max = j;
		  }
		  scambia(a, i, max);
		  i--;
	  }
	  //for(i = 0; i < n; i ++)
	  //printf("\n%d", a[i]);
  }


/** INSERTION SORT */

/** METODO AUSILIARIO INSERISCI
  preso come parametro un array v di interi ORDINATO
  fino all'elemento di indice i-1,
  inserisce l'intero a[i] al posto giusto in v[0..i]
*/

  void inserisci(int v[], int i) {
	  int temp = v[i], j = i;
	  while(j > 0 && v[j - 1] > temp){
		  v[j] = v[j-1];
		  j--;
	  }
	  v[j] = temp;
  }

  void isort(int a[], int n) {
	  int i;
	  for(i = 0; i < n; i++)
		  inserisci(a, i);
  }


/** MERGESORT */

void merge(int a[], int first, int middle, int last, int aux[]){
	int i = first, j = middle+1, k = first;
	while(i <= middle && j <= last) {
		if(a[i] <= a[j])
			aux[k++] = a[i++];
		else
			aux[k++] = a[j++];
	}
	int h, l, r;
	for(h = middle, l = last; h >= i;)//Ricopio la parte ordinata del primo segmento in a
		a[l--] = a[h--];
	for(r = first; r < k; r++)//Ricopio la parte ordinata del secondo segmento in a
		a[r] = aux[r];
}

void mergesortASDric(int a[], int first, int last, int aux[]){
	if(first < last) {
		int m = (first+last)/2;
		mergesortASDric(a, first, m, aux);
		mergesortASDric(a, m+1, last, aux);
		merge(a, first, m, last, aux);
	}
}

  void mergesortASD(int a[], int n) {
	  int aux[n];
	  mergesortASDric(a, 0, n-1, aux);
  }

void scambia(int * arr, int i1, int i2){
	int temp = arr[i1];
	arr[i1] = arr[i2];
	arr[i2] = temp;
}
