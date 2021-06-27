package org.vaadin.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An in memory dummy "database" for the example purposes. In a typical Java app
 * this class would be replaced by e.g. EJB or a Spring based service class.
 * <p>
 * In demos/tutorials/examples, get a reference to this service class with
 * {@link NoteService#getInstance()}.
 */
public class NoteService {

    private static NoteService instance;
    private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());

    private final HashMap<Long, Note> contacts = new HashMap<>();
    private long nextId = 0;

    private NoteService() {
    }

    /**
     * @return a reference to an example facade for Note objects.
     */
    public static NoteService getInstance() {
        if (instance == null) {
            instance = new NoteService();
            instance.ensureTestData();
        }
        return instance;
    }

    /**
     * @return all available Note objects.
     */
    public synchronized List<Note> findAll() {
        return findAll(null);
    }

    /**
     * Finds all Note's that match given filter.
     *
     * @param stringFilter
     *            filter that returned objects should match or null/empty string
     *            if all objects should be returned.
     * @return list a Note objects
     */
    public synchronized List<Note> findAll(String stringFilter) {
        ArrayList<Note> arrayList = new ArrayList<>();
        for (Note contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Note>() {

            @Override
            public int compare(Note o1, Note o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    /**
     * Finds all Note's that match given filter and limits the resultset.
     *
     * @param stringFilter
     *            filter that returned objects should match or null/empty string
     *            if all objects should be returned.
     * @param start
     *            the index of first result
     * @param maxresults
     *            maximum result count
     * @return list a Note objects
     */
    public synchronized List<Note> findAll(String stringFilter, int start, int maxresults) {
        ArrayList<Note> arrayList = new ArrayList<>();
        for (Note contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Note>() {

            @Override
            public int compare(Note o1, Note o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        int end = start + maxresults;
        if (end > arrayList.size()) {
            end = arrayList.size();
        }
        return arrayList.subList(start, end);
    }

    /**
     * @return the amount of all customers in the system
     */
    public synchronized long count() {
        return contacts.size();
    }

    /**
     * Deletes a customer from a system
     *
     * @param value
     *            the Note to be deleted
     */
    public synchronized void delete(Note value) {
        contacts.remove(value.getId());
    }

    /**
     * Persists or updates customer in the system. Also assigns an identifier
     * for new Note instances.
     *
     * @param entry
     */
    public synchronized void save(Note entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Note is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Note) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        contacts.put(entry.getId(), entry);
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {
            final String[] names = new String[] { "Note1 sampletext", "note2 sampletext", "note3 sampletext", "note4 sampletext" };
            Random r = new Random(0);
            for (String name : names) {
                String[] split = name.split(" ");
                Note c = new Note();
                c.setName(split[0]);
                c.setContent(split[1]);
                c.setStatus(NoteStatus.values()[r.nextInt(NoteStatus.values().length)]);
                c.setNoteDate(LocalDateTime.now().minusDays(r.nextInt(365*10)));
                save(c);
            }
        }
    }
}
