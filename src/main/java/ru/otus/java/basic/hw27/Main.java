package ru.otus.java.basic.hw27;

public class Main {
    public static void main(String[] args) {
        Fruit f = new Fruit(10);
        Apple a = new Apple(5);
        Orange o = new Orange(3);
        System.out.println(f);
        System.out.println(a);
        System.out.println(o);

        Box<Fruit> fBox1 = new Box<>();
        fBox1.add(f);
        fBox1.add(a);
        fBox1.add(o);

        Box<Fruit> fBox2 = new Box<>();
        fBox2.add(f);
        fBox2.add(a);
        fBox2.add(o);

        Box<Apple> aBox1 = new Box<>();
//        aBox1.add(f);
        aBox1.add(a);
//        aBox1.add(o);

        Box<Apple> aBox2 = new Box<>();
        aBox1.add(a);
        aBox1.add(a);

        Box<Orange> oBox = new Box<>();
//        oBox.add(f);
//        oBox.add(a);
        oBox.add(o);

        System.out.println(fBox1);
        System.out.println(fBox2);
        System.out.println(aBox1);
        System.out.println(aBox2);
        System.out.println(oBox);

        System.out.println(fBox1.getWeight());
        System.out.println(fBox2.getWeight());
        System.out.println(aBox1.getWeight());
        System.out.println(aBox2.getWeight());
        System.out.println(oBox.getWeight());

        System.out.println(fBox1.compare(fBox2));
        System.out.println(fBox1.compare(aBox1));
        System.out.println(oBox.compare(aBox1));

        fBox1.transfer(fBox2);
        System.out.println(fBox1);
        System.out.println(fBox2);
//        fBox1.transfer(aBox1);
        oBox.transfer(fBox2);
        System.out.println(oBox);
        System.out.println(fBox2);
    }
}
