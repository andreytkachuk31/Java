package com.epam.core.file.json;

import com.google.common.base.Objects;

/**
 * @author Andrii_Tkachuk
 * @since 10/28/2015
 */
public class JsonEntry {

    private String key;
    private String value;

    public JsonEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonEntry other = (JsonEntry) o;

        return Objects.equal(key, other.key) || Objects.equal(value, other.value);
    }
}
