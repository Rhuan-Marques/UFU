package POO.Entrega2.Circulo;
class Point{
  private double x;
  private double y;

  public void constructor_not_constructor(double coordx, double coordy){
    setX(coordx);
    setY(coordy);
  }
  
  public double getX(){
    return x;
  }
  public void setX(double input){
    x = Math.max(0, input);
  }
  public double getY(){
    return y;
  }
  public void setY(double input){
    y = Math.max(0, input);
  }
  public double dist(Point p){
    double dist1 = Math.abs(p.x - x);
    double dist2 = Math.abs(p.y - y);
    return Math.sqrt(Math.pow(dist1, 2)+Math.pow(dist2, 2));
  }
}