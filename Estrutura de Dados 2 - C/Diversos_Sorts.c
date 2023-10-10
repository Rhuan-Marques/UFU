/*
PT: Isto não é nenhum projeto especial, somente um código simples com funções para: 
EN: This isn't a special project, just simple code with functions for:

-Bubble Sort
-Selection Sort
-Insertion Sort
-Merge Sort
-Quick Sort
-Binary Search
*/
#include<stdio.h>
#include<stdlib.h>

#define loop(i, x, y) for(int i=x; i<y; i++)

typedef struct vector{
    long int *value;
    int size;
} Vector;

Vector* start_vet(long int size){
    Vector *v;
    v = (Vector*)malloc(1 * sizeof(Vector));
    v->value = (long int*)malloc(size * sizeof(long int));
    v->size = size;
    return v;
}

void print_vet(Vector* v){
    loop(i, 0, v->size){
        printf("%ld ", v->value[i]);
    }
    printf("\n");
}

void swap(long int *a, long int *b){
    long int aux = *a;
    *a = *b;
    *b = aux;
}

void BubbleSort(Vector* v){
    int found=1;
    for(int j = v->size; j>0 && found; j--){
        found=0;
        loop(i, 1, j){
            if(v->value[i-1] > v->value[i]){
                swap(&v->value[i-1], &v->value[i]);
                found=1;
            }
        }
    }
    return;
}

void SelectionSort(Vector* v){
    long int lower;
    loop(i, 0, v->size){
        lower = i;
        loop(j, i+1, v->size){
            if(v->value[j] < v->value[lower])
                lower = j;
        }
        if(i!=lower){
            swap(&v->value[i], &v->value[lower]);
        }
    }
    return;
}

void InsertionSort(Vector* v){
    loop(i, 1, v->size){
        long int inserting = v->value[i];
        int j;
        for(j=i; j>0 && v->value[j-1] > inserting; j--)
            v->value[j] = v->value[j-1]; 
        v->value[j] = inserting;
    }
    return;
}

void MergeSortRecursive(Vector *v, long int start, long int end);
// ^ Declaração básica pra poder chamar em MergeSort() ^

void MergeSort(Vector *v){
    return MergeSortRecursive(v, 0, v->size-1);
}

void MergeSortRecursive(Vector *v, long int start, long int end){
    long int half = (start+end)/2;
    long int size = end-start+1;
    if(start+1 == end){
        if(v->value[start] > v->value[end])
            swap(&v->value[start], &v->value[end]);
    }
    else if(start<end){
        MergeSortRecursive(v, start, half);
        MergeSortRecursive(v, half+1, end);
        long int left=start, right=half+1, cont;
        Vector* aux = start_vet(size);
        for(cont=0; left<=half && right<=end; cont++){
            if(v->value[left] < v->value[right]){
                aux->value[cont] = v->value[left];
                left++;
            }
            else{
                aux->value[cont] = v->value[right];
                right++;
            }
        }
        if(left>half && right<=end) loop(i, right, end+1){
            aux->value[cont] = v->value[i];
            cont++;
        }
        else if(left<=half) loop(i, left, half+1){
            aux->value[cont] = v->value[i];
            cont++;
        }
        loop(i, 0, aux->size){
            v->value[start+i] = aux->value[i];
        }
        
    }
}

void QSortRecursive(Vector* v, int start, int end); 
// ^ Declaração básica pra poder chamar em QuickSort() ^

void QuickSort(Vector* v){
    QSortRecursive(v, 0, v->size-1);
}

void QSortRecursive(Vector* v, int start, int end){
    long int pivot = v->value[end],
             count = start,
             pvt_pos = start;
    while(count<end){
        if(v->value[count] < pivot){
            swap(&v->value[count], &v->value[pvt_pos]);
            pvt_pos++;
        }
        count++;
    }
    swap(&v->value[end], &v->value[pvt_pos]);
    if(end-start > 1){
        QSortRecursive(v, start, pvt_pos-1);
        QSortRecursive(v, pvt_pos+1, end);
    }
}

int BinarySearch(long int x, Vector* v){
    long int start=0, end = v->size-1, half;
    do{
        half = (start+end)/2;
        if(v->value[half] > x) end=half-1;
        else if(v->value[half] < x) start=half+1;
        else return half;
    }while(start<=end);
    return -1;
}

int main(){
    long int x, size=10;
    Vector  *v1 = start_vet(size),
            *v2 = start_vet(size),
            *v3 = start_vet(size),
            *v4 = start_vet(size),
            *v5 = start_vet(size);
    loop(i, 0, size){
        scanf("%ld", &v1->value[i]);
        v2->value[i] = v3->value[i] = v4->value[i] = v5->value[i] = v1->value[i];
    }
    BubbleSort(v1);
    InsertionSort(v2);
    SelectionSort(v3);
    MergeSort(v4);
    QuickSort(v5);

    print_vet(v1);
    print_vet(v2);
    print_vet(v3);
    print_vet(v4);
    print_vet(v5);

    scanf("%ld", &x);
    int res = BinarySearch(x, v4);
    if(res == -1) printf("Deu ruim mermao\n");
    else printf("Posição %d", res);

}
