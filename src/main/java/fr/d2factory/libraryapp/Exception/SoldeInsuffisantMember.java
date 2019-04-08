package fr.d2factory.libraryapp.Exception;

public class SoldeInsuffisantMember extends Exception {

    private String message;


    public SoldeInsuffisantMember(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
