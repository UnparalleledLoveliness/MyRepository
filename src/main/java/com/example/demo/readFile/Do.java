package com.example.demo.readFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Do {
  //clauseItemId: 3014,clauseLog: 当地普通话导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3014,clauseLog: 当地英语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3014,clauseLog: 当地普通话英语双语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3014,clauseLog: 当地粤语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3014,clauseLog: 当地普通话导游服务（接驳部分含导游服务）,roleString: 导游,featureString: 接驳部分含、当地
  //clauseItemId: 3014,clauseLog: 当地英语导游服务（接驳部分含导游服务）,roleString: 导游,featureString: 接驳部分含、当地
  //clauseItemId: 3014,clauseLog: 当地普通话英语双语导游服务（接驳部分含导游服务）,roleString: 导游,featureString: 接驳部分含、当地
  //clauseItemId: 3014,clauseLog: 当地粤语导游服务（接驳部分含导游服务）,roleString: 导游,featureString: 接驳部分含、当地
  //clauseItemId: 3016,clauseLog: 全程普通话导游服务,roleString: 导游,featureString: 全程
  //clauseItemId: 3016,clauseLog: 全程英语导游服务,roleString: 导游,featureString: 全程
  //clauseItemId: 3016,clauseLog: 全程普通话英语双语导游服务,roleString: 导游,featureString: 全程
  //clauseItemId: 3016,clauseLog: 全程粤语导游服务,roleString: 导游,featureString: 全程
  //clauseItemId: 3017,clauseLog: 当地普通话司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3017,clauseLog: 当地英语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3017,clauseLog: 当地普通话英语双语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3017,clauseLog: 当地粤语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3018,clauseLog: 仅安排中文司机负责行程活动中接待服务（不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解
  //clauseItemId: 3023,clauseLog: 中文领队兼导游服务,roleString: 领队兼导游,featureString: null
  //clauseItemId: 3024,clauseLog: 中文领队和当地普通话导游服务,roleString: 领队、导游,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地英语导游服务,roleString: 领队、导游,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地英语（提供翻译）导游服务,roleString: 领队、导游,featureString: 当地、提供翻译
  //clauseItemId: 3024,clauseLog: 中文领队和当地普通话英语双语导游服务,roleString: 领队、导游,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地当地语言（提供翻译）导游服务,roleString: 领队、导游,featureString: 当地、提供翻译
  //clauseItemId: 3024,clauseLog: 中文领队和当地粤语导游服务,roleString: 领队、导游,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地普通话司机兼导游服务,roleString: 司机兼导游、领队,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地英语司机兼导游服务,roleString: 司机兼导游、领队,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地英语（提供翻译）司机兼导游服务,roleString: 司机兼导游、领队,featureString: 当地、提供翻译
  //clauseItemId: 3024,clauseLog: 中文领队和当地普通话英语双语司机兼导游服务,roleString: 司机兼导游、领队,featureString: 当地
  //clauseItemId: 3024,clauseLog: 中文领队和当地当地语言（提供翻译）司机兼导游服务,roleString: 司机兼导游、领队,featureString: 当地、提供翻译
  //clauseItemId: 3024,clauseLog: 中文领队和当地粤语司机兼导游服务,roleString: 司机兼导游、领队,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地普通话导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地英语导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地英语（提供翻译）导游服务,roleString: 导游,featureString: 当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地普通话英语双语导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地语言（提供翻译）导游服务,roleString: 导游,featureString: 当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地粤语导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地普通话粤语双语导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地语言（无翻译）导游服务,roleString: 导游,featureString: 当地、无翻译
  //clauseItemId: 3025,clauseLog: 当地普通话司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地英语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地英语（提供翻译）司机兼导游服务,roleString: 司机兼导游,featureString: 当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地普通话英语双语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地语言（提供翻译）司机兼导游服务,roleString: 司机兼导游,featureString: 当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地粤语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地普通话粤语双语司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 3025,clauseLog: 当地语言（无翻译）司机兼导游服务,roleString: 司机兼导游,featureString: 当地、无翻译
  //clauseItemId: 3025,clauseLog: 当地普通话司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地
  //clauseItemId: 3025,clauseLog: 当地英语司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地
  //clauseItemId: 3025,clauseLog: 当地英语（提供翻译）司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地普通话英语双语司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地
  //clauseItemId: 3025,clauseLog: 当地语言（提供翻译）司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地粤语司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地
  //clauseItemId: 3025,clauseLog: 当地普通话粤语双语司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地
  //clauseItemId: 3025,clauseLog: 当地语言（无翻译）司机服务（仅负责行程活动中接待服务,不提供景区/场馆讲解）,roleString: 司机,featureString: 不提供景区/场馆讲解、当地、无翻译
  //clauseItemId: 3025,clauseLog: 当地普通话导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地英语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地英语（提供翻译）导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地普通话英语双语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地语言（提供翻译）导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地粤语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地普通话粤语双语导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地语言（无翻译）导游服务（接驳部分不含）,roleString: 导游,featureString: 接驳部分不含、当地、无翻译
  //clauseItemId: 3025,clauseLog: 当地普通话司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地英语司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地英语（提供翻译）司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地普通话英语双语司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地语言（提供翻译）司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地、提供翻译
  //clauseItemId: 3025,clauseLog: 当地粤语司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地普通话粤语双语司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地
  //clauseItemId: 3025,clauseLog: 当地语言（无翻译）司机兼导游服务（接驳部分不含）,roleString: 司机兼导游,featureString: 接驳部分不含、当地、无翻译
  //clauseItemId: 3030,clauseLog: 中文领队兼导游服务或者中文领队和当地导游服务,roleString: 领队兼导游、领队、导游,featureString: 当地
  //clauseItemId: 31635,clauseLog: 安排中文司机负责行程活动中接待服务（不提供景区/场馆讲解）。团队出行人数满人，额外赠送当地普通话导游服务（接驳部分不含）,roleString: 导游、司机,featureString: 接驳部分不含、不提供景区/场馆讲解、当地
  //clauseItemId: 31635,clauseLog: 安排中文司机负责行程活动中接待服务（不提供景区/场馆讲解）。团队出行人数满人，额外赠送当地英语导游服务（接驳部分不含）,roleString: 导游、司机,featureString: 接驳部分不含、不提供景区/场馆讲解、当地
  //clauseItemId: 31635,clauseLog: 安排中文司机负责行程活动中接待服务（不提供景区/场馆讲解）。团队出行人数满人，额外赠送当地普通话英语双语导游服务（接驳部分不含）,roleString: 导游、司机,featureString: 接驳部分不含、不提供景区/场馆讲解、当地
  //clauseItemId: 31635,clauseLog: 安排中文司机负责行程活动中接待服务（不提供景区/场馆讲解）。团队出行人数满人，额外赠送当地粤语导游服务（接驳部分不含）,roleString: 导游、司机,featureString: 接驳部分不含、不提供景区/场馆讲解、当地
  //clauseItemId: 32666,clauseLog: 当地普通话T队（T.PAL，Trip Pal的缩写）服务（接驳部分不含）,roleString: T队,featureString: 接驳部分不含、当地
  //clauseItemId: 32666,clauseLog: 当地英语T队（T.PAL，Trip Pal的缩写）服务（接驳部分不含）,roleString: T队,featureString: 接驳部分不含、当地
  //clauseItemId: 32666,clauseLog: 当地普通话英语双语T队（T.PAL，Trip Pal的缩写）服务（接驳部分不含）,roleString: T队,featureString: 接驳部分不含、当地
  //clauseItemId: 32666,clauseLog: 当地粤语T队（T.PAL，Trip Pal的缩写）服务（接驳部分不含）,roleString: T队,featureString: 接驳部分不含、当地
  //clauseItemId: 159,clauseLog: 全陪和当地中文导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 160,clauseLog: 全程陪同中文导游服务,roleString: 导游,featureString: 全程
  //clauseItemId: 161,clauseLog: 当地中文导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 161,clauseLog: 当地英文导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 161,clauseLog: 当地中英文双语导游服务,roleString: 导游,featureString: 当地
  //clauseItemId: 162,clauseLog: 中文领队兼导游服务,roleString: 领队兼导游,featureString: null
  //clauseItemId: 163,clauseLog: 领队和当地中文导游服务,roleString: 领队、导游,featureString: 当地
  //clauseItemId: 211,clauseLog: 领队和当地中文导游服务或中文领队兼导游服务,roleString: 领队兼导游、领队、导游,featureString: 当地
  //clauseItemId: 219,clauseLog: 中文领队兼导游服务或领队和当地中文导游服务,roleString: 领队兼导游、领队、导游,featureString: 当地
  //clauseItemId: 247,clauseLog: 当地司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 247,clauseLog: 当地中文司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 247,clauseLog: 当地英文司机兼导游服务,roleString: 司机兼导游,featureString: 当地
  //clauseItemId: 20009,clauseLog: 游学导师服务,roleString: 游学导师,featureString: null
  //clauseItemId: 32525,clauseLog: 自驾游领航员带队服务,roleString: 自驾游领航员,featureString: null

  public static void main(String[] args) throws IOException {
    //把上面数据写入excel文件
     ExcelData sheet1 = new ExcelData("D:\\Users\\jtang7\\Desktop\\龙缸直拨 谭在谋.xls");

  }
}
