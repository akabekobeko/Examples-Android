package me.akabeko.hostwebview;

import android.os.Bundle;
import android.app.Activity;

/**
 * WebView 上の Google Maps API 連携をテストする画面です。
 */
public class GoogleMapsActivity extends Activity {
    /**
     * 画面が作成される時に発生します。
     *
     * @param savedInstanceState 保存されたインスタンスの状態。
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_google_maps );
    }
}
