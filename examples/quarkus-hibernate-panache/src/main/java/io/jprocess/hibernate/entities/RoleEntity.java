package io.jprocess.hibernate.entities;

import io.quarkus.security.jpa.RolesValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "APP_ROLES")
@Entity
public class RoleEntity extends BaseEntity {

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    @Column(name = "ROLE_NAME")
    @RolesValue
    private String role;

    /**
     * Gets the value of the users property.
     *
     * @return possible object is {@link List<  UserEntity >}
     */
    public List<UserEntity> getUsers() {

        return users;
    }

    /**
     * Sets the value of the users property
     *
     * @param users allowed object is {@link List<  UserEntity > }
     * @return the {@link RoleEntity}
     */
    public RoleEntity setUsers(List<UserEntity> users) {

        this.users = users;
        return this;
    }

    /**
     * Gets the value of the role property.
     *
     * @return possible object is {@link String}
     */
    public String getRole() {

        return role;
    }

    /**
     * Sets the value of the role property
     *
     * @param role allowed object is {@link String }
     * @return the {@link RoleEntity}
     */
    public RoleEntity setRole(String role) {

        this.role = role;
        return this;
    }
}
