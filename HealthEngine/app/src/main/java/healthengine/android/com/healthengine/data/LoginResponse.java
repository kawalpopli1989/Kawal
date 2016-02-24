package healthengine.android.com.healthengine.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements DataInter {

@SerializedName("auth-token")
@Expose
private String authToken;
@SerializedName("email")
@Expose
private String email;
@SerializedName("firstname")
@Expose
private String firstname;
@SerializedName("lastname")
@Expose
private String lastname;
@SerializedName("avatar_url")
@Expose
private Object avatarUrl;

/**
* 
* @return
* The authToken
*/
public String getAuthToken() {
return authToken;
}

/**
* 
* @param authToken
* The auth-token
*/
public void setAuthToken(String authToken) {
this.authToken = authToken;
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
* The avatarUrl
*/
public Object getAvatarUrl() {
return avatarUrl;
}

/**
* 
* @param avatarUrl
* The avatar_url
*/
public void setAvatarUrl(Object avatarUrl) {
this.avatarUrl = avatarUrl;
}

}