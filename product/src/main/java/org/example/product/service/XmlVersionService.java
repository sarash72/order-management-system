//package org.example.product.service;
//
//import org.example.product.entity.ConfigFileEntity;
//import org.example.product.repository.ConfigFileRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class XmlVersionService {
//
//    private final ConfigFileRepository repository;
//    private final XmlCompareService compareService;
//
//    public XmlVersionService(ConfigFileRepository repository, XmlCompareService compareService) {
//        this.repository = repository;
//        this.compareService = compareService;
//    }
//
//    public List<String> uploadNewVersion(String xmlContent) {
//
//        // نسخه قبلی را پیدا کن
//        Optional<ConfigFileEntity> lastVersion = Optional.ofNullable(repository.findFirstByOrderByVersionDesc());
//
//        List<String> changes = new ArrayList<>();
//
//        if (lastVersion.isPresent()) {
//            String oldXml = lastVersion.get().getXmlContent();
//            changes = compareService.compare(oldXml, xmlContent);
//        } else {
//            changes.add("اولین نسخه ذخیره شد. تغییر خاصی وجود ندارد.");
//        }
//
//        // نسخه جدید را ذخیره کن
//        ConfigFileEntity newVersion = new ConfigFileEntity();
//        newVersion.setXmlContent(xmlContent);
//        newVersion.setVersion(lastVersion.map(v -> v.getVersion() + 1).orElse(1));
//        repository.save(newVersion);
//
//        return changes;
//    }
//}
