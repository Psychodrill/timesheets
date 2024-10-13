package homework.zhiganov.timesheet.model;

import jakarta.persistence.*;
import lombok.*;


// public enum Role {

//     ADMIN("admin"), USER("user");
//     private final String name;

//     Role(String name){
//         this.name = name;
//     }
//     public String getName(){
//         return name;
//     }

// }

@Data
@Entity
@Table(name ="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include

    private Long id;
    @Column(name="role_name")
    private String name;


}
