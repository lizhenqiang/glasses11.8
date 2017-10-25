package com.remote.glasses.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.litesuits.orm.LiteOrm;
import com.remote.glasses.R;
import com.remote.glasses.activity.GlassesMessageActivity;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.base.MyApplication;
import com.zbar.lib.CaptureActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the[
 * to handle interaction events.
 * Use the {@link GlassesChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GlassesChoiceFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LiteOrm liteOrm;
    private View view;
    private Button btnJingyan,btnJingjia;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public GlassesChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GlassesChoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GlassesChoiceFragment newInstance(String param1, String param2) {
        GlassesChoiceFragment fragment = new GlassesChoiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.glasses_choice, container, false);
        btnJingyan =(Button)view.findViewById(R.id.jingyan);
        btnJingjia = (Button)view.findViewById(R.id.jingjia);
        btnJingyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GlassesMessageActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
