package com.example.moviedb.helper;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    LinearLayoutManager layoutManager;

    /**
     * Supporting only LinearLayoutManager for now.
     *
     * @param layoutManager
     */
    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }

    }


   /* @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int[] firstVisibleItemPositions = layoutManager.fi;
        if (!isLoading() && !isLastPage()) {
            if ((firstVisibleItemPositions[0] + visibleItemCount) >= totalItemCount
                    && firstVisibleItemPositions[0] >= 0
                    && totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }
    }*/

        /*@Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mGridLayoutManager.getChildCount();
            int totalItemCount = mGridLayoutManager.getItemCount();
            int[] firstVisibleItemPositions = mGridLayoutManager.findFirstVisibleItemPositions(null);
            if (!mIsLoading && !mIsLastPage) {
                if ((firstVisibleItemPositions[0] + visibleItemCount) >= totalItemCount
                        && firstVisibleItemPositions[0] >= 0
                        && totalItemCount >= Config.PAGE_SIZE) {
                    loadMorePosts();
                }
            }
        }
    });*/

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
