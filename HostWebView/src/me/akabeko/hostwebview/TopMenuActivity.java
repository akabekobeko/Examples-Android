package me.akabeko.hostwebview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * メニュー画面です。
 */
public class TopMenuActivity extends Activity {
    /**
     * 画面が作成される時に発生します。
     *
     * @param savedInstanceState 保存されたインスタンスの状態。
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_top_menu );

        ArrayAdapter< String > adapter = new ArrayAdapter< String >( this, android.R.layout.simple_list_item_1 );
        adapter.add( this.getString( R.string.top_menu_basic_coordination ) );
        adapter.add( this.getString( R.string.top_menu_google_maps_api    ) );

        ListView listView = ( ListView )this.findViewById( R.id.topMenuListView );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView< ? > parent, View view, int position, long id ) {
                switch( position ) {
                case 0: moveActivity( BasicCoordinationActivity.class ); break;
                case 1: moveActivity( GoogleMapsActivity.class        ); break;
                }
            }
        } );
    }

    /**
     * 指定された画面に遷移します。
     *
     * @param cls 移動先となる Activity のクラス情報。
     */
    private void moveActivity( Class< ? > cls ) {
        Intent intent = new Intent( TopMenuActivity.this,cls );
        this.startActivity( intent );
    }
}
