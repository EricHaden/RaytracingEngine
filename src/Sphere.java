public class Sphere extends Hittable{
    Vector center;
    double radius;

    HitRecord nearest;

    public Sphere(Vector c, double r){
        center = c;
        radius = r;
    }
    public Sphere(double i, double j, double k, double radius){
        center = new Vector(i,j,k);
        this.radius = radius;
    }
    @Override
    public boolean Hit(Ray r, double tMin, double tMax, HitRecord rec) {
        //calculate whether the ray hits the sphere or not
        Vector v = r.origin().sub(center);
        double a = r.direction().lengthSquared(); //vector dotted with itself = length squared
        double halfb = v.dot(r.direction());
        double c =  v.lengthSquared() - radius*radius;
        double discriminant = halfb*halfb-a*c;

        //if misses, return -1, else return t value of point
        if (discriminant<0){
            return false;
        }
        //Find the nearest root within given range, return false
        //if no root is in range
        double sqrtd = Math.sqrt(discriminant);
        double root = (-halfb - sqrtd)/a;
        if (root > tMax || root < tMin){
            root = (-halfb + sqrtd)/a;
            if (root > tMax || root < tMin){
                return false;
            }
        }
        //update HitRecord rec with values of closest hit
        rec.t = root;
        rec.point = r.at(rec.t);
        Vector outwardNormal = (rec.point.sub(center)).divide(radius);
        rec.setFaceNormal(r, outwardNormal);
        return true;
    }
}
