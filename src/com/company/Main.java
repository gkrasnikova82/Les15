package com.company;

//Реализовать класс корзины интернет магазина по следующему интерфейсу:

//interface Basket {

//void addProduct(String product, int quantity);

// void removeProduct(String product);

// void updateProductQuantity(String product, int quantity);

// void clear();

// List<String> getProducts();

// int getProductQuantity(String product);


import java.util.ArrayList;
import java.util.List;

interface Basket {
    void addProduct(String product, int quantity);

    void removeProduct(String product);

    void updateProductQuantity(String product, int quantity);

    void clear();

    List<String> getProducts();

    int getProductQuantity(String product);
}
class Score implements Basket {
    String shipment;
    int quantity;
    ArrayList<Score> Products = new ArrayList<>();

    public int GetIndexProduct(String product){ //поиск индекса продукта (1-е совпадение)
        Score P = new Score();
        int k = 0;
        for (int i = 0; i < Products.size(); i++) {
            P = Products.get(i);
            if (P.shipment == product) {
                k = i;
                i=Products.size();
            }
        }
        return k;
    }

    @Override
    public void addProduct(String product, int quantity) {
        Score P = new Score();// добавить конструктор
        P.shipment = product;
        P.quantity = quantity;
        Products.add(P);
    }

    @Override
    public void removeProduct(String product) {
        try {
            Products.remove(GetIndexProduct(product));
            System.out.println("Из корзины удален продукт "+ product);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Продукт не найден");
        }
    }

    @Override
    public void updateProductQuantity(String product, int quantity) {
        Score P = new Score();
        try {
            P = Products.get(GetIndexProduct(product));
            P.quantity = quantity;
            System.out.println("Изменено количество продукта " +product+" на "+quantity);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Продукт не найден");
        }
    }

    @Override
    public void clear() {
        Products.clear();
    }

    @Override
    public List<String> getProducts() {
        List<String> list = new ArrayList<String>();
        for (Score s: Products) {
            list.add(s.shipment);
        }
        return list;
    }

    @Override
    public int getProductQuantity(String product) {
        Score P = new Score();
        try {
            P = Products.get(GetIndexProduct(product));
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Продукт не найден");
        }
        return P.quantity;
    }
}


public class Main {

    public static void main(String[] args) {

        Score scores = new Score();

        scores.addProduct("milk", 1);
        scores.addProduct("bear", 3);
        scores.addProduct("egg", 2);
        System.out.println("Текущее состояние корзины: ");
        for (Score s : scores.Products) {
            System.out.println(s.shipment + " " + s.quantity);
        }

        scores.removeProduct("bear");

        System.out.println("Количество продукта egg  = "+ scores.getProductQuantity("egg"));
        scores.updateProductQuantity("egg", 3);

        System.out.println("Текущее состояние корзины: ");
        for (Score s : scores.Products) {// выводим список продуктов
            System.out.println(s.shipment + " " + s.quantity);
        }

        System.out.println("Получение списка продуктов в корзине");
        System.out.println(scores.getProducts());

        scores.clear();
        System.out.println("Выполнена очистка корзины");
        for (Score s : scores.Products) {
            System.out.println(s.shipment + " " + s.quantity);
        }

    }
}