package org.mikotin.flow.java.todomvc.ui.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import org.mikotin.flow.java.todomvc.backend.Todo;
import org.mikotin.flow.java.todomvc.ui.TodoPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoFilters extends Footer {

    private final Span todoCountAmount;
    private final Text countText;
    private final Span todoCount;

    private TodoPresenter presenter;

    public TodoFilters(TodoPresenter presenter) {
        this.presenter = presenter;

        // Mostly just simple html-template creation with java-api
        todoCountAmount = new Span();
        todoCountAmount.setClassName("strong");
        countText = new Text("");

        setClassName("footer");
        todoCount = new Span();
        todoCount.setClassName("todo-count");
        todoCount.add(todoCountAmount, countText);
        UnorderedList filters = new UnorderedList();
        filters.setClassName("filters");

        // Add filters
        List<Todo.State> stateList = new ArrayList<>();
        stateList.add(null);
        stateList.addAll(Arrays.asList(Todo.State.values()));
        for (Todo.State state : stateList) {
            ListItem item = new ListItem(state == null ? "All" : state.toString());
            item.addClickListener(click -> {
                // call the presenter to do something with the filter
                presenter.filter(state);
                // set selected-class to right item (filter)
                filters.getChildren().forEach(c -> ((ListItem) c).removeClassName("selected"));
                item.addClassName("selected");
            });
            filters.add(item);
        }

        // Clear completed "button" (div)
        Div clearButton = new Div();
        clearButton.setText("Clear completed");
        clearButton.setClassName("clear-completed");
        clearButton.addClickListener(event -> {
            // simply call the presenter to do the clear-action
            presenter.clearCompleted();
        });
        add(todoCount, filters, clearButton);
        setVisible(false);

    }


    public void setCount(int count, boolean empty) {
        countText.setText(" item" + (count != 1 ? "s" : "") + " left");
        todoCountAmount.setText(count + "");
        setVisible(!empty);
    }

}
