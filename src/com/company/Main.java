package com.company;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("Bread",0.86,100);
        stockList.addStock(temp);

        temp = new StockItem("Cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("Cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("Car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("Chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("Cup", 0.5, 200);
        stockList.addStock(temp);

        temp = new StockItem("Cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("Door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("Juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("Phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("Towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("Vase", 8.76, 40);
        stockList.addStock(temp);
        System.out.println(stockList);
//        for(String s : stockList.items().keySet()) {
//            System.out.println(s);
//        }
        Basket basket = new Basket("Andrey");
        sellItem(basket,"Vase",12);
        sellItem(basket,"Towel",12);
        sellItem(basket,"Door",2);
        sellItem(basket,"Vase",11);
        removeItem(basket,"Vase",23);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

//            temp = new StockItem("pen",1.12);
//            stockList.items().put(temp.getName(),temp);
        stockList.items().get("Car").adjustStock(200);
        stockList.get("Car").adjustStock(-100);
        System.out.println(stockList);
        for(Map.Entry<String, Double> price : stockList.priceList().entrySet()) {
            System.out.println(price.getKey() + " costs " + price.getValue());
        }
    }
    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list;
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem,quantity);
        }
        return 0;
    }
    public static int removeItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list;
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unReserveStock(item,quantity);
        }
        return 0;
    }
    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.items().entrySet()) {
            stockList.sellStock(item.getKey().getName(),item.getValue());
        }
        basket.clearBasket();
    }
}
