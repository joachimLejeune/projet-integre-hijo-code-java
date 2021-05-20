package model.tableModelTool;

public class SearchTwo {
    private String firstName;
    private String lastName;
    private String wording;
    private Integer quantity;

    public SearchTwo(String firstName, String lastName, String wording, Integer quantity){
        this.firstName = firstName;
        this.lastName = lastName;
        this.wording = wording;
        this.quantity = quantity;
    }
    // getter
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getWording() {
        return wording;
    }
    public Integer getQuantity() {
        return quantity;
    }
}
