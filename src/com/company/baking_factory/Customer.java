package com.company.baking_factory;

public class Customer extends Thread {

    private DeliveryBuffer deliveryBuffer;

    public Customer(String name, DeliveryBuffer deliveryBuffer) {
        super(name);
        this.deliveryBuffer = deliveryBuffer;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Integer breadId = deliveryBuffer.customer();
                System.out.printf("Хлеб с ID %s был продан %s\n", breadId, this.getName());
            } catch (InterruptedException e) {
                System.out.println("Клиент сломался. Бизнес закрылся");
                break;
            }
        }
    }
}
