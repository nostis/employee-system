package app.service;

import app.dao.RoleDAOCrud;
import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDAOCrud roleDAOCrud;

    public List<Role> getAllRoles(){
        List<Role> list = new ArrayList<>();
        roleDAOCrud.findAll().forEach(list::add);
        return list;
    }
}
