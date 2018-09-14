package interviewPrep;

public class LandlordTestPOJO {
	public String firstName;
	public String lastName;
	public boolean trusted;
	
	public LandlordTestPOJO(String fName, String lName, boolean trusted)
	{
		this.firstName = fName;
		this.lastName = lName;
		this.trusted = trusted;
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

	public boolean isTrusted() {
		return trusted;
	}

	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}

	

}
