package org.mikotin.flow.java.todomvc.ui.components;

import com.vaadin.flow.component.html.*;
import org.mikotin.flow.java.todomvc.ui.TodoPresenter;

import java.util.List;

public class TodoListSection extends Section {
    private final Div toggleAll;
    private final UnorderedList todoList;

    private TodoPresenter presenter;

    public TodoListSection(TodoPresenter presenter) {
        this.presenter = presenter;

        setClassName("main");
        toggleAll = new Div();
        toggleAll.setId("toggle-all");
        toggleAll.setClassName("toggle-all");
        toggleAll.addClassName("checkbox");
        toggleAll.addClickListener(event -> {
            presenter.toggleAll();
        });
        Label toggleLabel = new Label("Mark all as complete");
        toggleLabel.setFor("toggle-all");

        todoList = new UnorderedList();
        todoList.setClassName("todo-list");
        add(toggleAll, toggleLabel, todoList);
    }

    public void setAllComplete(boolean allComplete) {
        if (allComplete) {
            toggleAll.addClassName("checked");
        } else {
            toggleAll.removeClassName("checked");
        }
    }

    public void setTodos(List<TodoElement> items) {
        todoList.removeAll();
        todoList.add(items.toArray(new ListItem[items.size()]));
    }

}
