package cybersoft.example.cozastore.controller;

import cybersoft.example.cozastore.exception.CustomFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/demo")
public class DemoUploadFileController {
    //Path: chua toan bo ham ho tro san lien quan toi duong dan
    @Value("${path.root}")
    private String spath;

    @GetMapping("/{filename}")
    public ResponseEntity<?>loafFile(@PathVariable String filename){
       Path rootPath=Paths.get(spath);
       try{
           Resource resource=new UrlResource(rootPath.resolve(filename).toUri());
        if(resource.exists()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attach; filename=\"" +resource.getFilename()+ "\"")
                    .body(resource);
        }else{
            //nem loi va dung code
            throw new CustomFileNotFoundException(200,"File Not Found");
        }
       }
       catch (Exception e){
           throw new CustomFileNotFoundException(200,"File Not Found");
       }


    }
    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file
            ){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
    //dinh nghia duong dan lay file anh
        Path rootPath= Paths.get(spath);
    try{
        if(!Files.exists(rootPath)){
            //tao folder co duogn dan neu duong dan khong ton tai
            Files.createDirectories(rootPath);
        }
        //resolve <=>/
      //  file.getOriginalFilename(): lay ten file va ding dang
        String fileName = file.getOriginalFilename();
        Files.copy(file.getInputStream(),rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return ResponseEntity.ok("File uploaded successfully");
    }catch(Exception e){
        System.out.println("Loi" + e.getLocalizedMessage());

    }


        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
