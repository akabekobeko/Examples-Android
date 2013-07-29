package me.akabeko.usefontawesome;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.util.Log;

/**
 * フォントに関するユーティリティです。 
 */
public final class FontUtility {
    /**
     * フォントを assets から読み込みます。
     *
     * @param context コンテキスト。
     * @param path    フォント ファイルを示す assets フォルダからの相対パス。
     *
     * @return 成功時はフォント情報インスタンス。それ以外は null。
     */
    public static Typeface getTypefaceFromAssets( Context context, String path ) {
        return Typeface.createFromAsset( context.getAssets(), path );
    }

    /**
     * フォントを res/raw から読み込みます。
     * 実装は Stack Overflow に投稿された以下の記事を参考にしています。
     *
     * Font in Android Library - Stack Overflow
     * http://stackoverflow.com/questions/7610355/font-in-android-library
     *
     * @param context    コンテキスト。
     * @param fileName リソース識別子。R.raw 以下に定義されたものを指定します。
     *
     * @return 成功時はフォント情報インスタンス。それ以外は null。
     */
    public static Typeface getTypefaceFromRaw( Context context, int resourceId ) {
        InputStream          inputStream  = null;
        BufferedOutputStream outputStream = null;
        Typeface             typeface     = null;
        try {
            // res/raw のフォントをメモリに読み込む
            inputStream  = context.getResources().openRawResource( resourceId );

            // フォントを一時ファイルとして出力
            String fontFilePath = context.getCacheDir() + "/tmp" + System.currentTimeMillis() + ".raw";
            outputStream = new BufferedOutputStream( new FileOutputStream( fontFilePath ) );

            byte[] buffer = new byte[ inputStream.available() ];
            int    length = 0;
            while( ( length = inputStream.read( buffer ) ) > 0 ) {
                outputStream.write( buffer, 0, length );
            }

            // フォントとして読み込んだら、一時ファイルを消す
            typeface = Typeface.createFromFile( fontFilePath );
            new File( fontFilePath ).delete();

        } catch( NotFoundException e ) {
            Log.e( "UseFontAwesome", "Could not find font in resources!", e );

        } catch( IOException e ) {
            Log.e( "UseFontAwesome", "Error reading in font!" );

        } finally {
            tryClose( inputStream  );
            tryClose( outputStream );
        }

        return typeface;
    }

    /**
     * 破棄すべきリソースを持つオブジェクトを開放します。
     *
     * @param obj オブジェクト。
     */
    private static void tryClose( Closeable obj ) {
        if( obj != null ) {
            try {
                obj.close();

            } catch( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}
