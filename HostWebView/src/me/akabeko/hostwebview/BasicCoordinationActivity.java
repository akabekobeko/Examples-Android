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
    /** ���[�J�� HTML ��\������ WebView�B */
    private WebView mWebView;

    /**
     * �w�肳�ꂽ URL �� WebView ����̃R�[���o�b�N�ł��邱�Ƃ𒲂ׁA�Ή����鏈�������s���܂��B
     *
     * @param url URL�B
     *
     * @return �R�[���o�b�N�������ꍇ�� true�B
     */
    private boolean checkCallbackUrl( String url ) {
        final String CallbacScheme = "app-callback://";
        if( !url.startsWith( CallbacScheme ) ) { return false; }

        String message = url.substring( CallbacScheme.length() );
        new AlertDialog.Builder( BasicCoordinationActivity.this )
            .setTitle( R.string.text_from_webview )
            .setMessage( message )
            .setPositiveButton( "OK", null )
            .show();

        return true;
    }

    /**
     * WebView ��ɓǂݍ��܂ꂽ JavaScript �̊֐������s���܂��B
     *
     * @param param JavaScript �̊֐��֎w�肷��p�����[�^�B
     */
    private void executeJavaScriptFunction( String param ) {
        final String script = "javascript:window.webViewCallback('%s');";
        this.mWebView.loadUrl( String.format( script, param ) );
    }

    /**
     * WebView �����������܂��B
     */
    @SuppressLint( "SetJavaScriptEnabled" )
    private void initWebView() {
        this.mWebView = ( WebView )this.findViewById( R.id.webViewBasicCoordination );
        this.mWebView.getSettings().setJavaScriptEnabled( true );
        this.mWebView.setWebViewClient( new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading( WebView view, String url ) {
                return checkCallbackUrl( url );
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
                    imm.hideSoftInputFromWindow( v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY );
                    imm.hideSoftInputFromWindow( v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
                    executeJavaScriptFunction( v.getText().toString() );
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
