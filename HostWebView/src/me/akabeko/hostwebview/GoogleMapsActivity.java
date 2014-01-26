package me.akabeko.hostwebview;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
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

    /** 住所を表示するための TextView。 */
    private TextView mAddressTextView;

    /**
     * 指定された URL が WebView からのコールバックであることを調べ、対応する処理を実行します。
     *
     * @param url URL。
     *
     * @return コールバックだった場合は true。
     */
    private boolean checkCallbackUrl( String url ) {
        final String CallbacScheme = "app-callback://map";
        if( !url.startsWith( CallbacScheme ) ) { return false; }

        Map< String, String > params = this.parseUrlParameters( url );
        String address   = params.get( "address" );
        String latitude  = params.get( "lat" );
        String longitude = params.get( "lng" );
        Log.d( "HostWebView", String.format( "address = %s, latitude = %s, longitude = %s", address, latitude, longitude ) );

        if( address != null ) {
            this.mAddressTextView.setText( address );
        }

        return true;
    }

    /**
     * マップの中央位置を指定された住所の位置へ移動させます。
     *
     * @param address 住所。ジオコーディングによって検索されます。
     */
    private void moveToMapCenter( String address ) {
        final String region = this.getString( R.string.google_map_rgion );
        final String script = "javascript:window.webViewCallbackSearchAddress('%s','%s');";
        this.mWebView.loadUrl( String.format( script, address, region ) );
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
                if( actionId == EditorInfo.IME_ACTION_DONE ) {
                    InputMethodManager imm = ( InputMethodManager )getSystemService( INPUT_METHOD_SERVICE );
                    imm.hideSoftInputFromWindow( v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY );
                    imm.hideSoftInputFromWindow( v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
                    moveToMapCenter( v.getText().toString() );
                }

                return false;
            }
        } );
    }

    /**
     * URL におけるパラメータ部分を解析してディクショナリ化します。
     *
     * @param url URL。
     *
     * @return 解析結果。
     */
    private Map< String, String > parseUrlParameters( String url ) {
        Map< String, String > result = new HashMap< String, String >();
        int                   index = url.indexOf( "?" );
        if( index == -1 ) { return result; }

        String[] params = url.substring( index + 1 ).split( "&" );
        for( String param : params ) {
            String[] keyValuePair = param.split( "=" );
            if( keyValuePair.length >= 2 ) {
                try {
                    String value = URLDecoder.decode( keyValuePair[ 1 ], "utf-8" );
                    result.put( keyValuePair[ 0 ], value );

                } catch( UnsupportedEncodingException e ) {
                    e.printStackTrace();
                }
            }
        }

        return result;
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
        this.mAddressTextView = ( TextView )this.findViewById( R.id.textViewAddress );
        this.initWebView();
        this.initEditText();
    }
}
