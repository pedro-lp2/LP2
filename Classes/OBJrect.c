#include <stdio.h>

typedef struct
{
    int posx, posy;
    int largura;
    int altura;
} Quad;

void printfigura (Quad* fig){
    printf("Dimensões %d e %d. Na posicao %d e %d\n", fig->largura, fig->altura, fig->posx, fig->posy);
}

void main(){
    Quad quadrado = {4, 5, 8, 8};
    Quad retangulo = {9, 9, 5, 10};
    printfigura(&quadrado);
    printfigura(&retangulo);
}
