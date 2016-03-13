package utils;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4JUtil {

	private Twitter twitter = null;

	/**
	 * Twitterクライアントを初期化する
	 * @throws TwitterException
	 */
	public void initialize() throws TwitterException {

		if (twitter != null)
			return;

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("prVAVyBbaD21xdcksZ1GEeQoK")
				.setOAuthConsumerSecret("MqeNrPV8frhCZvNGNpqQeHc6q41qeKYzWx8NvY4KTlQFTh4frN")
				.setOAuthAccessToken("708816462694129665-P66vwuzhiOwGMUGbfDT1ubODjT9JiTw")
				.setOAuthAccessTokenSecret("AHK0qt6ANqBb3ACrxeHPIBSdiS7la2CyTHDABoCiFvYM7");

		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	/**
	 * メッセージをポストする
	 * 実行前に initialize()の実行が必要
	 * @param msg postしたいメッセージ
	 * @return 成功時はtrue　失敗時はfalse
	 */
	public boolean postTweet(String msg) {

		if (twitter == null) {
			System.out.println("You need to call initialize() before posting tweet.");
			return false;
		}
		try {
			Status status = twitter.updateStatus(msg);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");

		} catch (TwitterException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}