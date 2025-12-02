package org.example.product.controller;


import lombok.RequiredArgsConstructor;
import org.example.product.dto.UploadRequest;
import org.example.product.entity.Product;
import org.example.product.service.ProductService;
import org.example.product.service.XmlCompareService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final XmlCompareService xmlCompareService;

    @GetMapping
    public List<Product> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return service.save(p);
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> upload(@ModelAttribute UploadRequest request) throws Exception {

        MultipartFile file = request.getFile();

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of("File is empty!"));
        }

        List<String> changes = xmlCompareService.uploadFile(file, "description");

        String xmlContent = new String(file.getBytes());

        System.out.println("XML = " + xmlContent);

        return ResponseEntity.ok(changes);
    }

    @PostMapping("/upload-xml")
    public String uploadXml(@RequestParam("file") MultipartFile file) throws Exception {

        // ساخت Document از InputStream فایل
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file.getInputStream());

        // گرفتن لیست همه <file>
        NodeList fileNodes = doc.getElementsByTagName("file");
        int fileCount = fileNodes.getLength();


        StringBuilder sb = new StringBuilder();
        sb.append("تعداد فایل‌ها: ").append(fileCount).append("\n");

        // برای هر فایل، تعداد <field> هایش را بشمار
        for (int i = 0; i < fileCount; i++) {
            NodeList fieldNodes = ((org.w3c.dom.Element) fileNodes.item(i))
                    .getElementsByTagName("field");
            sb.append("فایل ").append(i + 1)
                    .append(" تعداد فیلدها: ").append(fieldNodes.getLength()).append("\n");
        }

        return sb.toString();
    }

    @PostMapping("/check-changes")
    public ResponseEntity<?> checkConfigChanges(@RequestParam("file") MultipartFile newFile) {
        try {
            xmlCompareService.checkConfigChanges(newFile); // سرویس خودش oldXml رو از DB می‌گیره
            return ResponseEntity.ok("Comparison done! Check console for details.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading file: " + e.getMessage());
        }
    }
}