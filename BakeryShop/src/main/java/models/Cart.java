/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import daos.GoWithDAO;
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
        int totalCakeIDInCart = 0;
        for (Item i : items) {
            if (item.getCake().getCakeID() == i.getCake().getCakeID()) {
                totalCakeIDInCart += i.getBuyQuantity();
            }
        }

        if (itemInCart == null) {
            if (item.getCake().getCakeQuantity() - totalCakeIDInCart - item.getBuyQuantity() >= 0) {
                items.add(item);
            }
            return;
        }

        int quantityCouldBuy = item.getBuyQuantity();
        //Get list of toppings that go with the cake
        GoWithDAO goWithDAO = new GoWithDAO();

        List<GoWith> goWithList = goWithDAO.findAllByCakeID(item.getCake().getCakeID());
        List<Topping> toppings = new ArrayList<>();
        List<Topping> toppingsInItem = item.getToppings();

        for (GoWith goWith : goWithList) {
            for (Topping topping : toppingsInItem) {
                if (topping.getToppingID() == goWith.getTopping().getToppingID()) {
                    toppings.add(goWith.getTopping());
                }
            }
        }

        for (Topping topping : toppings) {
            int buyQuantity = 0;
            for (Item cartItem : items) {
                for (Topping toppingItem : cartItem.getToppings()) {
                    if (topping.getToppingID() == toppingItem.getToppingID()) {
                        buyQuantity += cartItem.getBuyQuantity();
                        break;
                    }
                }
            }
            topping.setToppingQuantity(topping.getToppingQuantity() - buyQuantity);
        }

        int maxQuantityTopping = 0;
        int length = toppings.size();
        for (int i = 0; i < length; ++i) {
            if (i == 0) {
                maxQuantityTopping = toppings.get(0).getToppingQuantity();
            } else if (maxQuantityTopping > toppings.get(i).getToppingQuantity()) {
                maxQuantityTopping = toppings.get(i).getToppingQuantity();
            }
        }

        if (quantityCouldBuy > maxQuantityTopping) {
            quantityCouldBuy = maxQuantityTopping;
        }

        itemInCart.setBuyQuantity(itemInCart.getBuyQuantity() + quantityCouldBuy);
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
