package com.company.baking_factory;

public class Main {

    //Блокирующая очередь
    public static void main(String[] args) {
        //Producer/Consumer

        //Producer - что-то производит/генерирует/передает
        //Consumer - читает/сохраняет/обрабатывает

        //Очень медленный producer
        //много/быстрый consumer

        DeliveryBuffer deliveryBuffer = new DeliveryBuffer();

        new BreadFactory("Tel-Aviv", deliveryBuffer, 10_000, 3000).start();
        new BreadFactory("Raanana", deliveryBuffer, 20_000, 800).start();
        new BreadFactory("Kfar Saba", deliveryBuffer, 30_000, 2500).start();
        new BreadFactory("Haifa", deliveryBuffer, 40_000, 3000).start();

        new Customer("Rami Levi", deliveryBuffer).start();
        new Customer("Shufersal", deliveryBuffer).start();
        new Customer("Tiv Tam", deliveryBuffer).start();
        new Customer("Yainot Bitan", deliveryBuffer).start();
        new Customer("Coffix", deliveryBuffer).start();
        new Customer("Victory", deliveryBuffer).start();
    }
}
