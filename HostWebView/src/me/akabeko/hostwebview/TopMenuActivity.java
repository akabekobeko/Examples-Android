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
 * ���j���[��ʂł��B
 */
public class TopMenuActivity extends Activity {
    /**
     * ��ʂ��쐬����鎞�ɔ������܂��B
     *
     * @param savedInstanceState �ۑ����ꂽ�C���X�^���X�̏�ԁB
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
     * �w�肳�ꂽ��ʂɑJ�ڂ��܂��B
     *
     * @param cls �ړ���ƂȂ� Activity �̃N���X���B
     */
    private void moveActivity( Class< ? > cls ) {
        Intent intent = new Intent( TopMenuActivity.this,cls );
        this.startActivity( intent );
    }
}
