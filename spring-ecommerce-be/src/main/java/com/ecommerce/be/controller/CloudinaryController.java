package com.ecommerce.be.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/image/upload")
	public ResponseEntity<?> upload(@RequestPart("file") MultipartFile[] file) {
		Map<Object, Object> map = new HashMap<>();
		Map uploadRes = new HashMap<>();
		for (int i = 0; i < file.length; i++) {
			File files = new File(file[i].getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(files)) {
				fos.write(file[i].getBytes());
			uploadRes = cloudinary.uploader().uploadLarge(files,
					ObjectUtils.asMap("use_filename", true, "unique_filename", false, "overwrite", true));
			map.put(uploadRes.get("public_id"), uploadRes.get("url"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping("/image/delete")
	public ResponseEntity<?> deleteImage(@RequestParam("imgId") String imgId) {
		Map deleteRes = new HashMap<>();
		try {
			deleteRes = cloudinary.uploader().destroy(imgId, ObjectUtils.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(deleteRes.get("result"),
				deleteRes.get("result").equals("ok") ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
