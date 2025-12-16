class Bird {

    String name;

    public Bird(String name){
        this.name = name;
    }

    String iCanFly(){
        return name + " i can fly";
    }

    String laysEggs(){
        return "Where are the eggs?";
    }

    Boolean isAngry(){
        return true;
    }
}
