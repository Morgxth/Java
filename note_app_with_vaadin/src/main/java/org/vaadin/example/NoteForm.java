package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDateTime;

public class NoteForm extends FormLayout {

    private TextField name = new TextField("Name");
    private TextField content = new TextField("Content");
    private ComboBox<NoteStatus> status = new ComboBox<>("Status");
    private DateTimePicker noteDate = new DateTimePicker("Note creation time");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<Note> binder = new Binder<>(Note.class);

    private MainView mainView;
    private NoteService service = NoteService.getInstance();



    public NoteForm(MainView mainView) {
        this.mainView = mainView;

        LocalDateTime now = LocalDateTime.now();
        noteDate.setValue(now);
        noteDate.setReadOnly(true);
        status.setItems(NoteStatus.values());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, content, status, noteDate, buttons);

        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setCustomer(Note note) {
        binder.setBean(note);

        if (note == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

    private void save() {
        Note note = binder.getBean();
        note.setNoteDate(LocalDateTime.now());
        service.save(note);
        mainView.updateList();
        setCustomer(null);
    }

    private void delete() {
        Note note = binder.getBean();
        service.delete(note);
        mainView.updateList();
        setCustomer(null);
    }
}
