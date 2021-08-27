package com.example.demo.space;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsInBox {

  /* 箱子的型号,盛放空间 */
  private final List<Map<String, Object>> boxTypeList;
  /* 订单中的商品 */
  private final List<Map<String, Object>> goodsList;
  /* 箱子装东西的情况，key 规则：箱型_箱子id */
  private final Map<Object, Object> boxes = new HashMap<Object, Object>();

  public GoodsInBox(List<Map<String, Object>> boxTypeList, List<Map<String, Object>> goodsList) {
    this.boxTypeList = boxTypeList;
    this.goodsList = goodsList;
    // 开始执行
    run();
  }

  private void run() {
    for (final Map<String, Object> abox : boxTypeList) {
      tryInSpance(abox/* 给一个盒子 */, new java.util.ArrayList<Map<String, Object>>() {
				  {
					  this.add(abox);
				  }
			  }, false, (goodsList), boxTypeList);
    }
  }

  /*-
   * 每次测试1块空间，和全部商品，将商品依次向空间转移，放进去后产生新的3块空间， 同时商品的数量再减少，直到商品全部转移；
   * @param space1
   * @param gs
   * @return
   */
  private List<Map<String, Object>> tryInSpance(
      Map<String/* 长l宽w高h等 */, Object> box/* 某1个盒子 */, List<Map<String, Object>> moreSpance/* 某一个盒子的剩余空间 */,
      boolean boxIsFull, List<Map<String, Object>> moreGoods, List<Map<String, Object>> boxTypes /* 可用的箱子型号 */) {
    // 为新的箱子分配一个箱子的唯一id，箱子型號+uuid保證每一個箱子的id唯一。箱子被裝滿之後，箱子的屬性為滿。
    // 如果没有boxid表示是一个新的箱子
    // 如果之前的箱子满了也需要新的箱子
    if (null == box.get("boxid") || boxIsFull) {
      box.put("boxid", box.get("id").toString().concat("_").concat(java.util.UUID.randomUUID().toString()));
      moreSpance = new java.util.ArrayList<Map<String, Object>>();
      moreSpance.add(box);
    }

	  if (null == box || null == moreGoods || null == moreSpance) {
		  return null;
	  }
    // 是否有东西被装进去？
    boolean in = false;
    // 遍历这个箱子的剩余空间；
    loops:
    for (int bi = moreSpance.size() - 1; bi >= 0; bi--) {
      Map<String, Object> b = moreSpance.get(bi);
      if (null == b) {
        continue loops;
      }
      double bl = Double.valueOf(b.get("l").toString());
      double bw = Double.valueOf(b.get("w").toString());
      double bh = Double.valueOf(b.get("h").toString());

		if (bl <= 0 || bw <= 0 || bh <= 0) {
			continue;
		}

      // 遍历未装箱的商品列表
      loopg:
      for (int gi = moreGoods.size() - 1; gi >= 0; gi--) {
        Map<String, Object> g = moreGoods.get(gi);
		  if (null == g.get("n") || null == g.get("sku")) {
			  continue;
		  }
        Integer sku = Integer.valueOf(g.get("sku").toString());
        // 商品数量
        Integer num = Integer.valueOf(g.get("n").toString());
        if (0 == num) {
          moreGoods.remove(gi);
          // 继续下一个商品
          continue loopg;
        }
        // boolean goodsinbox = false;
        // 多少件商品就循环多少次，每次处理一件；
        loopn:
        for (int i = num; i >= 0; i--) {
          String code = box.get("code").toString().concat(",").concat(sku.toString());
          double gl = Double.valueOf(g.get("l").toString());
          double gw = Double.valueOf(g.get("w").toString());
          double gh = Double.valueOf(g.get("h").toString());
          Integer t = Integer.valueOf(g.get("t").toString()); // 0,可平躺可码放不可倒置；1，不可平躺可码放不可倒置；2,不可平躺不可码放不可倒置

          // 正面放置商品
          if ((bl - gl) >= 0d && (bw - gw) >= 0d && (bh - gh) >= 0d) {
            // 这件商品被装进了这个盒子；是正着被放进去的。
            g.put("boxid", box.get("boxid"));
            // 商品的数量要减少一个
            g.put("n", i - 1);
            // 剩余空间需要减少一个
            moreSpance.remove(bi);
            in = true;
            boxes.put(box.get("boxid"), g);

            // 正放的3块剩余空间
            if (2 != t.intValue() && bh - gh > 0d) {
              Map<String, Object> leftover;
              // 第一块空间 (盒子上面的剩余空间) bh - gh 高度相减表示盒子正上方
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));
              leftover.put("l", gl);
              leftover.put("w", gw);
              leftover.put("h", bh - gh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第二块空间
            if (bw - gw > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", gl);
              leftover.put("w", bw - gw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);

            }
            // 第三块空间
            if (bl - gl > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bl - gl);
              leftover.put("w", bw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }

            tryInSpance(box, moreSpance, moreSpance.isEmpty(), moreGoods, boxTypes);
            return moreSpance;

            // 侧面放置商品
          } else if ((bl - gw) >= 0d && (bw - gl) >= 0d && (bh - gh) >= 0d) {
            // 可以放入的情况下先减少商品的数量；
            // 这件商品被装进了这个盒子；
            g.put("boxid", box.get("boxid"));
            // 商品的数量要减少一个
            g.put("n", i - 1);
            // 剩余空间需要减少一个
            moreSpance.remove(bi);
            in = true;
            boxes.put(box.get("boxid"), g);
            // 侧放的3块剩余空间
            if (2 != t.intValue() && bh - gh > 0d) {
              Map<String, Object> leftover;
              // 第一块空间
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", gl);
              leftover.put("w", gw);
              leftover.put("h", bh - gh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第二块空间
            if (bw - gl > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bw - gl);
              leftover.put("w", gw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第三块空间
            if (bl - gw > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bl - gw);
              leftover.put("w", bw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }

            tryInSpance(box, moreSpance, moreSpance.isEmpty(), moreGoods, boxTypes);
            return moreSpance;

            // 卧倒放置商品
          } else if (t.intValue() == 0d && (bl - gh) >= 0d && (bw - gw) >= 0d && (bw - gl) >= 0d) {
            // 这件商品被装进了这个盒子；
            g.put("boxid", box.get("boxid"));
            // 商品的数量要减少一个
            g.put("n", i - 1);
            // 剩余空间需要减少一个
            moreSpance.remove(bi);
            in = true;
            boxes.put(box.get("boxid"), g);
            // 侧放的3块剩余空间

            if (2 != t.intValue() && bh - gh > 0d) {
              // 第一块空间
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", gh);
              leftover.put("w", gw);
              leftover.put("h", bh - gh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第二块空间
            if (bw - gw > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bw - gw);
              leftover.put("w", gh);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第三块空间
            if (bl - gh > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bl - gh);
              leftover.put("w", bw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }

            tryInSpance(box, moreSpance, moreSpance.isEmpty(), moreGoods, boxTypes);
            return moreSpance;
            // 侧卧放置商品
          } else if (t.intValue() == 0d && (bl - gw) >= 0d && (bh - gl) >= 0d && (bw - gh) >= 0d) {
            // 这件商品被装进了这个盒子；
            g.put("boxid", box.get("boxid"));
            // 商品的数量要减少一个
            g.put("n", i - 1);
            // 剩余空间需要减少一个
            moreSpance.remove(bi);
            in = true;
            boxes.put(box.get("boxid"), g);
            // 侧放的3块剩余空间

            if (2 != t.intValue() && bh - gl > 0d) {
              Map<String, Object> leftover;
              // 第一块空间
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", gw);
              leftover.put("w", gh);
              leftover.put("h", bh - gl);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第二块空间
            if (bw - gh > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bw - gh);
              leftover.put("w", gw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }
            // 第三块空间
            if (bl - gw > 0d) {
              Map<String, Object> leftover;
              leftover = new HashMap<String, Object>();
              leftover.put("boxid", box.get("boxid"));
              leftover.put("id", box.get("id"));

              leftover.put("l", bl - gw);
              leftover.put("w", bw);
              leftover.put("h", bh);
              leftover.put("code", code);
              moreSpance.add(leftover);
            }

            tryInSpance(box, moreSpance, moreSpance.isEmpty(), moreGoods, boxTypes);
            return moreSpance;
          }
        }
      }

    }
    // 任何东西都装不下了
	  if (!in) {
		  box.put("boxid", box.get("id").toString().concat("_").concat(java.util.UUID.randomUUID().toString()));
	  }
    return null;
  }

  public Map<String, Integer> getBoxes() {
    Map<String, Integer> boxNum = new HashMap<String, Integer>();
    for (Object k : boxes.keySet()) {
      String boxid = k.toString().split("_")[0];
      Integer count = 1;
		if (boxNum.get(boxid) != null) {
			count = boxNum.get(boxid) + 1;
		}
      boxNum.put(boxid, count);
    }
    return boxNum;
  }

}
