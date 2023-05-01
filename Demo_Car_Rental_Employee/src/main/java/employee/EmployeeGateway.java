package employee;


import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeGateway implements CommandLineRunner {

    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl = "http://localhost:8082/customer";


    public ResponseEntity<?> addNewCustomer(CustomerDTO customerDTO) {
        String url = serverUrl + "/addNew";
        try {
            ResponseEntity<CustomerDTO> response = restTemplate.postForEntity(url, customerDTO, CustomerDTO.class);
            return new ResponseEntity<CustomerDTO>(response.getBody(), HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            return new ResponseEntity<CustomErrorType>(error, e.getStatusCode());
        }
    }


    public CustomerDTO searchCustomer(String customerNumber){
        CustomerDTO customerDTO =  restTemplate.getForObject(serverUrl + "/searchCustomer/"+customerNumber, CustomerDTO.class);
        return  customerDTO;
    }
    public void updateCustomer(String customerNumber, CustomerDTO customerDTO){
         restTemplate.put(serverUrl + "/updateCustomer/"+ customerNumber, customerDTO);
         System.out.println("Customer has been updated ");
    }
    public void deleteCustomer(String customerNumber){
        restTemplate.delete(serverUrl + "/removeCustomer/"+customerNumber);
        System.out.println("Customer has been deleted ");

    }

    public void findCustomerByNumber(String customerNumber) {
        String url = serverUrl + "/findByCustomerNumber/" + customerNumber;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Customer found:");
                System.out.println(response.getBody());
            } else {
                CustomErrorType error =  new CustomErrorType(response.getBody());;
                System.out.println("Error: " + error.getErrorMessage());
            }
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            System.out.println("Error: " + error.getErrorMessage());
        }
    }
    public void findByCustomersByName(String name) {
        String url = serverUrl + "/findByCustomersByName/" + name;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
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

    public void findByCustomerEmail(String email) {
        String url = serverUrl + "/findByCustomerEmail/" + email;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
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
    public void findByLevel(String level) {
        String url = serverUrl + "/findByLevel/" + level;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
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
    public void getAllCustomerHistory(String customerNumber) {
        String url = serverUrl + "/getAllCustomerData/" + customerNumber;
        try {
            ResponseEntity<CustomerDTO> response = restTemplate.getForEntity(url, CustomerDTO.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Customer data found:");
                System.out.println(response.getBody().toString());
            } else {
                CustomErrorType error = new CustomErrorType(response.getBody().toString());
                System.out.println("Error: " + error.getErrorMessage());
            }
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            System.out.println("Error: " + error.getErrorMessage());
        }
    }
    public void getAllDataFromCar(String licencePlate) {
        String url = serverUrl + "/getAllDataFromCar/" + licencePlate;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Car data found:");
                System.out.println(response.getBody().toString());
            } else {
                CustomErrorType error = new CustomErrorType(response.getBody());
                System.out.println("Error: " + error.getErrorMessage());
            }
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            System.out.println("Error: " + error.getErrorMessage());
        }
    }
    public void getAllAvailableAndNotAvailableCars(String fleetId) {
        String url = serverUrl + "/getAllAvailableAndNotAvailableCars/1";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Available and not available cars:");
                System.out.println(response.getBody().toString());
            } else {
                CustomErrorType error =new CustomErrorType(response.getBody());
                System.out.println("Error: " + error.getErrorMessage());
            }
        } catch (HttpStatusCodeException e) {
            CustomErrorType error = new CustomErrorType(e.getMessage());
            System.out.println("Error: " + error.getErrorMessage());
        }
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
