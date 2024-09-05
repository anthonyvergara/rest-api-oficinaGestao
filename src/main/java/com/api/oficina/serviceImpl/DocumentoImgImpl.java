package com.api.oficina.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.oficina.model.Cliente;
import com.api.oficina.model.DocumentoImg;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.DocumentoImgRepository;
import com.api.oficina.service.DocumentoImgService;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class DocumentoImgImpl implements DocumentoImgService{

	private final S3Client s3Client;
	private final ClienteRepository clienteRepository;
	private final DocumentoImgRepository documentoRepository;
	
	@Value("${aws.s3.bucket.name}")
	private String bucketName;
    
    public DocumentoImgImpl(S3Client s3Client, ClienteRepository clienteRepository, DocumentoImgRepository documentoRepository) {
    	this.s3Client = s3Client;
    	this.clienteRepository = clienteRepository;
    	this.documentoRepository = documentoRepository;
    }
	
	@Override
	public List<DocumentoImg> listAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> save(List<MultipartFile> file, Long id) {
		
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		
		List<String> keys = new ArrayList();
		
		try {
			for(MultipartFile f : file) {
				CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->{
					String fileUrlKey = upload(f);
					
					DocumentoImg doc = new DocumentoImg();
					doc.setKey(fileUrlKey);
					doc.setCliente(cliente.get());
					this.documentoRepository.save(doc);
					System.out.println("save: "+fileUrlKey);
                    return fileUrlKey;
				});
				
				keys.add(future.get().toString());
			}
		}catch(RuntimeException | InterruptedException | ExecutionException e) {
			e.getMessage();
		}
		
		return keys;
	}
	
	
	public String upload(MultipartFile file) {
		
		String key = UUID.randomUUID().toString();
		try (InputStream fileInput = file.getInputStream()){
			s3Client.putObject(PutObjectRequest.builder()
					.bucket(bucketName)
					.key(key)
					.build()
					, RequestBody.fromInputStream(fileInput,file.getSize()));
			
		}catch(AwsServiceException | IOException e) {
			e.getMessage();
		}
		
		return key;
	}

}
