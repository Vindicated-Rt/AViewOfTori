package com.example.lenovo.aviewoftori.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.Activity.PosterActivity;
import com.example.lenovo.aviewoftori.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment {

    private Button poster_btn;

    private Button tool_other_1;

    private Button tool_other_2;

    private View v;

    String poetry[] = {"忽如一夜春风来，千树万树梨花开", "千里冰封，万里雪飘", "今我来思，雨雪霏霏", "吹灯窗更明，月照一天雪",
            "入扇萦离匣，点素皎残机", "孤舟蓑笠翁，独钓寒江雪", "千里黄云白日曛，北风吹雁雪纷纷", "微风摇庭树，细雪下帘隙",
            "别有根芽，不是人间富贵花", "天光乍破遇，暮雪白头老"};

    public ToolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.tool_fragment, container, false);

        toolfindid();


        return v;

    }


    public void toolfindid() {

        tool_other_1 = (Button) v.findViewById(R.id.tool_other_1);

        tool_other_1.getBackground().setAlpha(0);

        tool_other_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun();
            }
        });

        tool_other_2 = (Button) v.findViewById(R.id.tool_other_2);

        tool_other_2.getBackground().setAlpha(0);

        tool_other_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun();
            }
        });

        poster_btn = (Button) v.findViewById(R.id.tool_poster);

        poster_btn.getBackground().setAlpha(0);

        poster_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent poster = new Intent(getActivity(), PosterActivity.class);

                startActivity(poster);
            }
        });

    }


    public void fun() {

        Random random = new Random();

        int r = random.nextInt(9);

        Toast.makeText(getContext(), poetry[r], Toast.LENGTH_SHORT).show();
    }
}
