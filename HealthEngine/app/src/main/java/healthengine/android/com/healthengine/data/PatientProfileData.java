package healthengine.android.com.healthengine.data;

import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientProfileData implements DataInter{

@SerializedName("auth")
@Expose
private String auth;
@SerializedName("firstname")
@Expose
private String firstname;
@SerializedName("lastname")
@Expose
private String lastname;
@SerializedName("dob")
@Expose
private String dob;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("address")
@Expose
private String address;
@SerializedName("suburb")
@Expose
private String suburb;
@SerializedName("state")
@Expose
private String state;
@SerializedName("postcode")
@Expose
private String postcode;

/**
* 
* @return
* The auth
*/
public String getAuth() {
return auth;
}

/**
* 
* @param auth
* The auth
*/
public void setAuth(String auth) {
this.auth = auth;
}

/**
* 
* @return
* The firstname
*/
public String getFirstname() {
return firstname;
}

/**
* 
* @param firstname
* The firstname
*/
public void setFirstname(String firstname) {
this.firstname = firstname;
}

/**
* 
* @return
* The lastname
*/
public String getLastname() {
return lastname;
}

/**
* 
* @param lastname
* The lastname
*/
public void setLastname(String lastname) {
this.lastname = lastname;
}

/**
* 
* @return
* The dob
*/
public String getDob() {
return dob;
}

/**
* 
* @param dob
* The dob
*/
public void setDob(String dob) {
this.dob = dob;
}

/**
* 
* @return
* The email
*/
public String getEmail() {
return email;
}

/**
* 
* @param email
* The email
*/
public void setEmail(String email) {
this.email = email;
}

/**
* 
* @return
* The phone
*/
public String getPhone() {
return phone;
}

/**
* 
* @param phone
* The phone
*/
public void setPhone(String phone) {
this.phone = phone;
}

/**
* 
* @return
* The address
*/
public String getAddress() {
return address;
}

/**
* 
* @param address
* The address
*/
public void setAddress(String address) {
this.address = address;
}

/**
* 
* @return
* The suburb
*/
public String getSuburb() {
return suburb;
}

/**
* 
* @param suburb
* The suburb
*/
public void setSuburb(String suburb) {
this.suburb = suburb;
}

/**
* 
* @return
* The state
*/
public String getState() {
return state;
}

/**
* 
* @param state
* The state
*/
public void setState(String state) {
this.state = state;
}

/**
* 
* @return
* The postcode
*/
public String getPostcode() {
return postcode;
}

/**
* 
* @param postcode
* The postcode
*/
public void setPostcode(String postcode) {
this.postcode = postcode;
}

}