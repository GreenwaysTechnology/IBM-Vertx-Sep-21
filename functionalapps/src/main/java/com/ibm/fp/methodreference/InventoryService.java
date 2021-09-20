package com.ibm.fp.methodreference;

public class InventoryService {

    public void updateInventor(InventoryProcessor processor) {
        System.out.println(processor.process(true));
    }
}
