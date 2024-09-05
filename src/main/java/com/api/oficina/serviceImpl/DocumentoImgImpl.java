package com.api.oficina.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.api.oficina.model.DocumentoImg;
import com.api.oficina.service.DocumentoImgService;
import com.api.oficina.service.FileUpload;

@Service
public class DocumentoImgImpl implements DocumentoImgService{

	private final AmazonS3 amazonS3;

    private String bucketName = "imagensdoc";
    
    public DocumentoImgImpl(AmazonS3 amazonS3) {
    	this.amazonS3 = amazonS3;
    }
	
	@Override
	public List<DocumentoImg> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() {
		File file = new File("/Users/anthonyvergara/Documents/curriculo.pdf");
		
		String key = UUID.randomUUID().toString();
		
		amazonS3.putObject(bucketName, key, file);
		
		
		return "cacaca";
	}

}
