package me.akabeko.testimagescroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * トップ画面を表します。
 */
public class TopActivity extends Activity implements OnClickListener {
	/**
	 * 画面が作成された時に発生します。
	 *
	 * @param savedInstanceState 保存されたインスタンスの状態。
	 */
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		this.setContentView( R.layout.top );

		this.findViewById( R.id.mode_advance ).setOnClickListener( this );
		this.findViewById( R.id.mode_simple  ).setOnClickListener( this );
	}

	/**
	 * 画面を遷移させます。
	 *
	 * @param cls 遷移先となる Activity のクラス情報。
	 */
	private void moveActivity( Class< ? > cls ) {
		Intent intent = new Intent();
		intent.setClass( this, cls );
		this.startActivity( intent );
	}

	/**
	 * View がクリックされた時に発生します。
	 *
	 * @param v クリックされた View。
	 */
	public void onClick( View v ) {
		switch( v.getId() ) {
		case R.id.mode_advance:
			this.moveActivity( AdvanceImageScrollActivity.class );
			break;

		case R.id.mode_simple:
			this.moveActivity( SimpleImageScrollActivity.class );
			break;
		}
	}
}
