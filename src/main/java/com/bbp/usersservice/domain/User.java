package com.bbp.usersservice.domain;


import com.bbp.usersservice.domain.enums.GenderEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(of = {"email", "firstName", "lastName"}, callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "\"user\"")
public class User extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(allocationSize = 1, sequenceName = "seq_user", name = "seq_user")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;

    private LocalDate birthDate;

    private GenderEnum gender;

    private boolean active = true;

    @ManyToOne
    private Role role;

    public static User create(String firstName, String lastName, String email, String passwordHash, LocalDate birthDate,
                              GenderEnum gender, Role role) {
        return new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPasswordHash(passwordHash)
                .setBirthDate(birthDate)
                .setGender(gender)
                .setRole(role);
    }
}
