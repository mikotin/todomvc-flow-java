package org.mikotin.flow.java.todomvc.ui.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Paragraph;

/**
 * Simple (static) footer, split to own class, so that main-view keeps a bit smaller
 */
public class TodoInfoFooter extends Footer {

    public TodoInfoFooter() {
        setClassName("info");

        add(new Paragraph("Double-click to edit a todo"));

        /*
         * Adding text and html-elements mixed is done by adding n-pieces of Text:s and then html
         * elements. Like these paragraphs are in html:
         * "<p>Created by <a href="http://todomvc.com">you</a></p>"
         * So it needs a Paragraph that has:
         * Text("Created by ") and after that Anchor (with href and actual text
         */
        Paragraph p = new Paragraph();
        p.add(new Text("Created by "));
        Anchor a = new Anchor();
        a.setHref("https://vaadin.com/company/team");
        a.setText("Mikko");
        p.add(a);
        add(p);

        p = new Paragraph();
        p.add(new Text("Part of "));
        a = new Anchor();
        a.setHref("http://todomvc.com");
        a.setText("TodoMVC");
        p.add(a);
        add(p);

    }

}
