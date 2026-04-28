package com.wf.xmg.a17event.a08customerMyListener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 spring 事件
 */
public class MySpringEvent extends ApplicationEvent {

	private static final long serialVersionUID = 7099057708183571937L;

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param message 事件消息
	 */
	public MySpringEvent(String message) {
		super(message);
	}

	@Override
	public String getSource() {
		return (String) super.getSource();
	}

	public String getMessage() {
		return getSource();
	}
}
