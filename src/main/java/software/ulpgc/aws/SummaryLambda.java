package software.ulpgc.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import software.amazon.awssdk.services.s3.S3Client;
import software.ulpgc.Executor;
import software.ulpgc.Processor;
import software.ulpgc.Storage;
import software.ulpgc.TextProcessor;

public class SummaryLambda implements RequestHandler<S3Event, String> {
	private final Executor executor;

	public SummaryLambda() {
		Storage storage = new S3Storage(s3Client());
		Processor processor = new TextProcessor();
		this.executor = new Executor(storage, processor);
	}

	private static S3Client s3Client() {
		return S3Client.builder().build();
	}

	@Override
	public String handleRequest(S3Event s3Event, Context context) {
		String fileName = s3Event.getRecords().get(0).getS3().getObject().getKey();
		executor.execute(fileName);
		return "Processing complete.";
	}
}
