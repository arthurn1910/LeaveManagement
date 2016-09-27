package com.example.leave.entity.leave;

import javax.persistence.*;

/**
 * Created by Medion on 2016-09-26.
 */
@Entity
@Table(name = "leave_type")

public class LeaveType {

    @Id
    @Column(name = "leave_type_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "version")
    @Version
    private long version;

    public LeaveType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
