package com.manhpd;

public class Default_methods_Functional_Interface {

	public static void main(String[] args) {
		ReceiptPrinter simpleReceiptPrinter = new ReceiptPrinter() {

			@Override
			public void print(Receipt receipt) {
				System.out.println("\nItem: \t" + receipt.item);
				System.out.println("\nPrice: \t" + receipt.price);
				System.out.println("\nDisc: \t" + receipt.discount);
				System.out.println("\nTax: \t" + receipt.tax);
				System.out.println("\nTotal: \t" + computeTotal(receipt));
			}
		};

		ReceiptPrinter exemptReceiptPrinter = new ReceiptPrinter() {

			@Override
			public void print(Receipt receipt) {
				System.out.println("\nItem: \t" + receipt.item);
				System.out.println("\nPrice: \t" + receipt.price);
				System.out.println("\nDisc: \t" + receipt.discount);
				System.out.println("\nTax: \t" + receipt.tax);
				System.out.println("\nTotal: \t" + receipt);
			}

			@Override
			public double computeTotal(Receipt receipt) {
				return receipt.price - (receipt.price * receipt.discount);
			}
		};
		
		Receipt receipt = new Receipt("shirt", 20.00, 0.05, 0.07);
		simpleReceiptPrinter.print(receipt);
		exemptReceiptPrinter.print(receipt);
	}
}

class Receipt {

	public String item;

	public double price;

	public double discount;

	public double tax;

	public Receipt(String i, double a, double b, double s) {
		this.item = i;
		this.price = a;
		this.discount = b;
		this.tax = s;
	}

	public Receipt(Receipt r) {
		this.item = r.item;
		this.price = r.price;
		this.discount = r.discount;
		this.tax = r.tax;
	}
}

/**
 * A functional interface can provide default methods. An implemented class can
 * use the default methods or provide its own versions.
 *
 */
@FunctionalInterface
interface ReceiptPrinter {

	void print(Receipt receipt);

	private double getDiscountedPrice(Receipt receipt) {
		return receipt.price - (receipt.price * receipt.discount);
	}

	default double computeTotal(Receipt receipt) {
		double discountedPrice = getDiscountedPrice(receipt);
		return discountedPrice + (discountedPrice * receipt.tax);
	}
}
