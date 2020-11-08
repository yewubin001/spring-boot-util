package com.example.springboot.design.decorator;

public class Casquette extends HatDecorator {

    Person person;

    public Casquette(Person person) {
        this.person = person;
    }
    @Override
    public String getDescription() {
        //鸭舌帽
        return person.getDescription() + "a casquette ";
    }

    @Override
    public double cost() {
        return 75 + person.cost();
    }

}