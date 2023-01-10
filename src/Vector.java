import java.lang.Object;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vector {
    ArrayList<Double> a;

    public Vector(Double x, Double y, Double z){
        a = new ArrayList<>(3);
        a.add(x);
        a.add(y);
        a.add(z);
    }

    public Double x(){
        Double x = a.get(0);
        return x;
    }
    public Double y(){
        return a.get(1);
    }
    public Double z(){
        return a.get(2);
    }


    public Vector invert(){
        return new Vector(-1*a.get(0),-1*a.get(1),-1*a.get(2));
    }

    public Vector add(Vector v){
        return new Vector(this.x()+v.x(), this.y()+v.y(), this.z()+v.z());
    }
    public Vector sub(Vector v){
        return new Vector(this.x()-v.x(), this.y()-v.y(), this.z()-v.z());
    }
    public Vector times(Vector v){
        return new Vector(this.x()*v.x(), this.y()*v.y(), this.z()*v.z());
    }

    public Vector times(double t){
        return new Vector(t*a.get(0),t*a.get(1),t*a.get(2));
    }
    public Vector divide(double t){
        Vector v = this.times(1/t);
        return v;
    }
    //requires i is between 0 and 2
    public Double get(int i){
        return a.get(i);
    }

    public Double lengthSquared(){
        return a.get(0)*a.get(0)+a.get(1)*a.get(1)+a.get(2)*a.get(2);
    }

    public Double length(){
        return (Double) Math.sqrt(lengthSquared());
    }

    public String toString(){
        return a.get(0) + " " + a.get(1) + " "+ a.get(2);
    }
    public Double dot(Vector v){
        return this.x()*v.x()+ this.y()*v.y()+ this.z()*v.z();
    }
    public Vector cross(Vector v){
        Double i = this.a.get(1) * v.a.get(2) - this.a.get(2) * v.a.get(1);
        Double j = this.a.get(2) * v.a.get(0) - this.a.get(0)  * v.a.get(2);
        Double k = this.a.get(0) * v.a.get(1) - this.a.get(1) * v.a.get(0);
        return new Vector(i,j,k);
    }
    public Vector unitVector(){
        return this.divide(this.length());
    }
    public String writeColour(Vector pixelColour, int samplesPerPixel){
        double r = a.get(0);
        double g = a.get(1);
        double b = a.get(2);

        //scale colour depending on number of samples
        double weight = 1.0/samplesPerPixel;
        //gamma correction
        r = Math.sqrt(weight*r);
        g = Math.sqrt(weight*g);
        b = Math.sqrt(weight*b);
        String newline = System.lineSeparator();

        return 256*clamp(r, 0.0, 0.9999) + " " + 256*clamp(g,0.0, 0.9999 ) + " " +  256*clamp(b,0.0, 0.9999 ) + newline;
    }
    public double clamp(double colour, double min, double max){
        if (colour < min) return min;
        return Math.min(colour, max);
    }
    public static Vector randomVector(){
        return new Vector(Math.random(), Math.random(), Math.random()); //might be problematic
    }
    public static Vector randomVector(double min, double max){
        return new Vector(Math.random()*(max-min)+min, Math.random()*(max-min)+min, Math.random()*(max-min)+min); //might be problematic
    }
    public static Vector bounceVector(){
        while(true){
            Vector temp = randomVector(-1,1);
            if (temp.lengthSquared() < 1){
                return temp;
            }
        }
    }

}
