package com.api.oficina.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.oficina.model.DocumentoImg;
import com.api.oficina.service.DocumentoImgService;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class DocumentoImgImpl implements DocumentoImgService{

	private final S3Client s3Client;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;
    
    public DocumentoImgImpl(S3Client s3Client) {
    	this.s3Client = s3Client;
    }
	
	@Override
	public List<DocumentoImg> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		File file = new File("/Users/anthonyvergara/Documents/teste.png");
		
		String key = UUID.randomUUID().toString();
		
		InputStream is;
		try {
			is = new FileInputStream(file);
			s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(), software.amazon.awssdk.core.sync.RequestBody.fromInputStream(is, file.length()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return key;
	}

}
