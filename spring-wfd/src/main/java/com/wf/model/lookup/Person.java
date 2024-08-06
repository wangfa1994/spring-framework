package com.wf.model.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @Desc :
 * @Author : Mr.WangF
 * @Date: 2022/7/8 15:39
 */
@Component
public class Person {

    private String name;

	@Autowired // 即使Cat是原型模式， 在这种模式下获得到的依然是相同的单例形式
    private Cat cat;

	@Autowired
	private Cat catLookUp; // 使用Lookup注解，在调用lookUp标记的方法，spring会去容器中重新getBean获取容器中的对象，这样的话就可以获取到的是不同的对象(原型模式)

	@Lookup
	public   Cat  lookUpCat(){return  null;};


	public Cat getCatLookUp() {
		return catLookUp;
	}

	public void setCatLookUp(Cat catLookUp) {
		this.catLookUp = catLookUp;
	}


    public Cat getCat() {
        return cat;
    }


    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
