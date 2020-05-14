package eg.edu.alexu.csd.datastructure.mailServer;

public class Contact implements IContact {
    private String name ;
    private String Email ;
    private String password ;
    public Contact(String name, String Email, String password) {
        this.name = name;  
        this.password = password;
        this.Email = Email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }

}
