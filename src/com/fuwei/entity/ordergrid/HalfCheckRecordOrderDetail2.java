package com.fuwei.entity.ordergrid;

import java.io.Serializable;

public class HalfCheckRecordOrderDetail2 implements Serializable {
	private Integer material;//材料
	private String color;//色号
	private String colorsample;//标准色样
	public Integer getMaterial() {
		return material;
	}
	public void setMaterial(Integer material) {
		this.material = material;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColorsample() {
		return colorsample;
	}
	public void setColorsample(String colorsample) {
		this.colorsample = colorsample;
	}
	
}