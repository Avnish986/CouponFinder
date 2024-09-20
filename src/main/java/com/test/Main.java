package com.test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;


class Main {

    public static class Categories {
        public String CategoryName;
        public String CategoryParentName;

        public Categories(String CategoryName, String CategoryParentName) {
            this.CategoryName = CategoryName;
            this.CategoryParentName = CategoryParentName;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public String getCategoryParentName() {
            return CategoryParentName;
        }

        public void setCategoryParentName(String categoryParentName) {
            CategoryParentName = categoryParentName;
        }

    }

    public static class Coupon {
        public String CouponName;
        public String CategoryName;

        public Coupon(String CategoryName, String CouponName) {
            this.CategoryName = CategoryName;
            this.CouponName = CouponName;
        }
    }

    public static String findCoupon(String category, List<Categories> list, Map<String, String> mp) {
        String res = "";
        Categories c = null;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getCategoryName().equalsIgnoreCase(category)) {
                System.out.println("Found");
                c = list.get(i);
                break;
            }
        }
        if(c != null ){
            String categoryToCheck = c.getCategoryName().toLowerCase();
            Set<Map.Entry<String, String>> values = mp.entrySet();
            for(Map.Entry<String, String> e : values) {
                if(e.getKey().equalsIgnoreCase(categoryToCheck)) {
                    res = e.getValue();
                    return res;
                }
            }
            res = findCoupon(c.getCategoryParentName(), list, mp);
            if(res == null) {
                return "";
            }

        }

        return res;
    }

    public static void main(String[] args) {
        List<Categories> list = new ArrayList<>();
        Categories categories1 = new Categories("Comforter Sets", "Bedding");
        Categories categories2 = new Categories("Bedding", "Bed & Bath");
        Categories categories3 = new Categories("Bed & Bath", null);
        Categories categories4 = new Categories("Soap Dispensers", "Bathroom Accessories");
        Categories categories5 = new Categories("Bathroom Accessories", "Bed & Bath");
        Categories categories6 = new Categories("Toy Organizers", "Baby And Kids");
        Categories categories7 = new Categories("Bay And Kids", null);
        list.add(categories1);
        list.add(categories2);
        list.add(categories3);
        list.add(categories4);
        list.add(categories4);
        list.add(categories5);
        list.add(categories6);
        list.add(categories7);

        Map<String, String> map = new HashMap<>();
        map.put("comforter Sets".toLowerCase(), "Comforters Sale");
        map.put("Bedding".toLowerCase(), "Savings on Bedding");
        map.put("Bed & Bath".toLowerCase(), "Low price for Bed & Bath");
        Set<Map.Entry<String, String>> values = map.entrySet();
        for(Map.Entry<String, String> entry : values) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        //System.out.println(findCoupon("Comforter Sets", list, map));
//Toy Organizers
        System.out.println(findCoupon("Comforter Sets", list, map));


    }
}