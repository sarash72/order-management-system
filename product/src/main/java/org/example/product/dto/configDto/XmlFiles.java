package org.example.product.dto.configDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@JacksonXmlRootElement(localName = "files")
@Data
public class XmlFiles {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "file")
    private List<XmlFile> files;

}
