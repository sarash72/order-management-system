package org.example.product.service;


import org.example.product.dto.configDto.XmlField;
import org.example.product.dto.configDto.XmlFile;
import org.example.product.dto.configDto.XmlFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConfigDiffService {

    public List<String> diff(XmlFiles oldCfg, XmlFiles newCfg) {
        List<String> changes = new ArrayList<>();

        Map<String, XmlFile> oldFiles = oldCfg.getFiles().stream()
                .collect(Collectors.toMap(XmlFile::getName, f -> f));

        Map<String, XmlFile> newFiles = (Map<String, XmlFile>) newCfg.getFiles().stream()
              .collect(Collectors.toMap(XmlFile::getName, f -> f));

        // چک کردن فایل‌ها
        for (String fileName : newFiles.keySet()) {
            XmlFile newFile = newFiles.get(fileName);
            XmlFile oldFile = oldFiles.get(fileName);

            if (oldFile == null) {
                changes.add("NEW FILE: " + fileName);
                continue;
            }

            changes.addAll(diffFields(fileName, oldFile.getFields().getField(), newFile.getFields().getField()));
        }

        return changes;
    }

    private List<String> diffFields(String fileName,
                                    List<XmlField> oldFields,
                                    List<XmlField> newFields) {

        List<String> changes = new ArrayList<>();

        Map<String, XmlField> oldMap = oldFields.stream()
                .collect(Collectors.toMap(XmlField::getName, f -> f));

        Map<String, XmlField> newMap = newFields.stream()
                .collect(Collectors.toMap(XmlField::getName, f -> f));

        for (String fieldName : newMap.keySet()) {
            XmlField newF = newMap.get(fieldName);
            XmlField oldF = oldMap.get(fieldName);

            if (oldF == null) {
                changes.add("file: " + fileName +
                            " → NEW FIELD: " + fieldName);
                continue;
            }


            compareFieldAttributes(fileName, changes, fieldName, oldF, newF);
        }
        for (String fieldName : oldMap.keySet()) {
            XmlField newF = newMap.get(fieldName);
            XmlField oldF = oldMap.get(fieldName);

            if (newF == null) {
                changes.add("file: " + fileName +
                            " → delete FIELD: " + fieldName);
                continue;
            }


            compareFieldAttributes(fileName, changes, fieldName, oldF, newF);
        }


        return changes;
    }

    private void compareFieldAttributes(
            String fileName,
            List<String> changes,
            String fieldName,
            XmlField oldF,
            XmlField newF
    ) {
        compare(changes, fileName, fieldName, "type", oldF.getType(), newF.getType());
        compare(changes, fileName, fieldName, "datatype", oldF.getDatatype(), newF.getDatatype());
        compare(changes, fileName, fieldName, "size", oldF.getSize(), newF.getSize());
        compare(changes, fileName, fieldName, "regex", oldF.getRegex(), newF.getRegex());
        compare(changes, fileName, fieldName, "valueType", oldF.getValueType(), newF.getValueType());
        compare(changes, fileName, fieldName, "value", oldF.getValue(), newF.getValue());
        compare(changes, fileName, fieldName, "client", oldF.getClient(), newF.getClient());
        compare(changes, fileName, fieldName, "iname", oldF.getIname(), newF.getIname());
        compare(changes, fileName, fieldName, "func", oldF.getFunc(), newF.getFunc());
        compare(changes, fileName, fieldName, "val", oldF.getVal(), newF.getVal());
    }

    private void compare(List<String> result,
                         String file,
                         String field,
                         String attr,
                         Object oldVal,
                         Object newVal) {

        if (!Objects.equals(oldVal, newVal)) {
            result.add("file: " + file +
                       ", field: " + field +
                       " → " + attr +
                       " changed: [" + oldVal + "] → [" + newVal + "]");
        }
    }
}
