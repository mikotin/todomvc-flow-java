/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mikotin.flow.java.todomvc.ui;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import org.mikotin.flow.java.todomvc.ui.components.*;

import java.util.List;

/**
 * A Java -created representation (quite same :D) of TodoMVC base-html file
 * @see <a href="https://github.com/tastejs/todomvc-app-template/blob/master/index.html">https://github.com/tastejs/todomvc-app-template/blob/master/index.html</a>
 *
 * Mostly just simple element creation, but also has listeners for user interactions, which are then passed to
 * the {@link TodoPresenter} that has all the logic within (except few style-swaps which I left in ui-code, for I
 * was lazy)
 *
 * The css used is also copied, with only minor changes (some elements were a bit straigthforward to create as divs
 * instead of real element)
 *
 */
@Route(value = "")
@PageTitle("Todo MVC")
@StyleSheet("frontend://styles/todo.css")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class TodoView extends Div
        implements PageConfigurator {

    private final TodoFilters actionBar;
    private final TodoListSection mainSection;
    private TodoPresenter presenter;

    public TodoView() {
        setClassName("wrapper");
        Section appSection = new Section();
        appSection.addClassName("todoapp");
        presenter = new TodoPresenter(this);
        actionBar = new TodoFilters(presenter);
        mainSection = new TodoListSection(presenter);
        appSection.add(new TodoHeader(presenter), mainSection, actionBar);
        add(appSection, new TodoInfoFooter());
        presenter.refresh();
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }

    /**
     * (re)populates todos
     *
     * @param items
     */
    public void setTodos(List<TodoElement> items) {
        mainSection.setTodos(items);
    }

    /**
     * sets count of todos
     *
     * @param count
     * @param empty
     */
    public void setCount(int count, boolean empty) {
        actionBar.setCount(count, empty);
    }

    /**
     * Sets view to show that everything is done (what it really does is
     * just marks toggle-all as checked)
     *
     * @param allComplete
     */
    public void setAllComplete(boolean allComplete) {
        mainSection.setAllComplete(allComplete);
    }

}
