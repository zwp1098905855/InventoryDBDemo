/**
 * 
 */
package com.zhiyou.indemo.test;

import java.util.ArrayList;
import java.util.List;

import com.zhiyou.indemo.bean.Goods;
import com.zhiyou.indemo.constants.DBKeys;
import com.zhiyou.indemo.dao.DaoGoods;

/**
 * @author longWH
 *测试数据库
 */
public class TestSql {
	private static DaoGoods dao = new DaoGoods();

	public static void main(String[] args) {
		TestSql test = new TestSql();
		// test.testAdd(new Goods("a01","name01",1.54f,12,"brand01"));

		// test.testAddAll();

		// test.testDeleteById("b03");
		// test.testDeleteByConditon(" price>5");0
		// test.testDeleteByIds("c01","c02");
//		test.testUpdate(new String[] { DBKeys.name, DBKeys.brand }, new Object[] { "hahah", "中国移动" }, "price<3");
//		test.testQuerySomeColumns(new String[]{DBKeys.name,DBKeys.brand});
//		test.testQueryById("c06");
//		test.testQueryByCondition("brand='中国移动'");
		test.testQuerySomeColumnsByCondition(new String[]{DBKeys.name,DBKeys.brand}, "brand='中国移动'");
	}

	private void testCreate() {
		dao.creatTable();
	}

	private void testAdd(Goods goods) {
		dao.add(goods);
	}

	private void testAddAll() {
		List<Goods> list = new ArrayList<Goods>();
		for (int i = 0; i < 10; i++) {
			Goods goods = new Goods("c0" + i, "name0" + i, 1.54f + i, 12, "brand0" + i);
			list.add(goods);
		}
		dao.addAll(list);
	}

	private void testDeleteById(String id) {
		dao.deleteById(id);
	}

	private void testDeleteByIds(String... ids) {
		dao.deleteAllByIds(ids);
	}

	private void testDeleteByConditon(String condition) {
		dao.deleteByCondition(condition);
	}

	private void testUpdate(String[] keys, Object[] newValues, String whereClause) {
		dao.update(keys, newValues, whereClause);
	}

	private void testQueryById(String id) {
		Goods goods = dao.selectById(id);
		System.out.println(goods);
	}

	private void testQueryByCondition(String whereClause) {
		Goods goods = dao.selectByCondition(whereClause);
		System.out.println(goods);
	}

	private void testQuerySomeColumns(String... columns) {
		List<Goods> list = dao.selectAllColumns(Goods.class, columns);
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}

	private void testQuerySomeColumnsByCondition(String[] columns, String whereClause) {
		List<Goods> list = dao.selectAllColumns(Goods.class, columns, whereClause);
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}

}
