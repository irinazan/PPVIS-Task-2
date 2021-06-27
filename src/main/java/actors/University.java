package actors;

import database.UniversityDatabase;
import database.Database;

public class University {
    private final Database database = new UniversityDatabase();
    private final SecretaryI secretaryI = new Secretary(database);
    private final ProfessorI professorI = new Professor(database);
    private final DeanI deanI = new Dean(database);

    public SecretaryI getSecretary() {
        return secretaryI;
    }

    public ProfessorI getTeacher() {
        return professorI;
    }

    public DeanI getDean() {
        return deanI;
    }

    public Database getDatabase() {
        return database;
    }
}
