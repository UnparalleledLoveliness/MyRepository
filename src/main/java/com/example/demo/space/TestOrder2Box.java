package com.example.demo.space;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOrder2Box {
	@SuppressWarnings("serial")
	public static void main(String[] args) {

		// 假设仓库有两款箱子(谁排前面优先用谁)
		List<Map<String, Object>> boxes = new ArrayList<Map<String, Object>>() {
			{

				// 盒子 1x1x1
				this.add(new HashMap<String, Object>() {
					{

						this.put("id", "1");// 盒子ID
						this.put("code", "1");// 盒子CODE
						this.put("title", "1立方的盒子");// 盒子名称
						this.put("l", 100.00d); // 盒子高度
						this.put("w", 100.00d);// 盒子宽度
						this.put("h", 100.00d);// 盒子高度
					}
				});

				// 盒子 1x1x2
				this.add(new HashMap<String, Object>() {
					{

						this.put("id", "2");// 盒子ID
						this.put("code", "2");// 盒子CODE
						this.put("title", "2立方的盒子");// 盒子名称
						this.put("l", 100.00d); // 盒子高度
						this.put("w", 100.00d);// 盒子宽度
						this.put("h", 200.00d);// 盒子高度
					}
				});

			}

		};

		// 订单中的商品列表
		List<Map<String, Object>> order = new ArrayList<Map<String, Object>>() {
			{
				// 商品也是1立方，1x1x1
				this.add(new HashMap<String, Object>() {
					{
						this.put("sku", "222");// 商品SKUID
						this.put("title", "1立方商品2件");// 商品名称
						this.put("l", 100.00d); // 商品长度
						this.put("w", 100.00d); // 商品宽度
						this.put("h", 100.00d); // 商品高度
						this.put("n", 2); // 该商品数量
						this.put("t", 1);// 0,可平躺可码放不可倒置；1，不可平躺可码放不可倒置；2,不可平躺不可码放不可倒置
					}
				});
			}
		};

		// 将商品装入盒中
		GoodsInBox gb = new GoodsInBox(boxes, order);

		// 得到需要盒子的数量
		Map<String, Integer> boxInfo = gb.getBoxes();
		for (Object k : boxInfo.keySet()) {
			System.out.print("该订单商品需要");
			System.out.print(k.toString());
			System.out.print("（ID）号箱：");
			System.out.print(boxInfo.get(k));
			System.out.print("只。");
		}

	}

}
