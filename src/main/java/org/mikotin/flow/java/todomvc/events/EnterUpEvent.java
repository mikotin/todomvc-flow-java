package org.mikotin.flow.java.todomvc.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.html.Input;

/**
 * Filtering kb-event so that this types of events are only created
 * when user presses enter - so we can leave enter-checking on client side
 */
@DomEvent(value = "keyup", filter = "event.keyCode == 13")
public class EnterUpEvent extends ComponentEvent<Input> {
    public EnterUpEvent(Input source, boolean fromClient) {
        super(source, fromClient);
    }
}
