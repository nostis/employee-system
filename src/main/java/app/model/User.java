package app.model;

import app.util.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int empId;
}
