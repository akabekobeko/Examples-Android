package me.akabeko.hostwebview;

import android.os.Bundle;
import android.app.Activity;

/**
 * WebView �̊�{�I�ȘA�g���e�X�g�����ʂł��B
 */
public class BasicCoordinationActivity extends Activity {
    /**
     * ��ʂ��쐬����鎞�ɔ������܂��B
     *
     * @param savedInstanceState �ۑ����ꂽ�C���X�^���X�̏�ԁB
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_basic_coordination );
    }
}
