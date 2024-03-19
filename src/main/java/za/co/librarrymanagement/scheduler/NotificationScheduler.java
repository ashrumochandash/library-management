package za.co.librarrymanagement.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import za.co.librarrymanagement.service.LibrarryManagementServiceImpl;

@Component
public class NotificationScheduler {
		
	@Autowired
	private LibrarryManagementServiceImpl librarryManagementService;
	
	
	/****
	 *  Generating Overdue Notices 
	 *  */
	@Scheduled(cron = "0 0 19 * * *")
	public void generateOverdueNotice() {
		librarryManagementService.generateOverdueNotice();
		
	}

}
