package com.lvg.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.lvg.rest.entity.Product;
import com.lvg.rest.repository.ProductRepository;
import com.lvg.rest.controller.ProductController;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.lvg.rest.service.ProductService;
//@RunWitj(SpringRunner.class)
@SpringBootTest
class FirstRestAppApplicationTests {
	@Autowired
	ProductService productService;
	
	//To use Mock object of the Repository\
	@MockBean
	ProductRepository productRepository;
	@Test
	void getAllProductsTest() {
	
		List<Product> plist = Arrays.asList(new Product(7001, "Lux soap", "Bath Soap",25,50), 
				new Product(7002, "Close Up", "Tooth Paste ",15,150));
		when(productRepository.findAll()).thenReturn(plist);
		assertEquals(2,productService.getAllProducts().size());
	}
@Test
public void insertOrModifyTest()
{
	Product prod = new Product(7002, "Close Up", "Tooth Paste",15,150);
	when(productRepository.save(prod)).thenReturn(prod);
	assertEquals(true,productService.insertOrModifyProduct(prod));
	
}

@Test 
public void deletProductById() {
	int productId = 7001;
//	doNothing.when(productRepository.deleteAllById(productId));
}
}
