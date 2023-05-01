package employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerGateway {

    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8082/customer";
    public ResponseEntity<?> searchCar(String brand) {
        String url = serverUrl + "/getCarsWithBrand/" + brand;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if(response.getBody().length() > 0) {
                return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
            }
            CustomErrorType error = new CustomErrorType("No available cars with this brand");
            return new ResponseEntity<CustomErrorType>(error, HttpStatus.NOT_FOUND);
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            return new ResponseEntity<CustomErrorType>(error, e.getStatusCode());
        }
    }
    public ResponseEntity<?> reserveOrRentCar(String licensePlate, String customerNumber, String operation) {
        String url = serverUrl + "/reserveCarOrRentCar?licensePlate=" + licensePlate + "&customerNumber=" + customerNumber + "&operation=" + operation;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
            } else {
                CustomErrorType error = new CustomErrorType(response.getBody());
                return new ResponseEntity<CustomErrorType>(error, response.getStatusCode());
            }
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            return new ResponseEntity<CustomErrorType>(error, e.getStatusCode());
        }
    }
    public void returnCar(String licensePlate, String customerNumber, String price) {
        String url = serverUrl + "/returnCar?licensePlate=" + licensePlate + "&customerNumber=" + customerNumber + "&price=" + price;
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println(response.getBody());
            } else {
                CustomErrorType error = new CustomErrorType(response.getBody());
                System.out.println("Error: " + error.getErrorMessage());
            }
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            System.out.println("Error: " + error.getErrorMessage());
        }
    }


}
