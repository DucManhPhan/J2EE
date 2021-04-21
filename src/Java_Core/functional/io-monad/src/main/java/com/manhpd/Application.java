package com.manhpd;

public class Application {
   public static void main(String[] args) {
       ProcessDataService.process("./files/sales.txt", "./files/discountedSales.txt")
                         .run();
    }
}
