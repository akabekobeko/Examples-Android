package me.akabeko.hostwebview;

import android.os.Bundle;
import android.app.Activity;

/**
 * WebView ��� Google Maps API �A�g���e�X�g�����ʂł��B
 */
public class GoogleMapsActivity extends Activity {
    /**
     * ��ʂ��쐬����鎞�ɔ������܂��B
     *
     * @param savedInstanceState �ۑ����ꂽ�C���X�^���X�̏�ԁB
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_google_maps );
    }
}
