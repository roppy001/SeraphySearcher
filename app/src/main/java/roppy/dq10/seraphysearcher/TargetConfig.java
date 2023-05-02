package roppy.dq10.seraphysearcher;

public class TargetConfig {
	public final static TargetConfig[] TARGET_CONFIG_ARRAY = {
			new TargetConfig(
					"ASUB",
					"アスバル",
					"アスバルが出現しました",
					2L * 60L * 60L * 1000L,
					1L * 60L * 60L * 1000L,
					"#野生のアスバル OR #流浪のアスバル OR 野生のアスバル OR 流浪のアスバル exclude:retweets"),
			new TargetConfig("AKUR",
					"アクロバット",
					"アクロバットが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール アクロバット exclude:retweets"),
			new TargetConfig("SUIK",
					"スイカさま",
					"スイカさまが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール スイカさま OR スイカ exclude:retweets"),
			new TargetConfig("ONIB",
					"おにび",
					"おにびが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール おにび exclude:retweets"),
			new TargetConfig("INEK",
					"いねかりぞく",
					"いねかりぞくが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール いねかりぞく OR いねかり exclude:retweets"),
			new TargetConfig("HARA",
					"ハラッヘリン",
					"ハラッヘリンが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール ハラッヘリン exclude:retweets"),
			new TargetConfig("PENC",
					"ペンシルボーイ",
					"ペンシルボーイが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール ペンシルボーイ exclude:retweets"),
			new TargetConfig("MANP",
					"まんぷく丸",
					"まんぷく丸が出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール まんぷく丸 exclude:retweets"),
			new TargetConfig("CREA",
					"クリームつむり",
					"クリームつむりが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール クリームつむり OR クリーム OR つむり exclude:retweets"),
			new TargetConfig("RAIN",
					"レインコート",
					"レインコートが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール レインコート OR レイン exclude:retweets"),
			new TargetConfig("HAKU",
					"はくばのおうじ",
					"はくばのおうじが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール はくばのおうじ OR はくば OR おうじ exclude:retweets"),
			new TargetConfig("PRIN",
					"プリンセスニャン",
					"プリンセスニャンが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール プリンセスニャン OR プリンセス OR ニャン OR にゃんこ exclude:retweets"),
			new TargetConfig("HANI",
					"はにわにんぎょう",
					"はにわにんぎょうが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール はにわにんぎょう OR はにわ OR にんぎょう exclude:retweets"),
			new TargetConfig("TYAB",
					"ちゃばしらこぞう",
					"ちゃばしらこぞうが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール ちゃばしらこぞう OR ちゃばしら exclude:retweets"),
			new TargetConfig("HITC",
					"ヒッチハイクデビル",
					"ヒッチハイクデビルが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール ヒッチハイクデビル OR ヒッチハイク OR デビル exclude:retweets"),
			new TargetConfig("CURR",
					"カレーサタン",
					"カレーサタンが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール カレーサタン OR サタン OR カレー exclude:retweets"),
			new TargetConfig("KINA",
					"きなこづち",
					"きなこづちが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール きなこづち OR きなこ exclude:retweets"),
			new TargetConfig("OSUM",
					"おすもっこり",
					"おすもっこりが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール おすもっこり OR もっこり OR おすも exclude:retweets"),
			new TargetConfig("HOTA",
					"ホタルビー",
					"ホタルビーが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール ホタルビー exclude:retweets"),
			new TargetConfig("CAME",
					"カメラこぞう",
					"カメラこぞうが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール カメラこぞう exclude:retweets"),
			new TargetConfig("SOUJ",
					"おそうじ童子",
					"おそうじ童子が出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール おそうじ exclude:retweets"),
			new TargetConfig("MOGU",
					"たたきモグラ",
					"たたきモグラが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール たたきモグラ OR モグラ OR もぐら"),
			new TargetConfig("NAUM",
					"ナウマンコック",
					"ナウマンコックが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール ナウマンコック OR ナウマン"),
			new TargetConfig("TENT",
					"サウルステントウ",
					"サウルステントウが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール サウルステントウ OR テントウ"),
			new TargetConfig("SHAB",
					"スラシャボン",
					"スラシャボンが出現しました",
					38L * 60L * 1000L,
					36L * 60L * 1000L,
					"#モンスターシール スラシャボン OR シャボン"),
		new TargetConfig(
				"SERA",
				"セラフィ",
				"セラフィが出現しました",
				2L * 60L * 60L * 1000L,
				1L * 60L * 60L * 1000L,
				"#野生のセラフィ OR #流浪のセラフィ OR 野生のセラフィ OR 流浪のセラフィ exclude:retweets"),
		new TargetConfig(
				"FOS",
				"フォステイル",
				"フォステイルが出現しました",
				2L * 60L * 60L * 1000L,
				1L * 60L * 60L * 1000L,
				"#野生のフォステイル OR #流浪のフォステイル OR 野生のフォステイル OR 流浪のフォステイル exclude:retweets"),
		new TargetConfig("GEN",
				 "幻想画",
				 "幻想画が出現しました",
				 3L * 60L * 60L * 1000L,
				 2L * 60L * 60L * 1000L,
				 "幻想画 exclude:retweets") };

	private String key;
	private String targetName;
	private String notificationText;
	private long intervalTime;
	private long expirationTime;
	private String query;

	public TargetConfig(String key, String targetName, String notificationText,
			long intervalTime, long expirationTime,String query) {
		this.key = key;
		this.targetName = targetName;
		this.notificationText = notificationText;
		this.intervalTime = intervalTime;
		this.expirationTime = expirationTime;
		this.query = query;
	}

	public String getKey() {
		return key;
	}

	public String getTargetName() {
		return targetName;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public long getIntervalTime() {
		return intervalTime;
	}

	public long getExpirationTime() {
		return expirationTime;
	}

	public String getQuery() {
		return query;
	}

}
