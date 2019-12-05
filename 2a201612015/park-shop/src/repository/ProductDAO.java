package repository;

import domain.Product;

public interface ProductDAO {
	
	// INSERT (or CREATE)
	int create(Product p);
	
	// SELECT
	Product readList(String p);
	
	// UPDATE
	int update(Product p);
	
	// DELETE (or DROP table...)
	int delete(Product p);
}
