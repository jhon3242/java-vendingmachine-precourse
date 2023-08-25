package vendingmachine.domain;

public class Product {
	private String name;
	private int cost;
	private int count;

	public Product(String name, int cost, int count) {
		validate(name, cost, count);
		this.name = name;
		this.cost = cost;
		this.count = count;
	}

	private void validate(String name, int cost, int count) {

	}
}
