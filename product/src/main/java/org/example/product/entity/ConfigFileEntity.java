package org.example.product.entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Arrays;


@Entity
@Table(
        name = "CONFIG_FILE_TABLE"
)
public class ConfigFileEntity {
    private static final String CONFIG_FILE_ENTITY_GENERATOR = "config_file_entity_generator";
    @Column(
            name = "ID"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "config_file_entity_generator"
    )
    @SequenceGenerator(
            name = "config_file_entity_generator",
            sequenceName = "CONFIG_FILE_SEQUENCE",
            initialValue = 3,
            allocationSize = 1
    )
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGTEXT",
            name = "VALUE_FILE"
    )
    private byte[] file;
    @Column(
            name = "DESCRIPTION",
            length = 256
    )
    private String description;
    @Column(
            name = "VERSION",
            length = 30
    )
    private String version;
    @Column(
            name = "PREVIOUS_VERSION",
            length = 30
    )
    private String previousVersion;

    public static ConfigFileEntityBuilder builder() {
        return new ConfigFileEntityBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ConfigFileEntity)) {
            return false;
        } else {
            ConfigFileEntity other = (ConfigFileEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return super.equals(o);
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConfigFileEntity;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }

    public ConfigFileEntity() {
    }

    public ConfigFileEntity(final Long id, final byte[] file, final String description, final String version, final String previousVersion) {
        this.id = id;
        this.file = file;
        this.description = description;
        this.version = version;
        this.previousVersion = previousVersion;
    }

    public Long getId() {
        return this.id;
    }

    public byte[] getFile() {
        return this.file;
    }

    public String getDescription() {
        return this.description;
    }

    public String getVersion() {
        return this.version;
    }

    public String getPreviousVersion() {
        return this.previousVersion;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFile(final byte[] file) {
        this.file = file;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public void setPreviousVersion(final String previousVersion) {
        this.previousVersion = previousVersion;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "ConfigFileEntity(id=" + var10000 + ", file=" + Arrays.toString(this.getFile()) + ", description=" + this.getDescription() + ", version=" + this.getVersion() + ", previousVersion=" + this.getPreviousVersion() + ")";
    }

    public static class ConfigFileEntityBuilder {
        private Long id;
        private byte[] file;
        private String description;
        private String version;
        private String previousVersion;

        ConfigFileEntityBuilder() {
        }

        public ConfigFileEntityBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ConfigFileEntityBuilder file(final byte[] file) {
            this.file = file;
            return this;
        }

        public ConfigFileEntityBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public ConfigFileEntityBuilder version(final String version) {
            this.version = version;
            return this;
        }

        public ConfigFileEntityBuilder previousVersion(final String previousVersion) {
            this.previousVersion = previousVersion;
            return this;
        }

        public ConfigFileEntity build() {
            return new ConfigFileEntity(this.id, this.file, this.description, this.version, this.previousVersion);
        }

        public String toString() {
            Long var10000 = this.id;
            return "ConfigFileEntity.ConfigFileEntityBuilder(id=" + var10000 + ", file=" + Arrays.toString(this.file) + ", description=" + this.description + ", version=" + this.version + ", previousVersion=" + this.previousVersion + ")";
        }
    }
}
