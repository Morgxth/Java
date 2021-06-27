package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin", shortName = "Project Base", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private NoteService service = NoteService.getInstance();
    private Grid<Note> grid = new Grid<>(Note.class);
    private TextField filterText = new TextField();
    private NoteForm form = new NoteForm(this);

    public MainView() {

        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);

        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());

        Button addCustomerBtn = new Button("Add new note");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Note());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addCustomerBtn);
//
//        grid.addColumn(Note::getName).setHeader("Name");
//        grid.addColumn(Note::getContent).setHeader("Content");
//        grid.addColumn(Note::getStatus).setHeader("Status");
//        grid.addColumn(Note::getNoteDate).setHeader("Note Creation Date");
//        grid.setColumns("name", "content", "status", "noteDate");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();
        updateList();

        form.setCustomer(null);

        grid.asSingleSelect().addValueChangeListener(event ->
                form.setCustomer(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        grid.setItems(service.findAll(filterText.getValue()));
    }

}
