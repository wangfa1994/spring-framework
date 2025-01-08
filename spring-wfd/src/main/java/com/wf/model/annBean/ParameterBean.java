package com.wf.model.annBean;

public class ParameterBean {

	private Cat cat;

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "ParameterBean{" +
				"cat=" + cat +
				'}';
	}
}
