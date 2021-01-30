package fr.sorbonne.paris.nord.university.api.exception;

public class TeamInvalidException extends Throwable {
    private String slogan;

    public void sloganException() throws IllegalAccessException {
        if (slogan.equals("") || slogan.isEmpty()) {
            throw new IllegalAccessException("this field must not be empty or null ");
        }
    }
}
