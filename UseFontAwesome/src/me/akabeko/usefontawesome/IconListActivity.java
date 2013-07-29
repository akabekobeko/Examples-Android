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
 * アイコン一覧を表示する画面です。
 */
public class IconListActivity extends Activity {
    /**
     * フォントをres/raw から読み込むことを示す、真偽値のパラメータ識別子。
     */
    public static final String INTENT_EXTRA_LOAD_FONT_FROM_RAW = "LoadFontFromRaw";

    /**
     * フォント情報を読み込みます。
     * 
     * @return フォント情報。
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
     * 画面が生成される時に発生します。
     *
     *  @param savedInstanceState 保存されたインスタンスの状態。
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_icon_list );

        // アイコン情報コレクション生成 ( 文字コード欠番はそのまま含める )
        List< IconInfo > iconInfos = new ArrayList< IconInfo >();
        for( char unicode = IconInfo.GRYPH_UNICODE_BEGIN; unicode <= IconInfo.GRYPH_UNICODE_END; ++unicode ) {
            iconInfos.add( new IconInfo( unicode ) );
        }

        // アダプタとフォントを関連付ける
        ListView listView = ( ListView )this.findViewById( R.id.iconListView );
        listView.setAdapter( new IconListItemAdapter( this, iconInfos, this.loadTypeface() ) );
    }
}
