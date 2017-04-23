package model;

public class DeveloperImpl {

	//Variables
	private Integer id;
    private String firstName;
    private String lastName;
    private Integer velocity;
    
    //Methods
    public String getEntityDescription(){
    	return id.toString() + " - " + firstName + " " + lastName;
    }
    
    //Getters & Setters    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getVelocity() {
		return velocity;
	}
	public void setVelocity(Integer velocity) {
		this.velocity = velocity;
	}
    
}
