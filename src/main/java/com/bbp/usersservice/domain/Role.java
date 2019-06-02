package com.bbp.usersservice.domain;

import com.bbp.usersservice.domain.enums.PermissionEnum;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(of = {"name"}, callSuper = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(chain = true)
@Entity
@Table(name = "\"role\"")
public class Role extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    @SequenceGenerator(allocationSize = 1, sequenceName = "seq_role", name = "seq_role")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean active = true;

    @ElementCollection
    @CollectionTable(name = "\"permission\"", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "value")
    private Set<PermissionEnum> permissions;

    public static Role create(String name, Set<PermissionEnum> permissions) {
        return new Role()
                .setName(name)
                .setPermissions(permissions);
    }
}
