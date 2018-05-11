package org.mikotin.flow.java.todomvc.app;

import com.vaadin.flow.server.*;

public class CustomVaadinServiceInitListener implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.addBootstrapListener(new CustomBootstrapListener());
	}
}
