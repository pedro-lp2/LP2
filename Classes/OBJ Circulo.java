class Circulo{
    int x, y;
    int raio;
    Circulo (int posx, int posy, int raio){
        this.x = posx;
        this.y = posy;
        this.raio = raio;
    }
  
    void print(){
        System.out.println("Posicao %d e %d e Raio %d", this.x, this.y, this.raio);
      
    }
}
