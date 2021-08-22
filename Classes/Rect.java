class Retangulo{
    int x, y;
    int largura, altura;
    Retangulo (int posx, int posy, int largura, int altura){
        this.x = posx;
        this.y = posy;
        this.largura = largura;
        this.altura = altura;
    }
    int area(){
        int rectarea;
        int b = this.largura;
        int h = this.altura;
        rectarea = b * h;
        return rectarea;
    }
    void drag(int dx, int dy){
        this.x = this.x + dx;
        this.y = this.y + dy;        
    }
}
