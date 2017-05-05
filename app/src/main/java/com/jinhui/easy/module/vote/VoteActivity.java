package com.jinhui.easy.module.vote;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.jinhui.easy.R;
import com.jinhui.easy.base.ArrayListAdapter;
import com.jinhui.easy.base.BaseActivity;
import com.jinhui.easy.common.dialog.DialogFactory;
import com.jinhui.easy.module.vote.data.VoteEntity;
import com.jinhui.easy.module.vote.data.source.VoteDataSource;
import com.jinhui.easy.module.vote.data.source.VoteRemoteRepo;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/15 13:57
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class VoteActivity extends BaseActivity implements VoteDataSource.CarridVoteCallBack{

    private TextView voteNumber;
    private TextView voteTitle;
    private ListView voteListView;

    VoteEntity entities;

    private VoteAdapter adapter;

    private VoteRemoteRepo remoteRepo;

    private int id,voteId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_vote);
        voteNumber=(TextView) findViewById(R.id.VoteActivity_number);
        voteTitle=(TextView)findViewById(R.id.VoteActivity_title);
        voteListView=(ListView) findViewById(R.id.VoteActivity_listView);
        ButterKnife.bind(this);
        setToolbarTitle("投票");
        entities=(VoteEntity) getIntent().getSerializableExtra("entity");
        initUI();
        remoteRepo=new VoteRemoteRepo();
    }

    private void initUI(){
        if (entities!=null){
            voteTitle.setText(entities.getTitle());
            voteNumber.setText("参与人数:"+entities.getVoteData().size());
        }

        adapter=new VoteAdapter(this);
        voteListView.setAdapter(adapter);
        adapter.appendToList(entities.getVoteData());

    }


    @OnClick(R.id.vote_button)
    public void onClick() {
        if (voteId==0){
            showToast("选择投票人");
            return;
        }
        DialogFactory.showMsgDialog(this, "投票提示", "确定投票", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remoteRepo.carridVote(id,voteId,VoteActivity.this);
            }
        });
    }

    @Override
    public void onCarridVoteStart() {
        loadDialog= DialogFactory.createLoadingDialog(this,"投票...");
    }

    @Override
    public void onCarridVoteEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void onCarridVoteSuccess() {
        showToast("投票成功");
        finish();
    }

    @Override
    public void onCarridVoteFailure() {
        showToast("投票失败");
    }

    private class VoteAdapter extends ArrayListAdapter<VoteEntity.VoteDataBean>{
        public VoteAdapter(Activity context){
            super(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                holder=new ViewHolder();
                convertView=mLayoutInflater.inflate(R.layout.item_vote_account,null);
                holder.name=ButterKnife.findById(convertView,R.id.Vote_userName);
                holder.radioButton=ButterKnife.findById(convertView,R.id.Vote_radioButton);

                convertView.setTag(holder);
            }else {
                holder=(ViewHolder) convertView.getTag();
            }
                holder.radioButton.setChecked(mList.get(position).isSelecte());
            holder.name.setText(mList.get(position).getName());
            holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mList.get(position).setSelecte(isChecked);
                    notifyDataSetChanged();
                    id=mList.get(position).getId();
                    voteId=mList.get(position).getVoteid();
                }
            });
            return convertView;
        }

        class ViewHolder{
            TextView name;
            CheckBox radioButton;
        }
    }
}
