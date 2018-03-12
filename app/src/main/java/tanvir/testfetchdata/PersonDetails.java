package tanvir.testfetchdata;

/**
 * Created by USER on 30-Dec-17.
 */

public class PersonDetails {

    String name , mobile_num , email;

    public PersonDetails(String name, String mobile_num, String email) {
        this.name = name;
        this.mobile_num = mobile_num;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
