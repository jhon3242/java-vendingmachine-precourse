package vendingmachine.domain;

import java.util.Comparator;
import java.util.Map;

public class ProductRepository {
	private Map<String, Product> repository;

	public ProductRepository(Map<String, Product> repository) {
		this.repository = repository;
	}

	public Product getMinCostProduct() {
		return repository.values().stream()
				.min(Comparator.comparing(Product::getCost))
				.orElse(null);
	}


	public Product findByNameForPurchase(String productName) {
		validateExist(productName);
		Product product = repository.get(productName);
		validateCount(product);
		return product;
	}

	private void validateCount(Product product) {
		if (product.getCount() <= 0) {
			throw new IllegalArgumentException("해당 상품은 품절입니다.");
		}
	}

	private void validateExist(String productName) {
		if (!repository.containsKey(productName)) {
			throw new IllegalArgumentException("존재하지 않는 상품명입니다.");
		}
	}
}
