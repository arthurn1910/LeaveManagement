package com.example.leave.dto.group;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Medion on 2016-09-20.
 */
public class CreateGroupDTO {
    private Long ID;

    @NotNull
    @NotEmpty
    @Size(min=2)
    private String title;

    public CreateGroupDTO() {
    }

    public CreateGroupDTO(String title) {
        this.title = title;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
