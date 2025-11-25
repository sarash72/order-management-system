package org.example.product.service;

import lombok.RequiredArgsConstructor;
import org.example.product.entity.ConfigFileEntity;
import org.example.product.repository.ConfigFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlunit.builder.*;
import org.xmlunit.diff.*;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
public class XmlCompareService {

    private final ConfigFileRepository repository;


//    public List<String> compare(String oldXml, String newXml) {
//
//        Diff diff = DiffBuilder.compare(oldXml)
//                .withTest(newXml)
//                .ignoreWhitespace()
//                .ignoreComments()
//                .checkForSimilar()
//                .withDifferenceEvaluator(DifferenceEvaluators.Default)
//                .withComparisonFormatter(new DefaultComparisonFormatter())
//                .build();
//
//        List<String> result = new ArrayList<>();
//
//        for (Difference d : diff.getDifferences()) {
//
//            Comparison comp = d.getComparison();
//            String message = """
//                🔸 تغییر یافت:
//                نوع تغییر: %s
//                مسیر XML: %s
//                مقدار قدیم: %s
//                مقدار جدید: %s
//                """.formatted(
//                    comp.getType(),
//                    comp.getControlDetails().getXPath(),
//                    comp.getControlDetails().getValue(),
//                    comp.getTestDetails().getValue()
//            );
//
//            result.add(message);
//        }
//
//        return result;
//    }
public List<String> compare(String oldXml, String newXml) {
    List<String> result = new ArrayList<>();

    Diff diff = DiffBuilder.compare(oldXml)
            .withTest(newXml)
            .ignoreWhitespace()
            .ignoreComments()
            .checkForSimilar()
            .withDifferenceEvaluator(DifferenceEvaluators.Default)
            .build();

    for (Difference d : diff.getDifferences()) {
        Comparison comp = d.getComparison();

        String controlValue = comp.getControlDetails().getValue() != null
                ? comp.getControlDetails().getValue().toString()
                : "null";
        String testValue = comp.getTestDetails().getValue() != null
                ? comp.getTestDetails().getValue().toString()
                : "null";

        String xPath = comp.getControlDetails().getXPath() != null
                ? comp.getControlDetails().getXPath()
                : comp.getTestDetails().getXPath();

        switch (comp.getType()) {
            case CHILD_NODELIST_LENGTH:
                int oldCount = (Integer) comp.getControlDetails().getValue();
                int newCount = (Integer) comp.getTestDetails().getValue();
                if (newCount > oldCount) {
                    result.add("🔹 اضافه شدن " + (newCount - oldCount) + " Node جدید در " + xPath);
                } else if (newCount < oldCount) {
                    result.add("🔹 حذف " + (oldCount - newCount) + " Node از " + xPath);
                }
                break;
            case TEXT_VALUE:
            case ATTR_VALUE:
                if (!Objects.equals(controlValue, testValue)) { // فقط وقتی مقدار فرق داشته باشه
                    result.add("🔸 تغییر مقدار:\nمسیر XML: " + xPath +
                            "\nمقدار قدیم: " + controlValue +
                            "\nمقدار جدید: " + testValue);
                }
                break;
            default:
                break;
        }
    }

    if (result.isEmpty()) {
        result.add("تغییری پیدا نشد.");
    }

    return result;
}
    public List<String> uploadFile(MultipartFile file, String description) throws IOException {
        String newXml = new String(file.getBytes(), StandardCharsets.UTF_8);

        // پیدا کردن آخرین نسخه موجود
        Optional<ConfigFileEntity> last = Optional.ofNullable(repository.findFirstByOrderByVersionDesc());

        List<String> diffResult = new ArrayList<>();
        String newVersion;
        String previousVersion;

        if (last.isPresent()) {

            String oldXml = new String(last.get().getFile(), StandardCharsets.UTF_8);

            // مقایسه XML ها
            diffResult = compare(oldXml, newXml);

            // ساختن version جدید
            previousVersion = last.get().getVersion();
            newVersion = "v" + (Integer.parseInt(previousVersion.replace("v", "")) + 1);

        } else {
            // اولین بار
            diffResult.add("اولین نسخه فایل ذخیره شد.");
            previousVersion = "-";
            newVersion = "v1";
        }

        // ذخیره در دیتابیس
        ConfigFileEntity entity = ConfigFileEntity.builder()
                .file(file.getBytes())
                .description(description)
                .version(newVersion)
                .previousVersion(previousVersion)
                .build();

        repository.save(entity);

        return diffResult;
    }

}

