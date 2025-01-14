package com.wf.xmg.a17event;

import java.util.Observable;
import java.util.Observer;

// 01 jdk  观察者模式
@SuppressWarnings({"deprecation","warn"})
public class ObserveDemo {

	public static void main(String[] args) {
		// 被观察者(目标对象)持有观察者对象列表
		MyTargetObservable myTargetObservable = new MyTargetObservable();
		// 观察者，观察我们的目标对象，在目标对象做出改变的时候，进行逻辑处理
		MyObserver myObserver = new MyObserver();
		// 目标对象添加我们的观察者
		myTargetObservable.addObserver(myObserver);
		// 目标对象改变了，并且要通知贯彻着
		myTargetObservable.setChanged();
		myTargetObservable.notifyObservers("hello ");

	}


	// 目标对象 被观察者，它知道她的观察者是谁
	static  class MyTargetObservable extends Observable{

		@Override
		protected synchronized void setChanged() {
			super.setChanged();
		}
	}

	// 观察者对象，她观察目标对象，并进行业务处理
	static class MyObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("监听的对象发生了变化,"+arg);
		}
	}
	//jdk9 之后，不建议使用了，
	/** 看 Observer Observable 注释
	 * 该类和Observer接口已弃用。观察者和可观察对象支持的事件模型是非常有限的，
	 * 可观察对象传递通知的顺序是未指定的，状态变化也不是与通知一对一对应的。
	 * 对于更丰富的事件模型，请考虑使用java。bean包。对于线程间可靠和有序的消息传递，
	 * 请考虑使用java.util.concurrent包中的一种并发数据结构。有关响应式流风格的编程
	 */
	// EventObject  和   EventListener 的事件接口  事件处理机制的两个重要接口
	/**
	 * EventListener: 标记接口，标识实现该接口的类为事件监听器，具体类型的事件监听器需要继承或者实现
	 * 该接口，并定义具体的事件处理方法
	 *
	 *
	 * EventObject:是所有事件对象的基类，封装了事件的信息，提供对事件源(触发事件的对象Source)的访问，所有的具体事件类型
	 * 都应该继承自EventObject。
	 *
	 *
	 * 在观察者模式中，EventListener 和 EventObject 分别对应以下角色：
	 * 观察者（Observer）：由实现了 EventListener 接口的具体监听器类扮演。这些监听器注册到事件源上，并在事件发生时被通知。
	 * 目标（Observable）：由继承自 EventObject 的具体事件类扮演。这些目标对象包含了事件的相关信息，并作为参数传递给监听器的方法
	 *
	 * spring中
	 * ApplicationListener 继承了 EventListener
	 * ApplicationEvent 继承了 EventObject   ，添加了 timestamp属性，标记事件的发生时间
	 * 组成了对应的事件机制
	 *
	 * ApplicationContextEvent 继承了  ApplicationEvent ,添加了getApplicationContext获取applicationContext的方法
	 *
	 *
	 *
	 */

}
