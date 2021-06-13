package io.jprocess.hibernate.entities;

import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.inject.spi.CDI;
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
                ((BaseEntity) o).setCreatedBy("todo");
            }

            ((BaseEntity) o).setLastModifiedBy("todo");
            ((BaseEntity) o).setLastModified(LocalDateTime.now());

        }
    }

    @PreUpdate
    private void handleUpdate(final Object o) {

        try {

            final SecurityIdentity securityIdentity = CDI.current().select(SecurityIdentity.class).get();
            if (o instanceof BaseEntity) {
                ((BaseEntity) o).setLastModifiedBy(securityIdentity.getPrincipal().getName());
                ((BaseEntity) o).setLastModified(LocalDateTime.now());
            }
            // could happen if there is no CDI-Context e.g. external thread call
        } catch (final ContextNotActiveException ex) {
            if (o instanceof BaseEntity) {
                ((BaseEntity) o).setLastModified(LocalDateTime.now());
            }
        }
    }
}
