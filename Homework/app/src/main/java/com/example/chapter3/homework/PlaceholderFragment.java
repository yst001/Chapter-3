package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    private ListView listView;
    private String[] data = {"people1","people2","people3","people4","people5"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = (LottieAnimationView) view.findViewById(R.id.animation_view);
        listView = (ListView) view.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(container.getContext(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String people = data[position];
                Toast.makeText(view.getContext(),"You click "+people,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animationView,"alpha",1f,0);
                animator1.setDuration(500);
                animator1.start();
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listView,"alpha",0,1f);
                animator2.setDuration(500);
                listView.setVisibility(View.VISIBLE);
                animator2.start();
            }
        }, 5000);
    }
}
