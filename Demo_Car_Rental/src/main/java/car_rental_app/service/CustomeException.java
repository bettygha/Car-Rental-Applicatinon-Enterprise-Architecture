package car_rental_app.service;

public class CustomeException extends RuntimeException{

    private String errorMessage;
    public CustomeException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }


}
