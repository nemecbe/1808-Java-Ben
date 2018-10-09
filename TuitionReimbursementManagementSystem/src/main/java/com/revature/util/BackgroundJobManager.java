package com.revature.util;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.revature.job.AutoUpdateRequestsJob;
import com.revature.job.ResetRequestsJob;

@WebListener
public class BackgroundJobManager implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//System.out.println("Starting up");
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new AutoUpdateRequestsJob(), 0, 1, TimeUnit.DAYS);
		scheduler.scheduleAtFixedRate(new ResetRequestsJob(), getDaysTillJan1(), 
				365, TimeUnit.DAYS);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		scheduler.shutdownNow();
	}
	
	/**
	 * Gets the number of days until the turn of the new year
	 * @return The number of days until January 1st of the next year
	 */
	private long getDaysTillJan1() {
		LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
		return endYear.minusDays(
				LocalDate.now().getDayOfYear()).getLong(
						ChronoField.DAY_OF_YEAR) + 1;
	}

}