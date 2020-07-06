package weatherNotification.weather.Libraries;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class SqsClient {

    private AmazonSQS basicSqsClient;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.sqsurl}")
    private String sqsURL;

    @Value("${cloud.aws.credentials.accessKey}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String awsSecretKey;

    public AmazonSQS getBasicSqsClient() {
        return basicSqsClient;
    }

    public void setBasicSqsClient(AmazonSQS basicSqsClient) {
        this.basicSqsClient = basicSqsClient;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    public String getSqsURL() {
        return sqsURL;
    }

    public void setSqsURL(String sqsURL) {
        this.sqsURL = sqsURL;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public void setAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }

    public SqsClient(){}

    @PostConstruct
    public void init(){
        this.basicSqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.awsAccessKey, this.awsSecretKey)))
                .withRegion(this.awsRegion)
                .build();
    }

    public SendMessageResult sendMessage(String body, String messageGroupId, String messageDeduplicationId, Map<String, MessageAttributeValue> attributes){
        SendMessageRequest messageRequest = new SendMessageRequest(this.sqsURL, body)
                .withMessageGroupId(messageGroupId)
                .withMessageDeduplicationId(messageDeduplicationId)
                .withMessageAttributes(attributes);
        SendMessageResult result = this.basicSqsClient.sendMessage(messageRequest);
        return result;

    }

}
