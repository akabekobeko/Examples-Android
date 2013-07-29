package me.akabeko.usefontawesome;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;

/**
 * �A�C�R���ꗗ��\�������ʂł��B
 */
public class IconListActivity extends Activity {
    /**
     * �t�H���g��res/raw ����ǂݍ��ނ��Ƃ������A�^�U�l�̃p�����[�^���ʎq�B
     */
    public static final String INTENT_EXTRA_LOAD_FONT_FROM_RAW = "LoadFontFromRaw";

    /**
     * �t�H���g����ǂݍ��݂܂��B
     * 
     * @return �t�H���g���B
     */
    private Typeface loadTypeface() {
        Intent intent = this.getIntent();
        if( intent.getBooleanExtra( INTENT_EXTRA_LOAD_FONT_FROM_RAW, false ) ) {
            Log.d( "UseFontAwesome", "Load font from res/raw" );
            return FontUtility.getTypefaceFromRaw( this, R.raw.fontawesome );
        }

        Log.d( "UseFontAwesome", "Load font from assets" );
        return FontUtility.getTypefaceFromAssets( this, "fontawesome-webfont.ttf" );
    }

    /**
     * ��ʂ���������鎞�ɔ������܂��B
     *
     *  @param savedInstanceState �ۑ����ꂽ�C���X�^���X�̏�ԁB
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_icon_list );

        // �A�C�R�����R���N�V�������� ( �����R�[�h���Ԃ͂��̂܂܊܂߂� )
        List< IconInfo > iconInfos = new ArrayList< IconInfo >();
        for( char unicode = IconInfo.GRYPH_UNICODE_BEGIN; unicode <= IconInfo.GRYPH_UNICODE_END; ++unicode ) {
            iconInfos.add( new IconInfo( unicode ) );
        }

        // �A�_�v�^�ƃt�H���g���֘A�t����
        ListView listView = ( ListView )this.findViewById( R.id.iconListView );
        listView.setAdapter( new IconListItemAdapter( this, iconInfos, this.loadTypeface() ) );
    }
}
