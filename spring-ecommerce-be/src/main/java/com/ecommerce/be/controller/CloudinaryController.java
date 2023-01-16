package com.ecommerce.be.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
@RequestMapping("/cloudinary")
public class CloudinaryController {

	Dotenv dotenv = Dotenv.load();
	Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file) {
		File files = new File(file.getOriginalFilename());
		Map uploadRes = new HashMap<>();
		try (FileOutputStream fos = new FileOutputStream(files)) {
			fos.write(file.getBytes());
			uploadRes = cloudinary.uploader().upload(files,
					ObjectUtils.asMap("use_filename", true, "unique_filename", false, "overwrite", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(uploadRes.get("url"), HttpStatus.OK);
	}

}
