//package org.example.product.controller;
//
//
//import org.example.product.entity.Product;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/test")
//public class MyController {
////
////    private MyService myService;
////
////    public MyController(MyService myService) {
////        this.myService = myService;
////    }
//
//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> upload(@ModelAttribute Product request) throws Exception {
//
//        MultipartFile file = request.getFile();
//
//        if (file == null || file.isEmpty()) {
//            return ResponseEntity.badRequest().body("File is empty!");
//        }
//
//        String xmlContent = new String(file.getBytes());
//
//        System.out.println("XML = " + xmlContent);
//
//        return ResponseEntity.ok("XML uploaded successfully!");
//    }
//}
