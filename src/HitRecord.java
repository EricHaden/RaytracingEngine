public class HitRecord {
    Vector point;
    Vector normal;
    double t;
    boolean frontFace;
    public HitRecord(){}
    public HitRecord(Vector p, Vector normal, double t){
        this.normal=normal;
        this.point=p;
        this.t=t;
    }


    public void setFaceNormal(Ray r, Vector norm){
        //normal always faces out
        //if dot product is negative, r and norm face opposite directions and ray is outside sphere
        frontFace = r.direction().dot(norm)<0;
        if (frontFace){
            normal = norm;
        }
        //if dot product is positive, ray is inside sphere
        else normal = norm.invert();
    }
}
