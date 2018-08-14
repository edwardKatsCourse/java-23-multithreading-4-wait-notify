package com.company.baking_factory;

public class BreadFactory extends Thread {

    private DeliveryBuffer deliveryBuffer;
    private Integer breadStartId;
    private long sleepTime;

    public BreadFactory(String name, DeliveryBuffer deliveryBuffer, Integer breadStartId, long sleepTime) {
        super(name);
        this.deliveryBuffer = deliveryBuffer;
        this.breadStartId = breadStartId;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            Integer currentBreadId = breadStartId++;
            System.out.printf("Хлеб с ID %s был выпечен на %s фабрике\n", currentBreadId, this.getName());
            try {
                deliveryBuffer.breadFactory(currentBreadId);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Хлебо-станок сломался");
                break;
            }
        }
    }
}
