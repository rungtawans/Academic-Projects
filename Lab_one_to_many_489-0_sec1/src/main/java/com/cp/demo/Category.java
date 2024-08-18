package com.cp.demo;

 

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 

@Entity // This tells Hibernate to make a table out of this class
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
 	private Integer id;
    @Column(length=45, nullable=false, unique=true)
	private String name;
	 
    public Integer getId() {
		return id;
	}
	public void setId(Integer category_id) {
		this.id = category_id;
	}

	@OneToMany(targetEntity=Product.class, mappedBy="category",
    		cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    public List<Product> getProducts(){
    	return products;
    };
    public void setProducts(List<Product> p) {
    	this.products=p;
    }
	public Category() {
		super();
	}

	public Category(String name2) {
		this.name=name2;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
}
