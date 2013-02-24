package me.akabeko.testimagescroll.model;

import android.app.Application;

/**
 * アプリケーションを表します。
 */
public class App extends Application {
	/**
	 * ログ出力用のタグ名。
	 */
	public static final String TAG = "TestImageScroll";

	/**
	 * 画像情報を管理するためのオブジェクト インスタンス。
	 */
	private PictureManager mPictureManager = new PictureManager();

	/**
	 * 画像情報を取得します。
	 *
	 * @return 画像情報。
	 */
	public PictureManager getPicture() {
		return this.mPictureManager;
	}
}
