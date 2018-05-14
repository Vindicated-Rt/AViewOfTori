package com.example.lenovo.aviewoftori.Fragment;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.Activity.HomeActivity;
import com.example.lenovo.aviewoftori.Activity.PosterActivity;
import com.example.lenovo.aviewoftori.Other.SeekBarListener;
import com.example.lenovo.aviewoftori.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment{

    private Button poster_btn;

    private View view;

    public ToolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.tool_fragment, container, false);

        toolfindid();

        return view;

    }

    public void toolfindid(){

        poster_btn = (Button) view.findViewById(R.id.tool_poster);

        poster_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent poster = new Intent(getActivity(), PosterActivity.class);

                startActivity(poster);
            }
        });

    }
}
