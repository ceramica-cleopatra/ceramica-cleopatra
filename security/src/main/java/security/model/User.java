package security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="User")
@Table(name="OAUTH_USER",schema="HR")
public class User {
@Id	
private String id;
@Column(name="name")
private String name;
@Column(name="password")
private String password;
public String getId() {
	return id;
}

public User(){}
public User(String id,String name,String password){
	this.id=id;
	this.name=name;
	this.password=password;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


}
