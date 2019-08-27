package rabbit.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface ServerStartSendMessage {

	@Output(MessageConstant.SERVER_START)
	SubscribableChannel sendServerStart();
}