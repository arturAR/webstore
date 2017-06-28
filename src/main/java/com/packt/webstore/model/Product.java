package com.packt.webstore.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement 
@Entity
@Table(name = "products")
public class Product extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3678107792576131001L;
	
	@Pattern(regexp="[A-Z]+[0-9]+", message="{Pattern.Product.productCode.validation}")
	@Size(min=9, max=9)
	@NotNull
	private String productCode;
	@Size(min=4, max=50, message="{Size.Product.name.validation}")
	private String name;
	@Min(value=0, message="Min.Product.unitProce.validation}")
	@Digits(integer=8, fraction=2, message="{Digits.Product.unitPrice.validation}")
	@NotNull(message="{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice;
	@Size(max=555)
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private String productCondition;
	@JsonIgnore
	@Transient
	private MultipartFile productImage;

	public Product() {
		super();
}

	public Product(String productCode, String name, BigDecimal unitPrice) {
		this.productCode = productCode;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getCondition() {
		return productCondition;
	}

	public void setCondition(String condition) {
		this.productCondition = condition;
	}

	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", name=" + name + "]";
	}
}