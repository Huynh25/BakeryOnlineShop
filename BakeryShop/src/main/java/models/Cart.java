/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for Cart purpose. Cart include items.
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String text, List<Cake> cakeList, List<Topping> toppingList) {
        items = new ArrayList<>();
        if (text != null && !text.isEmpty()) {
            String[] itemStrings = text.split(";");
            for (String itemString : itemStrings) {
                try {
                    String[] itemPart = itemString.split(":");
                    int id = Integer.parseInt(itemPart[0]);
                    int quantity = Integer.parseInt(itemPart[1]);
                    String[] toppingStrings = itemPart[2].split(",");
                    Cake cake = getCakeByID(id, cakeList);
                    List<Topping> toppings = new ArrayList<>();
                    List<Integer> toppingsBuyQuantity = new ArrayList<>();
                    for (String toppingString : toppingStrings) {
                        toppings.add(getToppingByID(Integer.parseInt(toppingString), toppingList));
                        toppingsBuyQuantity.add(quantity);
                    }
                    Item item = new Item(cake, toppings, toppingsBuyQuantity, quantity);
                    addItem(item);
                } catch (Exception e) {

                }

            }
        }
    }

    private Cake getCakeByID(int id, List<Cake> list) {
        for (Cake cake : list) {
            if (cake.getCakeID() == id) {
                return cake;
            }
        }

        return null;
    }

    private Topping getToppingByID(int id, List<Topping> list) {
        for (Topping topping : list) {
            if (topping.getToppingID() == id) {
                return topping;
            }
        }

        return null;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getBuyQuantityByItem(Item findItem) {
        return getItemInCart(findItem).getBuyQuantity();
    }

    public Item getItemInCart(Item findItem) {
        for (Item item : items) {
            if (item.equals(findItem)) {
                return item;
            }
        }

        return null;
    }

    public void addItem(Item item) {
        Item itemInCart = getItemInCart(item);

        if (itemInCart != null) {
            itemInCart.setBuyQuantity(itemInCart.getBuyQuantity() + item.getBuyQuantity());
        } else {
            items.add(item);
        }

    }

    public void removeItem(Item item) {
        Item removeItem = getItemInCart(item);
        if (removeItem != null) {
            items.remove(removeItem);
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Item item : items) {
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;

        for (Item item : items) {
            totalQuantity += item.getBuyQuantity();
        }

        return totalQuantity;
    }

    @Override
    public String toString() {
        String cartString = "";
        for (Item item : items) {
            cartString += item.toString();
        }

        return cartString;
    }

}
