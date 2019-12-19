package domain;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String county;
	private String regDate;
	private String rank;
	
	public Member(String id, String pw, String name, String phone, String email, String country, String regDate, String rank) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.county = country;
		this.regDate = regDate;
		this.rank = "";
		
		if(rank != null && rank.equals("0")) this.rank = "general";
		else this.rank = "admin";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return county;
	}
	public void setCountry(String county) {
		this.county = county;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	

}
