package tr.com.thy.sensordatainterpreter.db.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * The Class MongoConfig is used to connect to Mongo DB.
 */
@Configuration
public class MongoConfig {
	
	/** The mongo connection string. */
	@Value("${mongoConnectionString}")
	private String mongoConnectionString;
	
	/** The mongo db name. */
	@Value("${mongoDbName}")
	private String mongoDbName;

	/**
	 * Mongo client settings.
	 *
	 * @return the mongo client
	 */
	@Bean
	public MongoClient mongo() {
		ConnectionString connectionString = new ConnectionString(mongoConnectionString);
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	/**
	 * Mongo template.
	 *
	 * @return the mongo template
	 * @throws Exception the exception
	 */
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), mongoDbName);
	}
}
