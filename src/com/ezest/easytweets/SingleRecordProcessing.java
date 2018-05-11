package com.ezest.easytweets;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class SingleRecordProcessing {
	TweetConverter tweetConverter=new TweetConverter();
	TweetsPosting tweets=new TweetsPosting();
	public void processRecord(final Consumer<Long, String> consumer) {
		final ConsumerRecords<Long, String> consumerRecords =
				consumer.poll(1000);
		consumerRecords.forEach(record -> {
			try {
				
				tweetConverter.processAndConvertToTweet(record);
				tweets.postTweets(tweetConverter.getTopic(), tweetConverter.getDetails());
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
	}


}
