package me.akabeko.usefontawesome;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * アイコンと各種コントロールの組み合わせを表示する画面です。 
 */
public class IconWithControlsActivity extends Activity {
    /**
     * 画面が生成される時に発生します。
     *
     *  @param savedInstanceState 保存されたインスタンスの状態。
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
