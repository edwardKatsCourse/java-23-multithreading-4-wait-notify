package com.company.baking_factory;

public class DeliveryBuffer {

    private Integer breadFromFactory;

    public synchronized void breadFactory(Integer breadFromFactory) throws InterruptedException {
        while (this.breadFromFactory != null) {
            this.wait();
        }

        this.breadFromFactory = breadFromFactory;

        //Уведомляем всех, кто ждет хлеб, что можно прийти забирать
        this.notifyAll();

    }

    public synchronized Integer customer() throws InterruptedException {

        //пока нечего отдавать клиету - жду и кручусь в цикле
        while (this.breadFromFactory == null) {

            //жду, когда хлеб привезут с фабрики
            this.wait();
        }


        //Если попали в эту точку - значит хлеб УЖЕ пришел с фабрики и его можно отдавать клиенту
        Integer bread = this.breadFromFactory;

        //Выгрузить хдеб из грузовика
        this.breadFromFactory = null;


        //всем, кто ждал, пока освободится грузовик - ЗАНОСИТЕ НОВЫЙ ХЛЕБ
        this.notifyAll();

        //Отдать хлеб клиенту
        return bread;
    }
}
