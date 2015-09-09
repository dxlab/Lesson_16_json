package ua.com.dxlab.solaris;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.com.dxlab.solaris.models.BugsModel;
import ua.com.dxlab.solaris.models.CommentsModel;
import ua.com.dxlab.solaris.models.CommentsProperties;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment implements CommentsCallbackLoading {

    public static final String JSON_COMMENTS_URL = "https://bugzilla.mozilla.org/rest/bug/707428/comment";
    public static final String KEY_COMMENTS_NUMBER = "707428";
    public static final String KEY_BUGS = "bugs";
    public static final String KEY_COMMENTS = "comments";

    private ListView mCommentsList;
    private Activity mActivity;
    private CommentsAdapter mCommentsAdapter;
    private ArrayList<CommentsModel> mData = new ArrayList<CommentsModel>();

    public CommentsFragment() {
        // Required empty public constructor
        this.setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatenew) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_comments, container, false);
        mActivity = getActivity();
        findViews(v);

        CommentsProperties commentsProperties = new CommentsProperties(JSON_COMMENTS_URL, KEY_COMMENTS_NUMBER, KEY_BUGS, KEY_COMMENTS);

        //TODO would check whether is device connected!!!
        new CommentsAsyncJsonLoader(CommentsFragment.this, commentsProperties).execute();

        return v;
    }

    /**
     * inits current fragment UI
     */
    private void findViews(View v){
        mCommentsList    = (ListView) v.findViewById(R.id.listView_FC);
    }


    @Override
    public void onSuccess(BugsModel bugsModel) {
        //Log.d("onSuccess", "Success");

        int commentsCount = bugsModel.commentItems.size();

        for (int i = 0; i < commentsCount; i++) {
            mData = bugsModel.commentItems;
        }
        mCommentsAdapter = new CommentsAdapter(mActivity, mData);
        mCommentsList.setAdapter(mCommentsAdapter);
    }

    @Override
    public void onFailure(String errorMessage) {
        Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
