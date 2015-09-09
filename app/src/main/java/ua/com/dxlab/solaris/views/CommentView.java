package ua.com.dxlab.solaris.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ua.com.dxlab.solaris.R;
import ua.com.dxlab.solaris.models.CommentsModel;

/**
 * Created by Dima on 05.09.2015.
 */
public class CommentView extends RelativeLayout {
    private TextView mTextViewCommentsData;


    public CommentView(Context _context) {
        super(_context);

        inflate(_context, R.layout.comments_view, this);

        findViews();
    }
    private void findViews() {
        mTextViewCommentsData = (TextView) findViewById(R.id.txtView_CIV);
    }

    public void setCommentsModel(CommentsModel _commentsModel) {
        mTextViewCommentsData.setText("ID: " + _commentsModel.attachment_id + '\n' +
                "Author: " + _commentsModel.author + '\n' +
                "Creation time: " + _commentsModel.creation_time + '\n' +'\n'+
                "Text: " +_commentsModel.text + '\n' +
                "Time: " + _commentsModel.time +'\n'+
                "Tags:" + _commentsModel.tags+'\n'+
                "Bug ID:" + _commentsModel.bug_id);
    }
}
