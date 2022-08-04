package com.example.catchtable.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.catchtable.dto.image.UploadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<UploadResponseDto> uploadImageV1(List<MultipartFile> files) {
        // 응답 리스트
        List<UploadResponseDto> responseDtos = new ArrayList<>();

        for (MultipartFile file : files) {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            try {
                // 이미지 업로드
                PutObjectRequest por = new PutObjectRequest(bucket, filename, file.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead);
                amazonS3.putObject(por);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 문제가 발생했습니다");
            }
            responseDtos.add(new UploadResponseDto(amazonS3.getUrl(bucket, filename).toString(), filename));
        }
        return responseDtos;
    }

    public List<String> uploadImageV2(List<MultipartFile> files) {
        // 응답 리스트
        List<String> responseDtos = new ArrayList<>();

        for (MultipartFile file : files) {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            try {
                // 이미지 업로드
                PutObjectRequest por = new PutObjectRequest(bucket, filename, file.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead);
                amazonS3.putObject(por);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 문제가 발생했습니다");
            }
            responseDtos.add((amazonS3.getUrl(bucket, filename).toString()));
        }
        return responseDtos;
    }

    public List<String> deleteImages(List<String> filenames) {
        try {
            DeleteObjectsRequest dor = new DeleteObjectsRequest(bucket)
//                    .withKeys(filenames.toArray(String[]::new));
                    .withKeys(filenames.toArray(new String[0]));
            amazonS3.deleteObjects(dor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 삭제 중 문제가 발생했습니다");
        }
        return filenames;
    }
}