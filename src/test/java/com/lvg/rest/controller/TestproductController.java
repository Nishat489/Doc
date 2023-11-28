package com.lvg.rest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.lvg.rest.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lvg.rest.controller.ProductController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.lvg.rest.service.ProductService;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.ResultActions;
@WebMvcTest(ProductController.class)
class TestproductController {
	
	@MockBean
	ProductService productService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	
	public void getAllProductTest() throws Exception {
		
		List<Product> plist = Arrays.asList(new Product(7001, "Lux soap", "Bath Soap",25,50), 
											new Product(7002, "Close Up", "Tooth Paste ",15,150));
		when(productService.getAllProducts()).thenReturn(plist);
		mockMvc.perform(get("/product")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()); 
		
	}
	
	@Test
	public void addProductDetailsTest() throws JsonProcessingException, Exception {
		
		Product product = new Product(7001,"Lux Soap","Bath Soap",25,50);
		when(productService.insertOrModifyProduct(product)).thenReturn(true);
		mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(product)));
	}
	
	@Test void deletProductByIdTest() throws Exception  {	
		int productId = 7001;
		when(productService.deleteProductById(productId)).thenReturn(true);
		mockMvc.perform(delete("/product/{productId}",productId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}