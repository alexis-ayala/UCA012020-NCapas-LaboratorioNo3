package com.uca.capas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Product;

@Controller
public class ProductController {
	private List<Product> products = new ArrayList<Product>();
	
	@GetMapping("/productos")
	public ModelAndView productos() {
		ModelAndView mav = new ModelAndView("productos");
		mav.addObject("product", new Product());
		
		products.add(new Product(0,"Coca Cola",2));
		products.add(new Product(1,"Cerveza",10));
		products.add(new Product(2,"Agua",15));
		products.add(new Product(3,"Cafe",5));
		products.add(new Product(4,"Refresco",16));
		
		mav.addObject("producto", products);
		
		return mav;
	}
	
	@PostMapping("/validar")
	public ModelAndView validar(Product product) {
		Product prod = products.get(product.getId());
		ModelAndView mav = new ModelAndView();
		if(product.getCantidad()!=null) {
			mav.addObject("nombre", prod.getNombre());
			if(prod.getCantidad()<product.getCantidad()) {
				mav.setViewName("error");
			}else {
				mav.setViewName("compra");
			}
		}else {
			mav.addObject("producto", products);
			mav.setViewName("productos");
		}
		
		return mav;
	}
	
}
