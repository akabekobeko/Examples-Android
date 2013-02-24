package me.akabeko.testimagescroll.model;

import me.akabeko.testimagescroll.R;

/**
 * 画像を管理します。
 */
public class PictureManager {
	/**
	 * 画像の表示モードが CENTER であることを示すテキスト。
	 */
	public static final String DISPLAY_MODE_CENTER = "Center";

	/**
	 * 画像の表示モードが FIT_CENTER であることを示すテキスト。
	 */
	public static final String DISPLAY_MODE_FIT_CENTER = "Fit Center";

	/**
	 * 画像のリソース識別子コレクション。
	 */
	private int[] mPictures = {
		R.drawable.picture_01,
		R.drawable.picture_02,
		R.drawable.picture_03
	};

	/**
	 * 選択されている画像のインデックス。
	 */
	private int mIndex;

	/**
	 * 選択されている画像のリソース識別子を取得します。
	 *
	 * @return リソース識別子。
	 */
	public int getPictureId() {
		return this.mPictures[ this.mIndex ];
	}

	/**
	 * 前の画像を選択します。
	 *
	 * @return 新しく選択された画像のリソース識別子。
	 */
	public int prev() {
		if( this.mIndex == 0 ) {
			this.mIndex = this.mPictures.length - 1;
		} else {
			--this.mIndex;
		}

		return this.getPictureId();
	}

	/**
	 * 次の画像を選択します。
	 *
	 * @return 新しく選択された画像のリソース識別子。
	 */
	public int next() {
		if( this.mIndex == this.mPictures.length - 1 ) {
			this.mIndex = 0;

		} else {
			++this.mIndex;
		}

		return this.getPictureId();
	}
}
