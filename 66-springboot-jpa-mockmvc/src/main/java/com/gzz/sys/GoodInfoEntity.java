package com.gzz.sys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品基本信息实体
 */
@Data
@Entity
@Table(name = "good_infos")
public class GoodInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 商品编号
	@Id
	@Column(name = "tg_id")
	@GeneratedValue
	private Integer tgId;

	// 商品类型编号
	@Column(name = "tg_type_id")
	private Integer typeId;

	// 商品标题
	@Column(name = "tg_title")
	private String title;

	// 商品价格
	@Column(name = "tg_price")
	private double price;

	// 商品排序
	@Column(name = "tg_order")
	private int order;
}
