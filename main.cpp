#include <stdio.h>
#include <pthread.h>

int A[2][2] = {{1, 2}, {3, 4}};
int B[2][2] = {{5, 6}, {7, 8}};
int C[2][2];

void* multiply(void* arg) {
    int row = *(int*)arg;
    for (int col = 0; col < 2; col++) {
        C[row][col] = 0;
        for (int i = 0; i < 2; i++) {
            C[row][col] += A[row][i] * B[i][col];
        }
    }
    return NULL;
}

int main() {
    pthread_t threads[2];
    int rows[2] = {0, 1};

    for (int i = 0; i < 2; i++) {
        pthread_create(&threads[i], NULL, multiply, (void*)&rows[i]);
    }
    for (int i = 0; i < 2; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Result:\n");
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++)
            printf("%d ", C[i][j]);
        printf("\n");
    }

    return 0;
}
