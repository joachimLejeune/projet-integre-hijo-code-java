package model.tableModelTool;

public class SearchThree {
    private String firstName;
    private String lastName;
    private Integer sumQuantity;

    public SearchThree(String firstName, String lastName, Integer sumQuantity){
        this.firstName = firstName;
        this.lastName = lastName;
        this.sumQuantity = sumQuantity;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Integer getSumQuantity() {
        return sumQuantity;
    }
}
