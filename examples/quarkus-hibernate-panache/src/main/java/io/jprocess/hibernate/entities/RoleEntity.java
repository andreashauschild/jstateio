package io.jprocess.hibernate.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.litexo.smartgarden.entities.BaseEntity;
import de.litexo.smartgarden.entities.UserEntity;
import io.quarkus.security.jpa.RolesValue;

@Table(name = "APP_ROLES")
@Entity
public class RoleEntity extends BaseEntity {

    @ManyToMany(mappedBy = "roles")
    private List<de.litexo.smartgarden.entities.UserEntity> users;

    @Column(name = "ROLE_NAME")
    @RolesValue
    private String role;

    /**
     * Gets the value of the users property.
     *
     * @return possible object is {@link List<  de.litexo.smartgarden.entities.UserEntity >}
     */
    public List<de.litexo.smartgarden.entities.UserEntity> getUsers() {

        return users;
    }

    /**
     * Sets the value of the users property
     *
     * @param users
     *            allowed object is {@link List<  de.litexo.smartgarden.entities.UserEntity > }
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
     * @param role
     *            allowed object is {@link String }
     * @return the {@link RoleEntity}
     */
    public RoleEntity setRole(String role) {

        this.role = role;
        return this;
    }
}
