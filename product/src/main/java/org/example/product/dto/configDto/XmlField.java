package org.example.product.dto.configDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlField {


    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String fa;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(isAttribute = true)
    private String datatype;

    @JacksonXmlProperty(isAttribute = true)
    private Integer size;

    @JacksonXmlProperty(isAttribute = true)
    private String regex;

    @JacksonXmlProperty(isAttribute = true)
    private String valueType;

    @JacksonXmlProperty(isAttribute = true)
    private String value;

    @JacksonXmlProperty(isAttribute = true)
    private String client;

    @JacksonXmlProperty(isAttribute = true)
    private String iname;

    @JacksonXmlProperty(isAttribute = true)
    private String dbname;

    @JacksonXmlProperty(isAttribute = true)
    private String validation;

    @JacksonXmlProperty(localName = "func")
    private String func;

    @JacksonXmlProperty(localName = "val")
    private String val;
}
