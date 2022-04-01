package less1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> boxLayot = new ArrayList<>();
    String kindFruit;
    private int amount;
    private float weight;


    public void addFruit(T fruits, int count) {
        if (boxLayot.isEmpty()) {
            kindFruit = fruits.name;
            weight = fruits.weight * count;
            amount = count;

            for (int i = 0; i < count; i++) {
                boxLayot.add(fruits);
            }
        }
        else if (!boxLayot.isEmpty() && fruits.name.equals(this.kindFruit)) {
            amount += count;
            weight += fruits.weight * count;

            for (int i = 0; i < count; i++) {
                boxLayot.add(fruits);
            }
        }
        else {
            return;
        }
    }

    public void clearBox() {
        boxLayot.clear();
        weight = 0;
        amount = 0;
        kindFruit = null;

    }

    public float getWeight() {
        if (boxLayot.isEmpty()){
            System.out.println("Box is empty");
            return 0;
        }
        System.out.println("Weight: " + weight);
        return weight;
    }

    public int getAmount() {
        if (boxLayot.isEmpty()){
            System.out.println("Box is empty");
            return 0;
        }
        System.out.println("Amount fruits: " + amount);
        return amount;
    }

    boolean compareBox(Box<?> box){
        if(getWeight()== box.getWeight()) {
            System.out.println("Boxes are equal");
            return true;
        }else {
            System.out.println("Boxes are not equal");
            return false;
        }
    }

    boolean shiftBox(Box<T> newBox){
        if(newBox.boxLayot.isEmpty()){
            for (T t : this.boxLayot) {
                newBox.boxLayot.add(t);
            }
            newBox.kindFruit=this.kindFruit;
            newBox.amount=this.amount;
            newBox.weight=this.weight;
            this.clearBox();
            return true;
        } else {
            System.out.println("You need to empty box before shift Box!!!");
            return false;
        }
    }
}