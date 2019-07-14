package s1p.tour.philly.userclicksperregion;

import java.util.function.BiFunction;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Serialized;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserClicksPerRegionApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserClicksPerRegionApplication.class, args);
	}

	@Bean
	public BiFunction<KStream<String, Long>, KTable<String, String>, KStream<String, Long>> clicks() {
		return (userClicksStream, userRegionsTable) -> (userClicksStream
				.leftJoin(userRegionsTable, (clicks, region) -> new RegionWithClicks(region == null ?
								"UNKNOWN" : region, clicks),
						Joined.with(Serdes.String(), Serdes.Long(), null))
				.map((user, regionWithClicks) -> new KeyValue<>(regionWithClicks.getRegion(),
						regionWithClicks.getClicks()))
				.groupByKey(Serialized.with(Serdes.String(), Serdes.Long()))
				.reduce(Long::sum)
				.toStream());
	}

	/**
	 * Tuple for a region and its associated number of clicks.
	 */
	private static final class RegionWithClicks {

		private final String region;
		private final long clicks;

		RegionWithClicks(String region, long clicks) {
			if (region == null || region.isEmpty()) {
				throw new IllegalArgumentException("region must be set");
			}
			if (clicks < 0) {
				throw new IllegalArgumentException("clicks must not be negative");
			}
			this.region = region;
			this.clicks = clicks;
		}

		public String getRegion() {
			return region;
		}

		public long getClicks() {
			return clicks;
		}

	}


}
