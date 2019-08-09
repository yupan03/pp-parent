package common.event;

import org.springframework.context.ApplicationListener;

public class RefreshRoutListener implements ApplicationListener<RefreshRoutEvent> {

	@Override
	public void onApplicationEvent(RefreshRoutEvent event) {
		System.out.println("刷新路由" + event.getId() + event.getOriginService());
	}
}