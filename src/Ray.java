public class Ray {
    private Vector origin;
    private Vector direction;

    public Ray(Vector o, Vector d){
        origin = o;
        direction = d;
    }
    public Vector origin(){
        return origin;
    }
    public Vector direction(){
        return direction;
    }
    public Vector at(double t){
        Vector v = direction.times(t);
        return new Vector(origin.x()+v.x(), origin.y()+v.y(), origin.z()+v.z());
    }

}
