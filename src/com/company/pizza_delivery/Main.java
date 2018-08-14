package com.company.pizza_delivery;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        PizzaProcessing pizzaDelivery = new PizzaProcessing();

        Thread customer = new Thread(() -> pizzaDelivery.eatPizza());
        customer.start();

//        Thread.sleep(1000);
//        System.out.println("Секунду спустя клиенту надоело ждать...");
//        customer.interrupt();


        Thread.sleep(5000);

        Thread delivery = new Thread(() -> pizzaDelivery.deliverPizza());
        delivery.start();
    }
}
