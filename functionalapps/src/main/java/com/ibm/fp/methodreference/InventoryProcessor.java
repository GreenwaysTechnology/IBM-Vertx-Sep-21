package com.ibm.fp.methodreference;

@FunctionalInterface
interface InventoryProcessor {
    String process(boolean isStockAvailable);
}