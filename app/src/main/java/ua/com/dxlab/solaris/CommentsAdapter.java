package ua.com.dxlab.solaris;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import ua.com.dxlab.solaris.models.CommentsModel;
import ua.com.dxlab.solaris.views.CommentView;

/**
 * Created by Dima on 05.09.2015.
 */
public class CommentsAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<CommentsModel> mCommentsModel;


    public CommentsAdapter(Context _context, ArrayList<CommentsModel> _commentsModel) {
        this.mContext   = _context;
        this.mLayoutInflater        = LayoutInflater.from(mContext);
        this.mCommentsModel = _commentsModel;
    }

    @Override
    public int getCount() {
        return mCommentsModel.size();
    }

    @Override
    public CommentsModel getItem(int position) {
        return mCommentsModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentsModel commentsModel = getItem(position);

        if (convertView == null){
            convertView = new CommentView(mContext);
        }

        ((CommentView) convertView).setCommentsModel(commentsModel);

        return convertView;
    }
}
