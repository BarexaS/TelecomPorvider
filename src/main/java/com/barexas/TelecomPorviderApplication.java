package com.barexas;

import com.barexas.services.bills.BillService;
import com.barexas.services.taxcollector.TaxCollector;
import com.barexas.services.taxcollector.TaxCollectorImpl;
import com.barexas.services.transactions.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class TelecomPorviderApplication extends AsyncConfigurerSupport {

	public static void main(String[] args) {
		SpringApplication.run(TelecomPorviderApplication.class, args);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TaxCollector taxCollector;

	@Bean
	public CommandLineRunner demo() {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				List<String> result = jdbcTemplate.query("SELECT * FROM `price_model`", new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("name");
					}
				});
				if (result.isEmpty()) {
					jdbcTemplate.execute("INSERT INTO `price_model` (`name`,`tax`) VALUES ('Standard - 10$ per month',10.0)");
					jdbcTemplate.execute("INSERT INTO `price_model` (`name`,`tax`) VALUES ('VIP - 100$ per year',8.34)");
				}

				taxCollector.collectTax();
			}
		};
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Collector-");
		executor.initialize();
		return executor;
	}
}
