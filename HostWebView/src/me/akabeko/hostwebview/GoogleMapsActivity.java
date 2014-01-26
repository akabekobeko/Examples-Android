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

/**
 * WebView 上の Google Maps API 連携をテストする画面です。
 */
public class GoogleMapsActivity extends Activity {
    /** ローカル HTML を表示する WebView。 */
    private WebView mWebView;

    /**
     * 指定された URL が WebView からのコールバックであることを調べ、対応する処理を実行します。
     *
     * @param url URL。
     *
     * @return コールバックだった場合は true。
     */
    private boolean checkCallbackUrl( String url ) {
        final String CallbacScheme = "app-callback://map?";
        if( !url.startsWith( CallbacScheme ) ) { return false; }

        return true;
    }

    /**
     * WebView 上に読み込まれた JavaScript の関数を実行します。
     *
     * @param param JavaScript の関数へ指定するパラメータ。
     */
    private void executeJavaScriptFunction( String param ) {
    }

    /**
     * WebView を初期化します。
     */
    @SuppressLint( "SetJavaScriptEnabled" )
    private void initWebView() {
        this.mWebView = ( WebView )this.findViewById( R.id.webViewGoogleMapsApi );
        this.mWebView.getSettings().setJavaScriptEnabled( true );
        this.mWebView.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading( WebView view, String url ) {
                return checkCallbackUrl( url );
            }
        } );

        this.mWebView.loadUrl( "file:///android_asset/html/map.html" );
    }

    /**
     * WebView へ送信するテキストを入力するための EditText を初期化します。
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
     * 画面が作成される時に発生します。
     *
     * @param savedInstanceState 保存されたインスタンスの状態。
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_google_maps );
        this.initWebView();
        this.initEditText();
    }
}
