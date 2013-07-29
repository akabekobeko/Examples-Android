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
 * �t�H���g�Ɋւ��郆�[�e�B���e�B�ł��B 
 */
public final class FontUtility {
    /**
     * �t�H���g�� assets ����ǂݍ��݂܂��B
     *
     * @param context �R���e�L�X�g�B
     * @param path    �t�H���g �t�@�C�������� assets �t�H���_����̑��΃p�X�B
     *
     * @return �������̓t�H���g���C���X�^���X�B����ȊO�� null�B
     */
    public static Typeface getTypefaceFromAssets( Context context, String path ) {
        return Typeface.createFromAsset( context.getAssets(), path );
    }

    /**
     * �t�H���g�� res/raw ����ǂݍ��݂܂��B
     * ������ Stack Overflow �ɓ��e���ꂽ�ȉ��̋L�����Q�l�ɂ��Ă��܂��B
     *
     * Font in Android Library - Stack Overflow
     * http://stackoverflow.com/questions/7610355/font-in-android-library
     *
     * @param context    �R���e�L�X�g�B
     * @param fileName ���\�[�X���ʎq�BR.raw �ȉ��ɒ�`���ꂽ���̂��w�肵�܂��B
     *
     * @return �������̓t�H���g���C���X�^���X�B����ȊO�� null�B
     */
    public static Typeface getTypefaceFromRaw( Context context, int resourceId ) {
        InputStream          inputStream  = null;
        BufferedOutputStream outputStream = null;
        Typeface             typeface     = null;
        try {
            // res/raw �̃t�H���g���������ɓǂݍ���
            inputStream  = context.getResources().openRawResource( resourceId );

            // �t�H���g���ꎞ�t�@�C���Ƃ��ďo��
            String fontFilePath = context.getCacheDir() + "/tmp" + System.currentTimeMillis() + ".raw";
            outputStream = new BufferedOutputStream( new FileOutputStream( fontFilePath ) );

            byte[] buffer = new byte[ inputStream.available() ];
            int    length = 0;
            while( ( length = inputStream.read( buffer ) ) > 0 ) {
                outputStream.write( buffer, 0, length );
            }

            // �t�H���g�Ƃ��ēǂݍ��񂾂�A�ꎞ�t�@�C��������
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
     * �j�����ׂ����\�[�X�����I�u�W�F�N�g���J�����܂��B
     *
     * @param obj �I�u�W�F�N�g�B
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
