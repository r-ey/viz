package model.component;

import model.util.SimpleList;

// constructing a profile class for name, term, and list of courses
public class Profile {

    private final SimpleList<Term> termList;
    private final String name;

    // REQUIRES : t and n aren't null
    // EFFECTS : make profile with list of terms
    public Profile(SimpleList<Term> t, String n) {
        termList = t;
        name = n;
    }

    public String getName() {
        return name;
    }

    public SimpleList<Term> getTermList() {
        return termList;
    }

    // Overriding method for printing Course
    // EFFECTS : make string of a Profile object
    @Override
    public String toString() {
        String term = "";
        for (int i = 0; i < termList.size(); i++) {
            term = term.concat(termList.get(i).toString());
        }
        return "Name : " + name + "\n--------------------------------\n" + term;
    }
}
