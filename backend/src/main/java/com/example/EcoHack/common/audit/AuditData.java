package com.example.EcoHack.common.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditData {
    @Column(updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long updatedAt;

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    void auditPreUpdateAndPersist() {
        setCreatedAt(System.currentTimeMillis());
        setUpdatedAt(System.currentTimeMillis());
    }

    @PreUpdate
    void auditPreUpdate() {
        setUpdatedAt(System.currentTimeMillis());
    }
}

