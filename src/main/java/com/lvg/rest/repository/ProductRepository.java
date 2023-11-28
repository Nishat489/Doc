package com.lvg.rest.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lvg.rest.entity.Product;
public interface ProductRepository extends JpaRepository<Product,Integer>
{
    Optional<Product> findByProductName(String productName);

	Object save(com.lvg.rest.dto.Product prod);
}
