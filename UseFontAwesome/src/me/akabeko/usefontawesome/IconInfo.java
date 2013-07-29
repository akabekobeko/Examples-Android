package me.akabeko.usefontawesome;

/**
 * Font Awesome �̃A�C�R������\���܂��B
 */
public class IconInfo {
    /**
     * Font Awesome �ɂ�����A�C�R������ UNICODE �̎n�_�B
     */
    public static final char GRYPH_UNICODE_BEGIN  = 0xF000;

    /**
     * Font Awesome �ɂ�����A�C�R������ UNICODE �̏I�_�B
     */
    public static final char GRYPH_UNICODE_END  = 0xF18B;

    /**
     * Font Awesome �̃A�C�R���ɑΉ����镶���B
     */
    private String mGlyph;

    /**
     * Font Awesome �̃A�C�R���ɑΉ����镶���� UNICODE�B
     */
    private int mUnicode;
    
    /**
     * �C���X�^���X�����������܂��B
     * 
     * @param unicode Font Awesome �̃A�C�R���ɑΉ����镶���� UNICODE�B
     */
    public IconInfo( char unicode ) {
        this.mGlyph   = String.valueOf( unicode );
        this.mUnicode = ( int )unicode;
    }

    /**
     * Font Awesome �̃A�C�R���ɑΉ����镶�����擾���܂��B
     * 
     * @return �����B
     */
    public String getGlyph() { return this.mGlyph; }

    /**
     * Font Awesome �̃A�C�R���ɑΉ����镶���� UNICODE ���擾���܂��B
     * 
     * @return UNICODE�B
     */
    public int getUnicode() { return this.mUnicode; }
}
