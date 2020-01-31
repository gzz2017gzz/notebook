package com.yuqiyu.chapter14.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "good_infos")
public class GoodEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "tg_id")
	private Long id;

	@Column(name = "tg_title")
	private String title;

	@Column(name = "tg_price")
	private double price;

	@Column(name = "tg_unit")
	private String unit;

	@Column(name = "tg_order")
	private int order;

	@OneToOne
	@JoinColumn(name = "tg_type_id")
	private GoodTypeEntity type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public GoodTypeEntity getType() {
		return type;
	}

	public void setType(GoodTypeEntity type) {
		this.type = type;
	}
}
