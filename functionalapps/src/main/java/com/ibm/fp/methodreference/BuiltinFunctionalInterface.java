package com.ibm.fp.methodreference;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BuiltinFunctionalInterface {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> adder = Calculator::add;
        System.out.println(adder.apply(10, 10));

        Calculator calculator = new Calculator();

        BiFunction<Integer, Integer, Integer> subtracter = (a, b) -> calculator.subtract(a, b);
        System.out.println(subtracter.apply(10, 2));
        BiFunction<Integer, Integer, Integer> subtracterMethodRef = calculator::subtract;
        System.out.println(subtracterMethodRef.apply(10, 2));

        OrderService orderService = new OrderService();

        Supplier<Boolean> orderSupplier = null;

        orderSupplier = () -> orderService.isStockAvailable();
        System.out.println(orderSupplier.get());
        orderSupplier = orderService::isStockAvailable;
        System.out.println(orderSupplier.get());

        InventoryService inventoryService = new InventoryService();
        inventoryService.updateInventor(res -> {
            return "Inventory processed" + res;
        });
        inventoryService.updateInventor(BuiltinFunctionalInterface::process);
    }
    private static String process(boolean res) {
        return "Inventory processed" + res;
    }
}
