package io.jprocess.hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROCESS_TEMPLATES")
public class ProcessTemplateEntity extends BaseEntity {

    @Column(name = "TEMPLATE",
            nullable = false, length = Integer.MAX_VALUE,
            columnDefinition = "text"
    )
    private String template;


    @OneToMany(
            mappedBy = "processTemplate",
            cascade = CascadeType.ALL
    )
    List<ProcessInstanceEntity> processInstances = new ArrayList<>();

    public String getTemplate() {
        return template;
    }

    public ProcessTemplateEntity setTemplate(String template) {
        this.template = template;
        return this;
    }

    public List<ProcessInstanceEntity> getProcessInstances() {
        return processInstances;
    }

    public ProcessTemplateEntity setProcessInstances(List<ProcessInstanceEntity> processInstances) {
        this.processInstances = processInstances;
        return this;
    }
}
