package software.ulpgc.terrace.aws;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import software.ulpgc.terrace.Storage;
import software.ulpgc.terrace.Summary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class S3Storage implements Storage {
	private final S3Client s3Client;
	private final static String fileBucket = "terrace-file-bucket";
	private final static String summaryBucket = "terrace-summary-bucket";

	public S3Storage(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	@Override
	public String read(String fileName) {
		GetObjectRequest getObjectRequest = GetObjectRequest.builder()
				.bucket(fileBucket)
				.key(fileName)
				.build();

		return new BufferedReader(new InputStreamReader(s3Client.getObject(getObjectRequest), StandardCharsets.UTF_8))
				.lines().collect(Collectors.joining("\n"));	}

	@Override
	public void write(Summary summary) {
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(summaryBucket)
				.key(summary.filename())
				.build();
		s3Client.putObject(putObjectRequest, RequestBody.fromString(summary.toString()));
	}
}
