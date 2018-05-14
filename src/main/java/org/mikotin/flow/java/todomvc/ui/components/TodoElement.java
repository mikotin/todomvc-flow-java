package org.mikotin.flow.java.todomvc.ui.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyUpEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import org.mikotin.flow.java.todomvc.backend.Todo;
import org.mikotin.flow.java.todomvc.events.EnterUpEvent;
import org.mikotin.flow.java.todomvc.events.EscUpEvent;
import org.mikotin.flow.java.todomvc.ui.TodoPresenter;

public class TodoElement extends ListItem {
    private Todo todo;
    private TodoPresenter presenter;
    private Input editor;
    private Div view;
    private Label name;
    private boolean editing = false;

    public TodoElement(Todo todo, TodoPresenter presenter) {
        setClassName("todo");
        this.todo = todo;
        this.presenter = presenter;
        view = getView();

        editor = new Input();
        editor.setType("text");
        editor.setClassName("edit");
        // save on enter or blur
        editor.getElement().addEventListener("blur", domEvent -> editDone(editor.getValue()));
        editor.addListener(EnterUpEvent.class, enterUpEvent -> editDone(editor.getValue().trim()));

        // cancel on esc
        editor.addListener(EscUpEvent.class, escUpEvent -> {
                    editing = false;
                    editDone(null);
                });

        add(view, editor);
        setStyle();
    }

    private void editDone(String value) {
        if (editing) {
            this.todo.setName(value.trim());
            this.name.setText(todo.getName());
        }
        editing = false;
        removeClassName("editing");
    }

    private Div getView() {
        view = new Div();
        view.setClassName("view");
        Div toggle = new Div();
        toggle.setClassName("toggle");
        toggle.addClickListener(event -> {
            todo.setState(todo.completed() ? Todo.State.OPEN : Todo.State.CLOSED);
            presenter.persist(todo);
            this.setStyle();
        });
        name = new Label(todo.getName());
        name.getElement().addEventListener("dblclick", domEvent -> {
            editing = true;
            editor.setValue(todo.getName());
            addClassName("editing");
            editor.focus();
        });

        Div destroy = new Div();
        destroy.setClassName("destroy");
        destroy.addClassName("button");
        destroy.addClickListener(event -> {
            presenter.deleteTodo(todo);
        });
        view.add(toggle, name, destroy);
        return view;
    }

    private void setStyle() {
        if (todo.completed()) {
            addClassName("completed");
        } else {
            removeClassName("completed");
        }
    }


}
