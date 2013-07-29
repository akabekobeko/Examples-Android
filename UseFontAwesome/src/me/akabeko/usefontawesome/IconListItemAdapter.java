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
 * アイコン一覧とアイテムを関連付けます。
 */
public class IconListItemAdapter extends ArrayAdapter< IconInfo > {
    /**
     * レイアウト操作用オブジェクト。
     */
    private LayoutInflater mLayoutInflater;

    /**
     * アイコン表示に使用するフォント情報。
     */
    private Typeface mIconTypeface;

    /**
     * インスタンスを初期化します。
     *
     * @param context      コンテキスト。
     * @param objects      アイコン情報コレクション。
     * @param iconTypeface アイコン表示に使用するフォント情報。
     */
    public IconListItemAdapter( Context context, List< IconInfo > objects, Typeface iconTypeface ) {
        super( context, 0, objects );

        this.mLayoutInflater = ( LayoutInflater )context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        this.mIconTypeface   = iconTypeface;
    }

    /**
     * アイテムの View を取得します。
     *
     * @param position    アイテムの位置。
     * @param convertView 既定の View。
     * @param parent      親となる ViewGroup。
     *
     * @return アイテムの View。
     */
    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        ViewHolder holder = null;
        if( convertView == null ) {
            convertView = this.mLayoutInflater.inflate( R.layout.item_icon_list, null );

            // コントロールの参照をキャッシュし、以降の検索を省略する
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
     * アイテムの View 内に配置されたコントロールの参照をキャッシュします。
     */
    private class ViewHolder {
        /**
         * アイコン文字を表示する TextView。
         */
        TextView GryphTextView;

        /**
         * アイコン文字の UNICODE を表示する TextView。
         */
        TextView UnicodeTextView;
    }
}
