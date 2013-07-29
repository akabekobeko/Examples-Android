package me.akabeko.usefontawesome;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

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

        ArrayAdapter< String > adapter = new ArrayAdapter< String >( this, android.R.layout.simple_list_item_1 );
        adapter.add( "Icon list from assets"  );
        adapter.add( "Icon list from res/raw" );

        ListView listView = ( ListView )this.findViewById( R.id.menuListView );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id ) {
                switch( position ) {
                case 0:
                    showIconListActivity( false );
                    break;

                case 1:
                    showIconListActivity( true );
                    break;
                }
            }
        } );
    }

    /**
     * アイコン一覧画面を表示します。
     * 
     * @param isLoadFontFromRaw アイコンを res/raw から読み込む場合は true。assets の場合は false。
     */
    private void showIconListActivity( boolean isLoadFontFromRaw ) {
        Intent intent = new Intent( TopMenuActivity.this, IconListActivity.class );
        intent.putExtra( IconListActivity.INTENT_EXTRA_LOAD_FONT_FROM_RAW, isLoadFontFromRaw );
        this.startActivity( intent );
    }
}
