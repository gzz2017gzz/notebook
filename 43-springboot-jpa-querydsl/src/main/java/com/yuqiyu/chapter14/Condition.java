package com.yuqiyu.chapter14;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class Condition {
	// 查询条件集合
	private List<BooleanExpression> expressions = new ArrayList<>();
 

	/**
	 * 添加查询条件到Query内的查询集合内
	 * @param expression 查询条件继承BooleanExpression抽象对象的具体实体对象,querydsl已经封装
	 */
	public Condition putExpression(BooleanExpression expression) {
		// 添加到条件集合
		expressions.add(expression);
		return this;
	}

	/**
	 * 构建出查询findAll函数使用的Predicate接口查询对象<br>
	 * 调用buidleQuery()可直接作为findAll参数查询条件使用
	 */
	public Predicate buidlCondition() {
		// 第一个查询条件对象
		BooleanExpression be = null;
		// 遍历所有查询条件，以第一个开始and
		for (int i = 0; i < expressions.size(); i++) {
			if (i == 0)
				be = expressions.get(i);
			else
				be = be.and(expressions.get(i));
		}
		return be;
	}

	/**
	 * 将Iterable集合转换成ArrayList集合
	 * 
	 * @param iterable 源集合
	 * @param <T>      类型
	 * @return arrayList结果
	 */
	public <T> List<T> iteratorToList(Iterable<T> iterable) {
		List<T> returnList = new ArrayList<T>();
		iterable.forEach(i -> returnList.add(i));
		return returnList;
	}
}
