package POO.Entrega2.Circulo;
class Circle{
  private String name;
  private double radius;
  private Point center;
  private final double pi = 3.14159265359;

  public void constructor_not_constructor(double raio, double centro_x, double centro_y){
    center = new Point();
    center.constructor_not_constructor(centro_x, centro_y);
    setRadius(raio);
  }

  public Point getCenter(){
    return center;
  }
  
  public void setRadius(double input){
    radius = Math.max(0, input);
  }
  public double getRadius(){
    return radius;
  }
  public void setName(String input){
    if(input != "") name = input;
  }
  public String getName(){
    return name;
  }
  public double diameter(){
    return radius*2;
  }
  public double area(){
    return pi*Math.pow(radius,2);
  } 
  public double perimeter(){
    return pi*radius*2;
  }

  public void print_values(){
    System.out.println("---------------------------");
  System.out.println("Dados do circulo de raio " + String.format("%.2f", getRadius()));
    System.out.println("Centro: [" + String.format("%.2f", getCenter().getX()) + " | " + String.format("%.2f", getCenter().getY()) + "]");
    System.out.println("Diametro: " + String.format("%.2f", diameter()));
    System.out.println("Circunferencia: " + String.format("%.2f", perimeter()));
    System.out.println("Area: " + String.format("%.2f", area()));
    System.out.println("---------------------------");
    return;
  }
}
