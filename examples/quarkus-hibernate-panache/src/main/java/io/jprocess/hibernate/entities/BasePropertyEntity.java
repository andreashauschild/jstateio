package io.jprocess.hibernate.entities;

import io.jprocess.util.CommonUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasePropertyEntity extends BaseEntity {

    @Column(name = "PROP_KEY",
            nullable = false)
    private String key;

    @Column(name = "PROP_VALUE",
            nullable = false, length = Integer.MAX_VALUE,
            columnDefinition = "text"
    )
    private String value;

    @Column(name = "PROP_VALUE_INDEXED",
            nullable = false, length = 512)
    private String valueIndexed;

    @Column(name = "SHA256")
    private String sha256;

    public BasePropertyEntity() {

    }

    public BasePropertyEntity(String key, String value) {
        this.key = key;
        this.value = value;
        if (this.value.length() < 512) {
            this.valueIndexed = this.value;
        }
        this.sha256 = CommonUtil.toSHA256(value);
    }


    public String getKey() {
        return key;
    }

    public BasePropertyEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public BasePropertyEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public String getSha256() {
        return sha256;
    }

    public BasePropertyEntity setSha256(String sha256) {
        this.sha256 = sha256;
        return this;
    }
}
