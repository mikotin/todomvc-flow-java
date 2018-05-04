package org.mikotin.flow.java.todomvc.backend;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Todo {
    public enum State {
        OPEN("Active"),
        CLOSED("Completed");

        private String value;

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private static int idPool = 0;

    private State state;
    private String name;
    private int id;

    public Todo(@Nonnull State state, String name, int id) {
        this.state = state;
        this.name = name;
        this.id = id;
    }

    protected Todo(String name) {
        this.name = name;
        this.id = ++idPool;
        this.state = State.OPEN;
    }

    protected State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean completed() {
        return State.CLOSED.equals(state);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
