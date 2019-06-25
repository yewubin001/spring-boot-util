package com.example.springboot.menu;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SysResource entity.
 */
public class SysResourceDTO implements Serializable {

    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String code;

    @Size(max = 100)
    private String url;

    @Size(max = 50)
    private String parentCode;

    private Integer status;

    @Size(max = 50)
    private String icon;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysResourceDTO sysResourceDTO = (SysResourceDTO) o;
        if (sysResourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysResourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysResourceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", url='" + getUrl() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
