/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package ruijie.com.my12306.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ruijie.com.my12306.R;


public class LoadMoreRecyclerView extends RecyclerView {

    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;
    public final static int TYPE_FOOTER = 2;
    public final static int TYPE_LIST = 3;
    public final static int TYPE_STAGGER = 4;

    private boolean mIsFooterEnable = false;

    private AutoLoadAdapter mAutoLoadAdapter;

    private boolean mIsLoadingMore;

    private int mLoadMorePosition;

    private LoadMoreListener mListener;

    private View headView;
    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        super.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null != mListener && mIsFooterEnable && !mIsLoadingMore && dy > 0) {
                    int lastVisiblePosition = getLastVisiblePosition();
                    if (lastVisiblePosition + 1 == mAutoLoadAdapter.getItemCount()) {
                        setLoadingMore(true);
                        mLoadMorePosition = lastVisiblePosition;
                        mListener.onLoadMore();
                    }
                }
            }
        });
    }

    public void setLoadMoreListener(LoadMoreListener listener) {
        mListener = listener;
    }

    public void setLoadingMore(boolean loadingMore) {
        this.mIsLoadingMore = loadingMore;
    }


    public interface LoadMoreListener {
        void onLoadMore();
    }


    public class AutoLoadAdapter extends Adapter<ViewHolder> {

        private Adapter mInternalAdapter;

        private boolean mIsHeaderEnable;
        private View mHeaderVeiw=null;
        private int mFooterResId;
        public AutoLoadAdapter(Adapter adapter) {
            mInternalAdapter = adapter;
            mIsHeaderEnable = false;
        }

        @Override
        public int getItemViewType(int position) {
            int headerPosition = 0;
            int footerPosition = getItemCount() - 1;

            if (headerPosition == position&& (mHeaderVeiw!=null||headView!=null)) {
                return TYPE_HEADER;
            }
            if (footerPosition == position && mIsFooterEnable) {
                return TYPE_FOOTER;
            }
            return TYPE_NORMAL;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                return new HeaderViewHolder(mHeaderVeiw!=null?mHeaderVeiw:headView);
            }
            if (viewType == TYPE_FOOTER) {
                return new FooterViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                mFooterResId>0?mFooterResId: R.layout.list_foot_loading, parent, false));
            } else { // type normal
                return mInternalAdapter.onCreateViewHolder(parent, viewType);
            }
        }
        public class FooterViewHolder extends ViewHolder {

            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }
        public class HeaderViewHolder extends ViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
            }
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type != TYPE_FOOTER && type != TYPE_HEADER) {
                if(mHeaderVeiw!=null||headView!=null) {
                    mInternalAdapter.onBindViewHolder(holder, --position);
                }else {
                    mInternalAdapter.onBindViewHolder(holder, position);
                }
            }
        }

        @Override
        public int getItemCount() {
            int count = mInternalAdapter.getItemCount();
            if (mIsFooterEnable) count++;
            if (mHeaderVeiw!=null) count++;
            return count;
        }

        public int getListCount(){
            return mInternalAdapter.getItemCount();
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if(lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && holder.getLayoutPosition() == 0&&headView!=null) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
                Log.i("LoadTAG", "wo  lai  le 111");
            }
            Log.i("LoadTAG",+holder.getLayoutPosition()+"----"+getLastVisiblePosition()+"-----"+getItemCount()+"------"+getAdapter().getItemCount());
            if(lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && holder.getLayoutPosition() == getAdapter().getItemCount()-1&&mIsFooterEnable) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }

//        @Override
//        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//            super.onAttachedToRecyclerView(recyclerView);
//
//            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//            if(manager instanceof GridLayoutManager) {
//                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                    @Override
//                    public int getSpanSize(int position) {
//                        return getItemViewType(position) == TYPE_FOOTER
//                                ? gridManager.getSpanCount() : 1;
//                    }
//                });
//            }
//        }

        public void addHeaderView(View view) {
            mHeaderVeiw = view;
        }
        public void addFooterView(int resId) {
            mFooterResId = resId;
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mAutoLoadAdapter = new AutoLoadAdapter(adapter);
        }
        super.swapAdapter(mAutoLoadAdapter, true);
    }

    private int getMinPositions(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }

    private int getLastVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    public void addHeaderView(View view) {
        if(mAutoLoadAdapter!=null) {
            mAutoLoadAdapter.addHeaderView(view);
        }else {
            headView=view;
        }
    }

    public void addFooterView(int resId) {
        mAutoLoadAdapter.addFooterView(resId);
    }

    public void setAutoLoadMoreEnable(boolean autoLoadMore) {
        mIsFooterEnable = autoLoadMore;
    }

    public void notifyMoreFinish(boolean hasMore,int i) {
        setAutoLoadMoreEnable(hasMore);
        if(i==0) {
            getAdapter().notifyItemRemoved(getLastVisiblePosition());
        }else {
            getAdapter().notifyDataSetChanged();
        }
        mIsLoadingMore = false;
    }

    public void notifyInsertFinish(int start,int end) {
        getAdapter().notifyItemRangeInserted(start,end);
        mIsLoadingMore = false;
    }


}
