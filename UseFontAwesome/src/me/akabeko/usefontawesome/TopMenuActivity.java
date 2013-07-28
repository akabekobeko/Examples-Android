package me.akabeko.usefontawesome;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * メニュー画面です。
 */
public class TopMenuActivity extends Activity {
    /**
     * 画面が生成される時に発生します。
     * 
     *  @param savedInstanceState 保存されたインスタンスの状態。
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_top_menu );
    }
}
