package me.akabeko.usefontawesome;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * �A�C�R���ꗗ�ƃA�C�e�����֘A�t���܂��B
 */
public class IconListItemAdapter extends ArrayAdapter< IconInfo > {
    /**
     * ���C�A�E�g����p�I�u�W�F�N�g�B
     */
    private LayoutInflater mLayoutInflater;

    /**
     * �A�C�R���\���Ɏg�p����t�H���g���B
     */
    private Typeface mIconTypeface;

    /**
     * �C���X�^���X�����������܂��B
     *
     * @param context      �R���e�L�X�g�B
     * @param objects      �A�C�R�����R���N�V�����B
     * @param iconTypeface �A�C�R���\���Ɏg�p����t�H���g���B
     */
    public IconListItemAdapter( Context context, List< IconInfo > objects, Typeface iconTypeface ) {
        super( context, 0, objects );

        this.mLayoutInflater = ( LayoutInflater )context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        this.mIconTypeface   = iconTypeface;
    }

    /**
     * �A�C�e���� View ���擾���܂��B
     *
     * @param position    �A�C�e���̈ʒu�B
     * @param convertView ����� View�B
     * @param parent      �e�ƂȂ� ViewGroup�B
     *
     * @return �A�C�e���� View�B
     */
    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        ViewHolder holder = null;
        if( convertView == null ) {
            convertView = this.mLayoutInflater.inflate( R.layout.item_icon_list, null );

            // �R���g���[���̎Q�Ƃ��L���b�V�����A�ȍ~�̌������ȗ�����
            holder = new ViewHolder();
            holder.GryphTextView   = ( TextView )convertView.findViewById( R.id.gryphTextView );
            holder.UnicodeTextView = ( TextView )convertView.findViewById( R.id.unicodeTextView );
            holder.GryphTextView.setTypeface( this.mIconTypeface );
            convertView.setTag( holder );

        } else {
            holder = ( ViewHolder )convertView.getTag();
        }

        IconInfo info = this.getItem( position );
        holder.GryphTextView.setText( info.getGlyph() );
        holder.UnicodeTextView.setText( String.format( "%04X", info.getUnicode() ) );

        return convertView;
    }

    /**
     * �A�C�e���� View ���ɔz�u���ꂽ�R���g���[���̎Q�Ƃ��L���b�V�����܂��B
     */
    private class ViewHolder {
        /**
         * �A�C�R��������\������ TextView�B
         */
        TextView GryphTextView;

        /**
         * �A�C�R�������� UNICODE ��\������ TextView�B
         */
        TextView UnicodeTextView;
    }
}
