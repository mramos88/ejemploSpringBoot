package sicaback.start;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.qos.logback.classic.Logger;
import sicaback.alfresco.Greeting;

@RestController
public class GreetingController {
	  private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();

	    @RequestMapping("/greeting")
	    public ResponseEntity<InputStreamResource> greeting(@RequestParam(value="name") String name) {
	    	System.out.println("llego"+name);
	    	  	File f = new File("./src/main/java/sicaback/start/Chrysanthemum.jpg");
	    		  InputStreamResource resource=null;
				try {
					resource = new InputStreamResource(new FileInputStream(f));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	      HttpHeaders headers = new HttpHeaders();
	    	      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
	    	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    	        headers.add("Pragma", "no-cache");
	    	        headers.add("Expires", "0");
	    	   return ResponseEntity.ok()
	    	            .headers(headers)
	    	            .contentLength(f.length())
	    	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	    	            .body(resource);
	    }

}
