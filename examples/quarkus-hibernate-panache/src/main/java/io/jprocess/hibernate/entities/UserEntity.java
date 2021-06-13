package io.jprocess.hibernate.entities;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_USERS")
@UserDefinition
public class UserEntity extends BaseEntity {

    @Column(name = "USER_NAME", unique = true)
    @Username
    private String name;

    @Column(name = "PASSWORD")
    @Password
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Roles
    private List<RoleEntity> roles = new ArrayList<>();

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String}
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the value of the name property
     *
     * @param name allowed object is {@link String }
     * @return the {@link UserEntity}
     */
    public UserEntity setName(String name) {

        this.name = name;
        return this;
    }

    /**
     * Gets the value of the pass property.
     *
     * @return possible object is {@link String}
     */
    public String getPassword() {

        return password;
    }

    /**
     * Sets the value of the pass property
     *
     * @param password allowed object is {@link String }
     * @return the {@link UserEntity}
     */
    public UserEntity setPassword(String password) {

        this.password = password;
        return this;
    }

    /**
     * Gets the value of the roles property.
     *
     * @return possible object is {@link List< RoleEntity>}
     */
    public List<RoleEntity> getRoles() {

        return roles;
    }

    /**
     * Sets the value of the roles property
     *
     * @param roles allowed object is {@link List< RoleEntity> }
     * @return the {@link UserEntity}
     */
    public UserEntity setRoles(List<RoleEntity> roles) {

        this.roles = roles;
        return this;
    }
}
