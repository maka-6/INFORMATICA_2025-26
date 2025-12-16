public class Aeroplane implements CanFly{

    String name;
    Aeroplane(){
        this.name = "9/11";
    }

    @Override
    public String Fly(){
        return "Fly";
    }
}
