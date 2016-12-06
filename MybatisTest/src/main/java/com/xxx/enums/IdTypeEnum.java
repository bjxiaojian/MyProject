package com.xxx.enums;

import com.xxx.util.EnumUtil;

/**
 * @Author bjxiaojian
 * @Date 2016/12/6
 * @Description
 */
public enum IdTypeEnum implements EnumUtil.ValuedEnum{
  IDCARD(1, "idcard"),
  PASSPORT(2, "passport"),
  UNKNOWN(3, "unknow");

  private int value;
  private String name;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  IdTypeEnum(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public static IdTypeEnum fromValue(int value) {
    try{
      return EnumUtil.getEnum(IdTypeEnum.class, value);
    }catch(IllegalArgumentException ex){
      return UNKNOWN;
    }
  }

  public static IdTypeEnum fromExtValue(String extValue) {
    return EnumUtil.getEnum(IdTypeEnum.class, extValue);
  }

  public int value() {
    return value;
  }

  public String extValue() {
    return name;
  }
}
