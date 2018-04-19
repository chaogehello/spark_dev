package com.hcb.spark;

/**
 * TODO 功能描述
 *
 * @author xu.zhang5
 * @date 2018/4/13 15:38
 * @since V1.0
 */
import java.util.*;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Seconds;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import scala.Tuple2;

public class SparkOnKafka_Java {
	public static void main(String[] args) throws InterruptedException {
		SparkConf conf=new SparkConf()
				.setMaster("local[2]")
				.setAppName("test");
		JavaSparkContext jsc=new JavaSparkContext(conf);
		jsc.setLogLevel("WARN");

		JavaStreamingContext jssc=new JavaStreamingContext(jsc, Durations.seconds(5));


		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "10.2.1.152:9092,10.2.1.153:9092,10.2.1.154:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
		kafkaParams.put("auto.offset.reset", "latest");
		kafkaParams.put("enable.auto.commit", false);

		Collection<String> topics = Arrays.asList("avatar_uc_jdbc","avatar_consignor_jdbc");

		final JavaInputDStream<ConsumerRecord<String, String>> stream =
				KafkaUtils.createDirectStream(
						jssc,
						LocationStrategies.PreferConsistent(),
						ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
				);
		stream.map(lines -> lines.value()).print();

				jssc.start();
		jssc.awaitTermination();
		jsc.stop();
	}
}
