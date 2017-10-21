package com.citytogo.jonnyhsia.rxevangelist.page.timeline;

import com.citytogo.jonnyhsia.rxevangelist.model.StoryService;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;

import java.util.List;

/**
 * Created by JonnyHsia on 17/10/21.
 */
public class TimelinePresenter implements TimelineContract.Presenter {

    private TimelineContract.View mView;
    private StoryService mStoryModel;
    private String mUsername;
    private int offset = 1;

    public TimelinePresenter(TimelineContract.View view, StoryService storyModel, String username) {
        mView = view;
        mView.bindPresenter(this);
        mStoryModel = storyModel;
        mUsername = username;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void requestTimeline() {
        int limit = 20;
        mStoryModel.getTimeline(mUsername, offset, limit, new StoryService.OnTimelineRequestListener() {
            @Override
            public void onSuccess(List<Story> data) {
                // 判断数据是否为 null 或 empty
                if (data == null || data.isEmpty()) {
                    mView.loadEmpty();
                } else {
                    mView.loadSuccess(data);
                }
            }

            @Override
            public void onError(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void dispose() {
        mStoryModel.dispose();
    }
}
