package io.jprocess.hibernate.entities;

import org.hibernate.sql.Template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PROCESS_INSTANCES")
public class ProcessInstanceEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ProcessTemplateEntity processTemplate;

    @Column(name = "RESERVATION_ID")
    private String reservationId;

    @Column(name = "RESERVATION_TIME")
    private LocalDateTime reservationTime;

    @OrderColumn
    @OneToMany
    @JoinColumn(name = "PROCESS_INSTANCE_ID")
    private List<StateEntity> states;

    public ProcessTemplateEntity getProcessTemplate() {
        return processTemplate;
    }

    public ProcessInstanceEntity setProcessTemplate(ProcessTemplateEntity processTemplate) {
        this.processTemplate = processTemplate;
        return this;
    }

    public String getReservationId() {
        return reservationId;
    }

    public ProcessInstanceEntity setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return this;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public ProcessInstanceEntity setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
        return this;
    }

    public List<StateEntity> getStates() {
        return states;
    }

    public ProcessInstanceEntity setStates(List<StateEntity> states) {
        this.states = states;
        return this;
    }
}
