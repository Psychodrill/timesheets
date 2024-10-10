package homework.zhiganov.timesheet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


public enum Role {

    ADMIN("admin"), USER("user");
    private final String name;

    Role(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

}
