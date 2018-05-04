package org.mikotin.flow.java.todomvc.backend;

import com.vaadin.flow.component.UI;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mockup of a database-service
 */
public class TodoService {
    private HashSet<Todo> database;
    private int idPool;

    private TodoService() {
        database = new HashSet<>();
        idPool = 0;
    }

    /**
     * Returns service-instance from session or creates a new one if no such is present
     * That way we get the todos-data saved to user session, so that data stays as longs as sessions
     * + each user user has it's own data
     *
     * @return
     */
    public static TodoService getService() {
        TodoService service = (TodoService) UI.getCurrent().getSession().getAttribute("todoService");
        if (service == null) {
            service = new TodoService();
            UI.getCurrent().getSession().setAttribute("todoService", service);
        }
        return service;
    }

    /**
     * Returns the todos from "database", filter with state-filter. If filter is null, then all todos are returned
     *
     * @param stateFilter which type of todos wanted, or null if all
     * @return
     */
    public List<Todo> getTodos(@Nullable Todo.State stateFilter) {
        return database.stream().filter(todo -> stateFilter == null || stateFilter.equals(todo.getState()))
                .sorted(Comparator.comparing(Todo::getId)).collect(Collectors.toList());
    }

    public Todo addTodo(String name) {
        Todo todo = new Todo(Todo.State.OPEN, name, ++idPool);
        database.add(todo);
        return todo;
    }

    public boolean removeTodo(Todo todo) {
        return database.remove(todo);
    }

    public void removeAll(@Nullable Todo.State stateFilter) {
        getTodos(stateFilter).forEach(todo -> database.remove(todo));
    }

    public boolean isEmpty() {
        return database.isEmpty();
    }

}
