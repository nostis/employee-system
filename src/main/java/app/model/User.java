package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    private String login;
    private String password;
    private int empId;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",  joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String login, String password, int empId, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.empId = empId;
        this.roles = roles;
    }

    public User(){}

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Id: ").append(id).append(" Login: ").append(login).append(" Hashed password: ").append(password).append(" roles: ");

        for(Role role : roles){
            toReturn.append(role.getRole());
            toReturn.append(" ");
        }

        return toReturn.toString();
    }
}
