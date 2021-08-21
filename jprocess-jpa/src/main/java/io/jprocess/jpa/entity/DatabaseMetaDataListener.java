package io.jprocess.jpa.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class DatabaseMetaDataListener {
    @PrePersist
    private void setMetaData(final Object o) {


        if (o instanceof BaseEntity) {

            if (((BaseEntity) o).getCreated() == null) {
                ((BaseEntity) o).setCreated(LocalDateTime.now());
            }
            if (((BaseEntity) o).getCreatedBy() == null) {
                ((BaseEntity) o).setCreatedBy(getUser());
            }

            ((BaseEntity) o).setLastModifiedBy(getUser());
            ((BaseEntity) o).setLastModified(LocalDateTime.now());

        }
    }

    @PreUpdate
    private void handleUpdate(final Object o) {

        if (o instanceof BaseEntity) {
            ((BaseEntity) o).setLastModifiedBy(getUser());
            ((BaseEntity) o).setLastModified(LocalDateTime.now());
        }

    }

    protected String getUser() {
        return "";
    }


}
