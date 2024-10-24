package software.ulpgc.terrace.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import software.ulpgc.terrace.Executor;

public class SummaryLambda implements RequestHandler<S3Event, String> {

	private final Executor executor;

	public SummaryLambda(Executor executor) {
		this.executor = executor;
	}

	@Override
	public String handleRequest(S3Event s3Event, Context context) {
		String fileName = s3Event.getRecords().get(0).getS3().getObject().getKey();
		executor.execute(fileName);
		return "Processing complete.";
	}
}
