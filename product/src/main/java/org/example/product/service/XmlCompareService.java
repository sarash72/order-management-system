package org.example.product.service;

import lombok.RequiredArgsConstructor;
import org.example.product.dto.configDto.XmlField;
import org.example.product.dto.configDto.XmlFile;
import org.example.product.dto.configDto.XmlFiles;
import org.example.product.entity.ConfigFileEntity;
import org.example.product.repository.ConfigFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


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
//public List<String> compare(String oldXml, String newXml) {
//    List<String> result = new ArrayList<>();
//
//    Diff diff = DiffBuilder.compare(oldXml)
//            .withTest(newXml)
//            .ignoreWhitespace()
//            .ignoreComments()
//            .checkForSimilar()
//            .withDifferenceEvaluator(DifferenceEvaluators.Default)
//            .build();
//
//    for (Difference d : diff.getDifferences()) {
//        Comparison comp = d.getComparison();
//
//        String controlValue = comp.getControlDetails().getValue() != null
//                ? comp.getControlDetails().getValue().toString()
//                : "null";
//        String testValue = comp.getTestDetails().getValue() != null
//                ? comp.getTestDetails().getValue().toString()
//                : "null";
//
//        String xPath = comp.getControlDetails().getXPath() != null
//                ? comp.getControlDetails().getXPath()
//                : comp.getTestDetails().getXPath();
//
//        switch (comp.getType()) {
//            case CHILD_NODELIST_LENGTH:
//                int oldCount = (Integer) comp.getControlDetails().getValue();
//                int newCount = (Integer) comp.getTestDetails().getValue();
//                if (newCount > oldCount) {
//                    result.add("🔹 اضافه شدن " + (newCount - oldCount) + " Node جدید در " + xPath);
//                } else if (newCount < oldCount) {
//                    result.add("🔹 حذف " + (oldCount - newCount) + " Node از " + xPath);
//                }
//                break;
//            case TEXT_VALUE:
//            case ATTR_VALUE:
//                if (!Objects.equals(controlValue, testValue)) { // فقط وقتی مقدار فرق داشته باشه
//                    result.add("🔸 تغییر مقدار:\nمسیر XML: " + xPath +
//                            "\nمقدار قدیم: " + controlValue +
//                            "\nمقدار جدید: " + testValue);
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    if (result.isEmpty()) {
//        result.add("تغییری پیدا نشد.");
//    }
//
//    return result;
//}

//    public List<String> compare(String oldXml, String newXml) {
//        List<String> result = new ArrayList<>();
//
//        Diff diff = DiffBuilder.compare(oldXml)
//                .withTest(newXml)
//                .ignoreWhitespace()
//                .ignoreComments()
//                .checkForSimilar()
//
//                // *** مهم‌ترین قسمت ***
//                //.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndAttributes("id")))
//                .withNodeMatcher(
//                        new DefaultNodeMatcher(
//                                ElementSelectors.byNameAndAttributes("name")
//                        )
//                )
//                .withDifferenceEvaluator(DifferenceEvaluators.Default)
//                .build();
//
//        for (Difference d : diff.getDifferences()) {
//            Comparison comp = d.getComparison();
//
//            String controlValue = comp.getControlDetails().getValue() != null
//                    ? comp.getControlDetails().getValue().toString()
//                    : "null";
//            String testValue = comp.getTestDetails().getValue() != null
//                    ? comp.getTestDetails().getValue().toString()
//                    : "null";
//
//            String xPath = comp.getControlDetails().getXPath() != null
//                    ? comp.getControlDetails().getXPath()
//                    : comp.getTestDetails().getXPath();
//
//            switch (comp.getType()) {
//                case CHILD_NODELIST_LENGTH:
//                    int oldCount = (Integer) comp.getControlDetails().getValue();
//                    int newCount = (Integer) comp.getTestDetails().getValue();
//                    if (newCount > oldCount) {
//                        result.add("🔹 اضافه شدن " + (newCount - oldCount) + " Node جدید در " + xPath);
//                    } else if (newCount < oldCount) {
//                        result.add("🔹 حذف " + (oldCount - newCount) + " Node از " + xPath);
//                    }
//                    break;
//
//                case TEXT_VALUE:
//                case ATTR_VALUE:
//                    if (!Objects.equals(controlValue, testValue)) {
//                        result.add("🔸 تغییر مقدار:\nمسیر XML: " + xPath +
//                                "\nمقدار قدیم: " + controlValue +
//                                "\nمقدار جدید: " + testValue);
//                    }
//                    break;
//            }
//        }
//
//        if (result.isEmpty()) {
//            result.add("تغییری پیدا نشد.");
//        }
//
//        return result;
//    }


//    public List<String> compare(String oldXml, String newXml) {
//        List<String> result = new ArrayList<>();
//
//        Diff diff = DiffBuilder
//                .compare(Input.fromString(oldXml))
//                .withTest(Input.fromString(newXml))
//                .ignoreWhitespace()              // نادیده گرفتن فاصله‌ها
//                .ignoreComments()                // نادیده گرفتن کامنت‌ها
//                .normalizeWhitespace()           // نادیده گرفتن newline و tab
//                .checkForSimilar()               // مقایسه شبیه، نه دقیق
//                .withNodeMatcher(
//                        new DefaultNodeMatcher(
//                                ElementSelectors.byNameAndAttributes("name") // مطابق کد خودت
//                        )
//                )
//                .withDifferenceEvaluator(DifferenceEvaluators.Default)
//                .build();
//
//        for (Difference d : diff.getDifferences()) {
//            Comparison comp = d.getComparison();
//
//            String controlValue = comp.getControlDetails().getValue() != null
//                    ? comp.getControlDetails().getValue().toString()
//                    : "null";
//
//            String testValue = comp.getTestDetails().getValue() != null
//                    ? comp.getTestDetails().getValue().toString()
//                    : "null";
//
//            String xPath = comp.getControlDetails().getXPath() != null
//                    ? comp.getControlDetails().getXPath()
//                    : comp.getTestDetails().getXPath();
//
//            switch (comp.getType()) {
//
//                // تعداد Node تغییر کرده
//                case CHILD_NODELIST_LENGTH:
//                    int oldCount = (Integer) comp.getControlDetails().getValue();
//                    int newCount = (Integer) comp.getTestDetails().getValue();
//
//
//                    if (newCount > oldCount) {
//                    //    result.add("🔹 اضافه شدن " + (newCount - oldCount) + " Node جدید در " + xPath);
//                      Node addedNode = (Node) comp.getTestDetails().getTarget();
//                       result.add("🔹 اضافه شدن Node جدید در " + xPath + ":\n" + nodeTagOnly(addedNode));
//
//                    } else if (newCount < oldCount) {
//                    //    result.add("🔹 حذف " + (oldCount - newCount) + " Node از " + xPath);
//                        Node removedNode = (Node) comp.getControlDetails().getTarget();
//                        result.add("🔹 حذف Node از " + xPath + ":\n" + nodeTagOnly(removedNode));
//
//                    }
//                    break;
//
//                // تغییر مقدار Text یا Attribute
//                case TEXT_VALUE:
//                case ATTR_VALUE:
//                    if (!Objects.equals(controlValue, testValue)) {
//                        result.add(
//                                "🔸 تغییر مقدار:\n" +
//                                        "مسیر XML: " + xPath +
//                                        "\nمقدار قدیم: " + controlValue +
//                                        "\nمقدار جدید: " + testValue
//                        );
//                    }
//                    break;
//            }
//        }
//
//        if (result.isEmpty()) {
//            result.add("تغییری پیدا نشد.");
//        }
//
//        return result;
//    }


//    public List<String> compare(String oldXml, String newXml) {
//        List<String> result = new ArrayList<>();
//
//        Diff diff = DiffBuilder
//                .compare(Input.fromString(oldXml))
//                .withTest(Input.fromString(newXml))
//                .ignoreWhitespace()
//                .ignoreComments()
//                .normalizeWhitespace()
//                .checkForSimilar()
//                .withNodeMatcher(
//                        new DefaultNodeMatcher(
//                                ElementSelectors.conditionalBuilder()
//                                        .whenElementIsNamed("field")
//                                        .thenUse(ElementSelectors.byNameAndAttributes("name"))
//                                        .elseUse(ElementSelectors.byName)  // مهم: برای بقیه Nodeها
//                                        .build()
//                        )
//                )
//                .build();
//
//        for (Difference d : diff.getDifferences()) {
//            Comparison comp = d.getComparison();
//            String xPath = comp.getControlDetails().getXPath() != null
//                    ? comp.getControlDetails().getXPath()
//                    : comp.getTestDetails().getXPath();
//
//            switch (comp.getType()) {
//                case CHILD_NODELIST_LENGTH:
//                    // فقط Node واقعی اضافه یا حذف شده
//                    Node oldParent = (Node) comp.getControlDetails().getTarget();
//                    Node newParent = (Node) comp.getTestDetails().getTarget();
//
//                    List<Node> oldChildren = getElementChildren(oldParent);
//                    List<Node> newChildren = getElementChildren(newParent);
//
//                    for (Node n : newChildren) {
//                        if (!oldChildren.contains(n)) {
//                            result.add("🔹 اضافه شدن Node در " + xPath + ": <" + n.getNodeName() +
//                                    nodeAttributes(n) + "/>");
//                        }
//                    }
//                    for (Node n : oldChildren) {
//                        if (!newChildren.contains(n)) {
//                            result.add("🔹 حذف Node از " + xPath + ": <" + n.getNodeName() +
//                                    nodeAttributes(n) + "/>");
//                        }
//                    }
//                    break;
//
//                case TEXT_VALUE:
//                case ATTR_VALUE:
//                    String oldVal = comp.getControlDetails().getValue() != null
//                            ? comp.getControlDetails().getValue().toString() : "null";
//                    String newVal = comp.getTestDetails().getValue() != null
//                            ? comp.getTestDetails().getValue().toString() : "null";
//                    if (!Objects.equals(oldVal, newVal)) {
//                        result.add("🔸 تغییر مقدار:\nمسیر: " + xPath +
//                                "\nقدیم: " + oldVal +
//                                "\nجدید: " + newVal);
//                    }
//                    break;
//            }
//        }
//
//        if (result.isEmpty()) result.add("تغییری پیدا نشد.");
//        return result;
//    }
//
//    // گرفتن فقط children نوع ELEMENT
//    private List<Node> getElementChildren(Node node) {
//        List<Node> list = new ArrayList<>();
//        if (node == null) return list;
//        NodeList nl = node.getChildNodes();
//        for (int i = 0; i < nl.getLength(); i++) {
//            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) list.add(nl.item(i));
//        }
//        return list;
//    }
//
//    // گرفتن attributeهای Node به صورت کوتاه
//    private String nodeAttributes(Node node) {
//        if (node.getAttributes() == null) return "";
//        NamedNodeMap map = node.getAttributes();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < map.getLength(); i++) {
//            Node attr = map.item(i);
//            sb.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
//        }
//        return sb.toString();
//    }

//    public List<String> compare(String oldXml, String newXml) {
//        List<String> result = new ArrayList<>();
//
//        Diff diff = DiffBuilder.compare(Input.fromString(oldXml))
//                .withTest(Input.fromString(newXml))
//                .ignoreWhitespace()
//                .ignoreComments()
//                .normalizeWhitespace()
//                .checkForSimilar()
//                .withNodeMatcher(new DefaultNodeMatcher(
//                        ElementSelectors.conditionalBuilder()
//                                .whenElementIsNamed("field")
//                                .thenUse(ElementSelectors.byNameAndAttributes("name"))
//                                .elseUse(ElementSelectors.byName)
//                                .build()
//                ))
//                .build();
//
//        for (Difference d : diff.getDifferences()) {
//            Comparison comp = d.getComparison();
//            ComparisonType type = comp.getType();
//
//            // اضافه یا حذف Node
//            if (type == ComparisonType.CHILD_NODELIST_LENGTH) {
//                Node oldParent = (Node) comp.getControlDetails().getTarget();
//                Node newParent = (Node) comp.getTestDetails().getTarget();
//
//                List<Node> oldChildren = getElementChildren(oldParent);
//                List<Node> newChildren = getElementChildren(newParent);
//
//                for (Node n : newChildren) {
//                    if (!containsNode(oldChildren, n)) {
//                        result.add("🔹 اضافه شدن Node: <" + n.getNodeName() + nodeAttributes(n) + "/>");
//                    }
//                }
//
//                for (Node n : oldChildren) {
//                    if (!containsNode(newChildren, n)) {
//                        result.add("🔹 حذف Node: <" + n.getNodeName() + nodeAttributes(n) + "/>");
//                    }
//                }
//            }
//
//            // تغییر attribute یا مقدار داخل Node
//            else if (type == ComparisonType.ATTR_VALUE || type == ComparisonType.TEXT_VALUE) {
//                Object oldVal = comp.getControlDetails().getValue();
//                Object newVal = comp.getTestDetails().getValue();
//
//                if (!Objects.equals(oldVal, newVal)) {
//                    Node node = comp.getControlDetails().getTarget();
//                    result.add("🔸 تغییر مقدار Node <" + (node != null ? node.getNodeName() : "unknown") + ">:\n" +
//                            "قدیم: " + oldVal + "\nجدید: " + newVal);
//                }
//            }
//        }
//
//        if (result.isEmpty()) result.add("تغییری پیدا نشد.");
//        return result;
//    }

    public List<String> compare(String oldXml, String newXml) {
        List<String> result = new ArrayList<>();

        Diff diff = DiffBuilder
                .compare(Input.fromString(oldXml))
                .withTest(Input.fromString(newXml))
                .ignoreWhitespace()
                .ignoreComments()
                .normalizeWhitespace()
                .checkForSimilar()
                .withNodeMatcher(
                        new DefaultNodeMatcher(
                                ElementSelectors.conditionalBuilder()
                                        .whenElementIsNamed("field")
                                        .thenUse(ElementSelectors.byNameAndAttributes("name"))
                                        .elseUse(ElementSelectors.byName)
                                        .build()
                        )
                )
                .build();

        for (Difference d : diff.getDifferences()) {
            Comparison comp = d.getComparison();

            Node oldNode = comp.getControlDetails().getTarget();
            Node newNode = comp.getTestDetails().getTarget();
            String fileName="";

            switch (comp.getType()) {
                case CHILD_NODELIST_LENGTH -> {
                    // اضافه یا حذف Node
                    List<Node> oldChildren = getElementChildren(oldNode);
                    List<Node> newChildren = getElementChildren(newNode);

                    for (Node n : newChildren) {
                        if (!containsNode(oldChildren, n)) {
                             fileName = getFileNameFromNode(n);

                            result.add("🔹 اضافه شدن Node در فایل<" + fileName + ">: <" + n.getNodeName() +
                                    nodeAttributes(n) + "/>");
                        }
                    }
                    for (Node n : oldChildren) {
                        if (!containsNode(newChildren, n)) {
                             fileName = getFileNameFromNode(n);

                            result.add("🔹 حذف Node از فایل<" + fileName + ">: <" + n.getNodeName() +
                                    nodeAttributes(n) + "/>");
                        }
                    }
                }
                case TEXT_VALUE, ATTR_VALUE -> {
                    if (oldNode != null && newNode != null) {
                        fileName = getFileNameFromNode(newNode);

                        result.add("🔹 تغییر Node در فایل<" + fileName + ">: <" + newNode.getNodeName() +
                                nodeAttributes(newNode) + "/>");
                    }
                }
            }
        }

        if (result.isEmpty()) result.add("تغییری پیدا نشد.");
        return result;
    }


    // بررسی برابری Node براساس name (برای <field>)
    private boolean containsNode(List<Node> list, Node target) {
        for (Node n : list) {
            if (n.getNodeName().equals(target.getNodeName())) {
                Node attr1 = n.getAttributes() != null ? n.getAttributes().getNamedItem("name") : null;
                Node attr2 = target.getAttributes() != null ? target.getAttributes().getNamedItem("name") : null;
                if (Objects.equals(attr1 != null ? attr1.getNodeValue() : null,
                        attr2 != null ? attr2.getNodeValue() : null)) {
                    return true;
                }
            }
        }
        return false;
    }


    // گرفتن فقط children نوع ELEMENT
    private List<Node> getElementChildren(Node node) {
        List<Node> list = new ArrayList<>();
        if (node == null) return list;
        NodeList nl = node.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) list.add(nl.item(i));
        }
        return list;
    }

    // گرفتن attributeهای Node به صورت کوتاه
    private String nodeAttributes(Node node) {
        if (node.getAttributes() == null) return "";
        NamedNodeMap map = node.getAttributes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.getLength(); i++) {
            Node attr = map.item(i);
            sb.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
        }
        return sb.toString();
    }
    private String getFileNameFromNode(Node node) {
        Node current = node;
        while (current != null) {
            if ("file".equals(current.getNodeName())) {
                Node nameAttr = current.getAttributes().getNamedItem("name");
                if (nameAttr != null) {
                    return nameAttr.getNodeValue();
                }
            }
            current = current.getParentNode();
        }
        return "نام‌فایل-نامشخص";
    }

    public List<String> uploadFile(MultipartFile file, String description) throws IOException {
        String newXml = new String(file.getBytes(), StandardCharsets.UTF_8);

        // پیدا کردن آخرین نسخه موجود
        Optional<ConfigFileEntity> last = Optional.ofNullable(repository.findLatestVersion());

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

    /**
     * تبدیل Node به رشته XML کامل (با متن و Attributeها)
     */
//    private String nodeToString(Node node) {
//        try {
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            StringWriter writer = new StringWriter();
//            transformer.transform(new DOMSource(node), new StreamResult(writer));
//            return writer.toString();
//        } catch (Exception e) {
//            return node.getNodeName(); // fallback ساده
//        }
//    }

    private String nodeTagOnly(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(node.getNodeName());

        NamedNodeMap attrs = node.getAttributes();
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node attr = attrs.item(i);
                sb.append(" ").append(attr.getNodeName())
                        .append("=\"").append(attr.getNodeValue()).append("\"");
            }
        }

        sb.append("/>"); // بدون children
        return sb.toString();
    }

    private static final XmlMapper xmlMapper = new XmlMapper();

    public void checkConfigChanges(MultipartFile newFile) throws IOException {

        // تبدیل فایل MultipartFile به رشته
        String newXml = new String(newFile.getBytes(), StandardCharsets.UTF_8);

        // پیدا کردن آخرین نسخه موجود
        Optional<ConfigFileEntity> last = Optional.ofNullable(repository.findLatestVersion());

        List<String> diffResult = new ArrayList<>();
        String newVersion;
        String previousVersion;

        if (last.isPresent()) {

            String oldXml = new String(last.get().getFile(), StandardCharsets.UTF_8);

            // پارس XMLها به Entity
            XmlFiles oldCfg = xmlMapper.readValue(oldXml, XmlFiles.class);
            XmlFiles newCfg = xmlMapper.readValue(newXml, XmlFiles.class);

            ConfigDiffService diffService = new ConfigDiffService();
            // مقایسه
            diffResult= diffService.diff(oldCfg, newCfg);

            // چاپ تغییرات
            diffResult.forEach(System.out::println);
            // ساختن version جدید
            previousVersion = last.get().getVersion();
            newVersion = "v" + (Integer.parseInt(previousVersion.replace("v", "")) + 1);

        }  else {
        // اولین بار
        diffResult.add("اولین نسخه فایل ذخیره شد.");
        previousVersion = "-";
        newVersion = "v1";
    }

    // ذخیره در دیتابیس
    ConfigFileEntity entity = ConfigFileEntity.builder()
            .file(newFile.getBytes())
            .description("description")
            .version(newVersion)
            .previousVersion(previousVersion)
            .build();

        repository.save(entity);
    }
}

