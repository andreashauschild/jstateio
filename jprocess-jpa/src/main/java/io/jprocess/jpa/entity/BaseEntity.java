package io.jprocess.jpa.entity;


import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

// @formatter:off
@MappedSuperclass
@EntityListeners({DatabaseMetaDataListener.class})
// @formatter:on
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "CREATED",
            nullable = false)
    private LocalDateTime created;

    @Column(name = "LAST_MODIFIED",
            nullable = false)
    private LocalDateTime lastModified;

    @Column(name = "CREATED_BY",
            nullable = false)
    private String createdBy;

    @Column(name = "MODIFIED_BY",
            nullable = false)
    private String lastModifiedBy;

    public UUID getId() {
        return id;
    }

    public BaseEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Gets the value of the created property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getCreated() {

        return created;
    }

    /**
     * Sets the value of the created property
     *
     * @param created allowed object is {@link LocalDateTime }
     * @return the {@link BaseEntity}
     */
    public BaseEntity setCreated(LocalDateTime created) {

        this.created = created;
        return this;
    }

    /**
     * Gets the value of the lastModified property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getLastModified() {

        return lastModified;
    }

    /**
     * Sets the value of the lastModified property
     *
     * @param lastModified allowed object is {@link LocalDateTime }
     * @return the {@link BaseEntity}
     */
    public BaseEntity setLastModified(LocalDateTime lastModified) {

        this.lastModified = lastModified;
        return this;
    }

    /**
     * Gets the value of the createdBy property.
     *
     * @return possible object is {@link String}
     */
    public String getCreatedBy() {

        return createdBy;
    }

    /**
     * Sets the value of the createdBy property
     *
     * @param createdBy allowed object is {@link String }
     * @return the {@link BaseEntity}
     */
    public BaseEntity setCreatedBy(String createdBy) {

        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     *
     * @return possible object is {@link String}
     */
    public String getLastModifiedBy() {

        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property
     *
     * @param lastModifiedBy allowed object is {@link String }
     * @return the {@link BaseEntity}
     */
    public BaseEntity setLastModifiedBy(String lastModifiedBy) {

        this.lastModifiedBy = lastModifiedBy;
        return this;
    }
}
