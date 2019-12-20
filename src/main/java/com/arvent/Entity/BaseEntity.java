package com.arvent.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

//    @CreatedBy
//    protected U createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_date")
    protected Date createdDate;

//    @LastModifiedBy
//    protected UlastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("last_modified_date")
    protected Date lastModifiedDate;

}