package common.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class RefreshRoutEvent extends RemoteApplicationEvent {
	public RefreshRoutEvent() {

	}

	public RefreshRoutEvent(Object source, String originService, String destinationService) {
		super(source, originService, destinationService);
	}
}