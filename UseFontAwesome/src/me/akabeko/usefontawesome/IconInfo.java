package me.akabeko.usefontawesome;

/**
 * Font Awesome のアイコン情報を表します。
 */
public class IconInfo {
    /**
     * Font Awesome におけるアイコン文字 UNICODE の始点。
     */
    public static final char GRYPH_UNICODE_BEGIN  = 0xF000;

    /**
     * Font Awesome におけるアイコン文字 UNICODE の終点。
     */
    public static final char GRYPH_UNICODE_END  = 0xF18B;

    /**
     * Font Awesome のアイコンに対応する文字。
     */
    private String mGlyph;

    /**
     * Font Awesome のアイコンに対応する文字の UNICODE。
     */
    private int mUnicode;
    
    /**
     * インスタンスを初期化します。
     * 
     * @param unicode Font Awesome のアイコンに対応する文字の UNICODE。
     */
    public IconInfo( char unicode ) {
        this.mGlyph   = String.valueOf( unicode );
        this.mUnicode = ( int )unicode;
    }

    /**
     * Font Awesome のアイコンに対応する文字を取得します。
     * 
     * @return 文字。
     */
    public String getGlyph() { return this.mGlyph; }

    /**
     * Font Awesome のアイコンに対応する文字の UNICODE を取得します。
     * 
     * @return UNICODE。
     */
    public int getUnicode() { return this.mUnicode; }
}
