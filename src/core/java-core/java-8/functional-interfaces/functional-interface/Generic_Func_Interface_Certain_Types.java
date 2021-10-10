package com.manhpd;

public class Generic_Func_Interface_Certain_Types {

	public static void main(String[] args) {

		ReceiptPrinter<Receipt> simpleReceiptPrinter = new ReceiptPrinter<>() {

			@Override
			public void print(Receipt receipt) {
				System.out.println("\nItem: \t" + receipt.item);
				System.out.println("\nPrice: \t" + receipt.price);
				System.out.println("\nDisc: \t" + receipt.discount);
				System.out.println("\nTax: \t" + receipt.tax);
				System.out.println("\nTotal: \t" + computeTotal(receipt));
				System.out.println("\n");
			}
		};

		ReceiptPrinter<CountyReceipt> countyReceiptPrinter = new ReceiptPrinter<>() {

			@Override
			public void print(CountyReceipt receipt) {
				System.out.println("\nItem: \t" + receipt.item);
				System.out.println("\nPrice: \t" + receipt.price);
				System.out.println("\nDisc: \t" + receipt.discount);
				System.out.println("\nTax: \t" + receipt.tax);
				System.out.println("\nTotal: \t" + computeTotal(receipt));
				System.out.println("\n");
			}

			@Override
			public double computeTotal(CountyReceipt receipt) {
				double discountedPrice = receipt.price - (receipt.price * receipt.discount);
				return discountedPrice + (discountedPrice * receipt.tax) + (discountedPrice * receipt.countyTax);
			}
		};

		Receipt receipt = new Receipt("shirt", 20.00, 0.05, 0.07);
		System.out.println("If we use normal tax, we have: ");
		simpleReceiptPrinter.print(receipt);

		CountyReceipt countyReceipt = new CountyReceipt(receipt, 0.04);
		System.out.println("If we use county tax, we have: ");
		countyReceiptPrinter.print(countyReceipt);
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

class CountyReceipt extends Receipt {

	public double countyTax;

	public CountyReceipt(Receipt r, double c) {
		super(r);
		this.countyTax = c;
	}

}

@FunctionalInterface
interface ReceiptPrinter<X extends Receipt> {
	void print(X receipt);

	private double getDiscountedPrice(X receipt) {
		return receipt.price - (receipt.price * receipt.discount);
	}

	default double computeTotal(X receipt) {
		double discountedPrice = this.getDiscountedPrice(receipt);
		return discountedPrice + (discountedPrice * receipt.tax);
	}
}