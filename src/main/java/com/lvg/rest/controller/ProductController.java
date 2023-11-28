package com.lvg.rest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lvg.rest.entity.Product;
import com.lvg.rest.exception.ProductNotFoundException;
import com.lvg.rest.service.ProductService;

@RestController
@RequestMapping("/product")       
public class ProductController 
{
    @Autowired
    ProductService productService;
    
    @GetMapping(produces="application/json")
    public ResponseEntity<List<Product>> getAllProducts()
    {
         return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
    }
    
    @GetMapping(value="/{productId}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable int productId)
    {
    	return new ResponseEntity<Product>(productService.getProductByid(productId),HttpStatus.OK);
    } 
    
    @GetMapping(value="/productName/{productName}",produces="application/json")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName)
    {
    	return new ResponseEntity<Product>(productService.getProductByName(productName),HttpStatus.OK);
    }
    
    @PostMapping(consumes="application/json")
    public HttpStatus addProductDetails(@RequestBody Product product)
    {
        if(productService.insertOrModifyProduct(product))
        	return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }
    
    @PutMapping(consumes="application/json")
    public HttpStatus modifyProductDetails(@RequestBody Product product)
    {
        if(productService.insertOrModifyProduct(product))
        	return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }
    
    @DeleteMapping(value="/{productId}")
    public HttpStatus deleteProductById(@PathVariable int productId)
    {
    	productService.deleteProductById(productId);
        return HttpStatus.OK;
    }
    
    
    
   /* @ExceptionHandler(ProductNotFoundException.class)
    public HttpStatus productNotFoundExceptionhandler(ProductNotFoundException ex)
    {
    	System.out.println(ex);
    	return HttpStatus.NO_CONTENT;
    }*/
}


	