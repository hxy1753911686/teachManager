package com.school.estimate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Estimate implements Serializable{
	private String id;
	private String name;
	private String dimension;	//评价维度
	private String element;		//评价要素
	private Double maxValue;	//满分
	private Double singleValue;	//单次评分,如果等于满分,则无单次评分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getSingleValue() {
		return singleValue;
	}
	public void setSingleValue(Double singleValue) {
		this.singleValue = singleValue;
	}
	
	
}
