package me.akabeko.usefontawesome;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * �A�C�R���Ɗe��R���g���[���̑g�ݍ��킹��\�������ʂł��B 
 */
public class IconWithControlsActivity extends Activity {
    /**
     * ��ʂ���������鎞�ɔ������܂��B
     *
     *  @param savedInstanceState �ۑ����ꂽ�C���X�^���X�̏�ԁB
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_icon_with_controls );

        char     unicode = IconInfo.GRYPH_UNICODE_BEGIN;
        Typeface typeface = FontUtility.getTypefaceFromRaw( this, R.raw.fontawesome );

        TextView view = ( TextView )this.findViewById( R.id.iconBbutton );
        view.setTypeface( typeface );
        view.setText( String.valueOf( unicode ) );

        view = ( TextView )this.findViewById( R.id.iconCheckBox );
        view.setTypeface( typeface );
        view.setText( String.valueOf( ++unicode ) );

        view = ( TextView )this.findViewById( R.id.iconCheckedTextView );
        view.setTypeface( typeface );
        view.setText( String.valueOf( ++unicode ) );

        view = ( TextView )this.findViewById( R.id.iconRadioButton );
        view.setTypeface( typeface );
        view.setText( String.valueOf( ++unicode ) );

        ToggleButton toggleButton = ( ToggleButton )this.findViewById( R.id.iconToggleButton );
        toggleButton.setTypeface( typeface );
        toggleButton.setText( String.valueOf( ++unicode ) );
        toggleButton.setTextOff( String.valueOf( unicode ) );
        toggleButton.setTextOn( String.valueOf( ++unicode ) );
    }
}
