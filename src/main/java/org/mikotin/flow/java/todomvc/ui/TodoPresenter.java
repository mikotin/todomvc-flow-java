package org.mikotin.flow.java.todomvc.ui;

import org.mikotin.flow.java.todomvc.backend.Todo;
import org.mikotin.flow.java.todomvc.backend.TodoService;
import org.mikotin.flow.java.todomvc.ui.components.TodoElement;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TodoMVC -presenter. The one that has all the (so-called) business logic.
 *
 */
public class TodoPresenter {
    private Todo.State state;
    private TodoService service;
    private TodoView view;

    public TodoPresenter(TodoView view) {
        state = null;
        service = TodoService.getService();
        this.view = view;
    }

    private void loadTodos() {
        // to be noted: this could be made much faster and light for browser
        // by having all the todos in dom all the time and make filtering by
        // only css, that way we wouldn't have to re-paint stuff
        // but this is now made by spec and as a test of creating
        // specified scenario as exact as possible with Java-only Flow
        setCount();
        List<TodoElement> elements = service.getTodos(state).stream().map(todo ->
                new TodoElement(todo, this)).collect(Collectors.toList());
        view.setTodos(elements);
    }

    private void setCount() {
        List<TodoElement> elements = service.getTodos(Todo.State.OPEN).stream().map(todo ->
                new TodoElement(todo, this)).collect(Collectors.toList());
        view.setCount(elements.size(), service.isEmpty());
        setAllComplete();
    }

    private void setAllComplete() {
        view.setAllComplete(allComplete());
    }

    private boolean allComplete() {
        return service.getTodos(null).stream().allMatch(todo -> todo.completed());
    }

    /**
     * Add new todo
     * @param name
     */
    public void addTodo(String name) {
        service.addTodo(name);
        loadTodos();
    }

    /**
     * Filter the todos
     * @param state
     */
    public void filter(@Nullable Todo.State state) {
        this.state = state;
        loadTodos();
    }

    /**
     * Remove (delete) all completed todos
     */
    public void clearCompleted() {
        service.removeAll(Todo.State.CLOSED);
        loadTodos();
    }

    /**
     * Save (updated) todo
     *
     * @param todo
     */
    public void persist(Todo todo) {
        if (state == null) {
            // everything is visible, let's just update count
            setCount();
        } else {
            // need to re-draw todos, for filters effect visibility
            loadTodos();
        }
    }

    /**
     * delete todo
     *
     * @param todo
     */
    public void deleteTodo(Todo todo) {
        service.removeTodo(todo);
        loadTodos();
    }

    /**
     * Toggles all todos state to completed (if there are any that aren't complete) or open (if everything is complete)
     */
    public void toggleAll() {
        boolean allComplete = allComplete();
        service.getTodos(null).forEach(todo -> {
            todo.setState(allComplete ? Todo.State.OPEN : Todo.State.CLOSED);
        });
        loadTodos();
    }

    public void refresh() {
        loadTodos();
    }

}
