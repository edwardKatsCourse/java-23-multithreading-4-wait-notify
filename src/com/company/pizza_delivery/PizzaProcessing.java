package com.company.pizza_delivery;

public class PizzaProcessing {

    private boolean isPizzaDelivered = false;

    public void eatPizza() {
        synchronized (this) {
            while (!isPizzaDelivered) {
                System.out.println("Клиент: Жду пиццу!");
                try {
                    this.wait();
                } catch (InterruptedException e) {

                    System.out.println("Клиент: Не сумел дождаться");
                }
            }

            System.out.println("Клиент: Наконец-то. Пойду точить пиццу!");
        }
    }

    public void deliverPizza() {
        System.out.println("Доставка: пытаюсь доставить пиццу");
        synchronized (this) {
            System.out.println("Доставка: Ваша пицца приехала! Где мои чаевые? ");
            this.isPizzaDelivered = true;
            this.notifyAll();
        }

    }
}
