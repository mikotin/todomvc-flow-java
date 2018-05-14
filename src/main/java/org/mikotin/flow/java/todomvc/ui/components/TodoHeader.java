package org.mikotin.flow.java.todomvc.ui.components;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Input;
import org.mikotin.flow.java.todomvc.events.EnterUpEvent;
import org.mikotin.flow.java.todomvc.ui.TodoPresenter;

public class TodoHeader extends Header {
    TodoPresenter presenter;

    public TodoHeader(TodoPresenter presenter) {
        this.presenter = presenter;

        setClassName("header");
        // The h1
        add(new H1("todos"));

        // input for adding new todos
        Input newTodo = new Input();
        newTodo.setClassName("new-todo");
        newTodo.setPlaceholder("What needs to be done?");
        newTodo.addListener(EnterUpEvent.class, enterUp -> {
            // trigger creation only on enter and only if trimmed value is non-empty
            if ((newTodo.getValue() != null && !newTodo.getValue().trim().isEmpty())) {
                String name = newTodo.getValue().trim();
                // clear current text
                newTodo.clear();
                // call presenter to create a new todo
                presenter.addTodo(name);
            }
        });
        add(newTodo);

    }
}
