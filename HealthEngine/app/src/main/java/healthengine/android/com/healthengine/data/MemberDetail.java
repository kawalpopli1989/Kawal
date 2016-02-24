package healthengine.android.com.healthengine.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberDetail implements DataInter, Serializable {

@SerializedName("id")
@Expose
private String id;
@SerializedName("url")
@Expose
private String url;
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
@SerializedName("phi")
@Expose
private String phi;
@SerializedName("optin-marketing")
@Expose
private Boolean optinMarketing;
@SerializedName("practices")
@Expose
private List<Integer> practices = new ArrayList<Integer>();

/**
* 
* @return
* The id
*/
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(String id) {
this.id = id;
}

/**
* 
* @return
* The url
*/
public String getUrl() {
return url;
}

/**
* 
* @param url
* The url
*/
public void setUrl(String url) {
this.url = url;
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

/**
* 
* @return
* The phi
*/
public String getPhi() {
return phi;
}

/**
* 
* @param phi
* The phi
*/
public void setPhi(String phi) {
this.phi = phi;
}

/**
* 
* @return
* The optinMarketing
*/
public Boolean getOptinMarketing() {
return optinMarketing;
}

/**
* 
* @param optinMarketing
* The optin-marketing
*/
public void setOptinMarketing(Boolean optinMarketing) {
this.optinMarketing = optinMarketing;
}

/**
* 
* @return
* The practices
*/
public List<Integer> getPractices() {
return practices;
}

/**
* 
* @param practices
* The practices
*/
public void setPractices(List<Integer> practices) {
this.practices = practices;
}

}