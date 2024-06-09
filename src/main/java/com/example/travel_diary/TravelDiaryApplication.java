package com.example.travel_diary;

import com.google.cloud.storage.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.google.cloud.storage.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class TravelDiaryApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TravelDiaryApplication.class, args);

		Storage storage =  StorageOptions.newBuilder().setProjectId("titanium-vision-424101-s9").build().getService();

		// Create a bucket
		String bucketName = "jorang"; // Change this to something unique
//		Bucket bucket = storage.create(BucketInfo.of(bucketName));
//		System.out.println(bucket.getName());
		// Upload a blob to the newly created bucket
		BlobId blobId = BlobId.of(bucketName, "image");
//		BlobId blobId2 = BlobId.of(bucketName, "jinho");

		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		String imgPath = "C:\\Users\\Playdata\\Pictures\\Screenshots\\tmp.png";

		storage.createFrom(blobInfo, Paths.get(imgPath));

//		Blob blob = storage.create(blobInfo, "Hello Jinho".getBytes(UTF_8));
//		for (BlobId blobId : blobIds) {
//			byte[] content = storage.readAllBytes(blobId);
//			String contentString = new String(content, UTF_8);
//			System.out.println(contentString);
//		}

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
