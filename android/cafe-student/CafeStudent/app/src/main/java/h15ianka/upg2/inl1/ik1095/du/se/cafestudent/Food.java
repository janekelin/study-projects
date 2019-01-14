package h15ianka.upg2.inl1.ik1095.du.se.cafestudent;


public class Food {

    private String name;
    private int imageResourceId;

    public static final Food[] sandwiches = {

            new Food("Ostmacka, 10 kr", R.drawable.cheese),
            new Food("Sandwich med skinka, 25 kr", R.drawable.ham),
            new Food("Laxwrap, 45 kr", R.drawable.salmon),
            new Food("Köttbullemacka, 40 kr", R.drawable.meatball)

    };


    private Food(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }

}
