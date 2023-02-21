package com.zos.modal;

import ch.qos.logback.core.subst.Token.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String brand;
	private String title;
	private String category;
	private String subCategory;
	private Integer price;
	private Integer offPrice;
	private Integer discount;
	
//	private
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
