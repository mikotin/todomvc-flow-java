package org.mikotin.flow.java.todomvc.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.html.Input;

/**
 * Filtering kb-event so that this types of events are only created
 * when user presses escape - so we can leave enter-checking on client side
 */
@DomEvent(value = "keyup", filter = "event.keyCode == 27")
public class EscUpEvent extends ComponentEvent<Input> {
    public EscUpEvent(Input source, boolean fromClient) {
        super(source, fromClient);
    }
}