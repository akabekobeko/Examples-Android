package me.akabeko.hostwebview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;

/**
 * WebView �̊�{�I�ȘA�g���e�X�g�����ʂł��B
 */
public class BasicCoordinationActivity extends Activity {
    /** WebView ��� JavaScript ����R�[���o�b�N����鎞�� URL �ɂ�����X�L�[���B */
    private static final String WEBVIEW_CALLBACK_SCHEME = "app-callback://";

    /** ���[�J�� HTML ��\������ WebView�B */
    private WebView mWebView;

    /**
     * WebView �����������܂��B
     */
    @SuppressLint( "SetJavaScriptEnabled" )
    private void initWebView() {
        this.mWebView = ( WebView )this.findViewById( R.id.webViewBasicCoordination );
        this.mWebView.getSettings().setJavaScriptEnabled( true );
        this.mWebView.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading( WebView view, String url ) {
                if( url.startsWith( WEBVIEW_CALLBACK_SCHEME ) ) {
                    String message = url.substring( WEBVIEW_CALLBACK_SCHEME.length() );
                    new AlertDialog.Builder( BasicCoordinationActivity.this )
                        .setTitle( R.string.text_from_webview )
                        .setMessage( message )
                        .setPositiveButton( "OK", null )
                        .show();

                    return true;
                }

                return false;
            }
        } );

        this.mWebView.loadUrl( "file:///android_asset/html/basic.html" );
    }

    /**
     * WebView �֑��M����e�L�X�g����͂��邽�߂� EditText �����������܂��B
     */
    private void initEditText() {
        EditText editText = ( EditText )this.findViewById( R.id.editTextSendWebView );
        editText.setOnEditorActionListener( new OnEditorActionListener() {
            @Override
            public boolean onEditorAction( TextView v, int actionId, KeyEvent event ) {
                if( event.getKeyCode() == KeyEvent.KEYCODE_ENTER ) {
                    InputMethodManager imm = ( InputMethodManager )getSystemService( INPUT_METHOD_SERVICE );
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY );
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
                }

                return false;
            }
        } );
    }

    /**
     * ��ʂ��쐬����鎞�ɔ������܂��B
     *
     * @param savedInstanceState �ۑ����ꂽ�C���X�^���X�̏�ԁB
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_basic_coordination );
        this.initWebView();
        this.initEditText();
    }
}
