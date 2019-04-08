package fr.d2factory.libraryapp.Exception;

public class MemberException extends Exception{

    private String message;

    public MemberException(String message) {
        this.message = message;
    }
}
